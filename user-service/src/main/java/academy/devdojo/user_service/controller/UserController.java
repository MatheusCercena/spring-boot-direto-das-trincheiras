package academy.devdojo.user_service.controller;

import academy.devdojo.user_service.domain.User;
import academy.devdojo.user_service.mapper.UserMapper;
import academy.devdojo.user_service.request.UserPostRequestBody;
import academy.devdojo.user_service.request.UserPutRequestBody;
import academy.devdojo.user_service.response.UserResponseBody;
import academy.devdojo.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserMapper MAPPER;
    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseBody> save(@RequestBody UserPostRequestBody requestBody) {
        User userToBeSaved = MAPPER.postRequestBodyToUser(requestBody);
        return ResponseEntity.ok(MAPPER.userToResponseBody(service.save(userToBeSaved)));
    }

    @GetMapping("list")
    public ResponseEntity<List<UserResponseBody>> listAll() {
        return ResponseEntity.ok(MAPPER.userListToResponseBodyList(service.listAll()));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<UserResponseBody> findById(@PathVariable Long id) {
        return ResponseEntity.ok(MAPPER.userToResponseBody(service.findById(id)));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<UserResponseBody>> findByFullName(@PathVariable String name) {
        return ResponseEntity.ok(MAPPER.userListToResponseBodyList(service.findByFullName(name)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        User userToDelete = service.findById(id);
        service.delete(userToDelete);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<UserResponseBody> update(@RequestBody UserPutRequestBody requestBody) {
        User userToUpdate = MAPPER.putRequestBodyToUser(requestBody);
        User userUpdated = service.update(userToUpdate);
        return ResponseEntity.ok(MAPPER.userToResponseBody(userUpdated));
    }


}

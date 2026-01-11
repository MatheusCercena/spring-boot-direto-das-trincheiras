package academy.devdojo.user_service.service;

import academy.devdojo.user_service.domain.User;
import academy.devdojo.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User save(User user) {
        repository.save(user);
        return user;
    }

    public List<User> listAll() {
        return repository.listAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public List<User> findByFullName(String fullName) {
        if (fullName.isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        return repository.findByFullName(fullName);
    }

    public User update(User user) {
        return repository.update(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }
}

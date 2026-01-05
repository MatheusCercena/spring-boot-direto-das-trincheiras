package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequestBody;
import academy.devdojo.request.AnimePutRequestBody;
import academy.devdojo.response.AnimeResponseBody;
import academy.devdojo.service.AnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("anime")
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeService service;
    private final AnimeMapper MAPPER;

    @GetMapping("listAll")
    public ResponseEntity<List<AnimeResponseBody>> listAll() {
        List<AnimeResponseBody> responseBody = MAPPER.toResponseBodyList(service.list());
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("filter")
    public ResponseEntity<List<AnimeResponseBody>> filterParam(@RequestParam(required = true) String name) {
        List<AnimeResponseBody> responseBodyList = MAPPER.toResponseBodyList(service.filterByName(name));
        return ResponseEntity.ok(responseBodyList);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<AnimeResponseBody> findByName(@PathVariable String name) {
        AnimeResponseBody responseBody = MAPPER.toResponseBody(service.findByName(name));
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<AnimeResponseBody> findById(@PathVariable Long id) {
        AnimeResponseBody responseBody = MAPPER.toResponseBody(service.findById(id));
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody AnimePostRequestBody AnimePostRequestBody) {
        Anime savedAnime = MAPPER.toAnime(AnimePostRequestBody);
        service.save(savedAnime);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnime);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Anime AnimeFound = service.findById(id);
        service.delete(AnimeFound);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Anime> replace(@RequestBody AnimePutRequestBody AnimeRequestBody) {
        Anime AnimeToBeUpdated = MAPPER.toAnime(AnimeRequestBody);
        service.update(AnimeToBeUpdated);
        return ResponseEntity.noContent().build();
    }
}

package academy.devdojo.controller;

import academy.devdojo.domain.Producer;
import academy.devdojo.mapper.ProducerMapper;
import academy.devdojo.request.ProducerPostRequestBody;
import academy.devdojo.request.ProducerPutRequestBody;
import academy.devdojo.response.ProducerResponseBody;
import academy.devdojo.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("producer")
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerService service;
    private final ProducerMapper MAPPER;

    @GetMapping("listAll")
    public ResponseEntity<List<ProducerResponseBody>> listAll() {
        List<ProducerResponseBody> responseBody = MAPPER.toResponseBodyList(service.list());
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("filter")
    public ResponseEntity<List<ProducerResponseBody>> filterParam(@RequestParam(required = true) String name) {
        List<ProducerResponseBody> responseBodyList = MAPPER.toResponseBodyList(service.filterByName(name));
        return ResponseEntity.ok(responseBodyList);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<ProducerResponseBody> findByName(@PathVariable String name) {
        ProducerResponseBody responseBody = MAPPER.toResponseBody(service.findByName(name));
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ProducerResponseBody> findById(@PathVariable Long id) {
        ProducerResponseBody responseBody = MAPPER.toResponseBody(service.findById(id));
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<Producer> save(@RequestBody ProducerPostRequestBody producerPostRequestBody) {
        Producer savedProducer = MAPPER.toProducer(producerPostRequestBody);
        service.save(savedProducer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProducer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Producer producerFound = service.findById(id);
        service.delete(producerFound);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Producer> replace(@RequestBody ProducerPutRequestBody producerRequestBody) {
        Producer producerToBeUpdated = MAPPER.toProducer(producerRequestBody);
        service.update(producerToBeUpdated);
        return ResponseEntity.noContent().build();
    }
}
package academy.devdojo.controller;

import academy.devdojo.config.Connection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConnectionController {
    private final Connection connectionMysql;

    @GetMapping("connection")
    public ResponseEntity<Connection> connectionMysql() {
        return ResponseEntity.ok(connectionMysql);
    }
}

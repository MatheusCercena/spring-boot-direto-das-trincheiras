package academy.devdojo.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProducerPutRequestBody {
    private Long id;
    private String name;
}

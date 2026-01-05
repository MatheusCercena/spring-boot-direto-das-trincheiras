package academy.devdojo.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Producer {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}

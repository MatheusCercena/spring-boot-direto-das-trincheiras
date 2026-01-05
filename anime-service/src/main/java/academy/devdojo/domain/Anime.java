package academy.devdojo.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Anime {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}



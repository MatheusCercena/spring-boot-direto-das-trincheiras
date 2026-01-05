package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class ProducerData {
    private final ArrayList<Producer> data = new ArrayList<>();

    {
        Producer dbz = new Producer(1L, "Kyoto Animation", LocalDateTime.now());
        Producer pokemon = new Producer(2L, "Pokemon Company", LocalDateTime.now());
        data.addAll(List.of(dbz, pokemon));
    }

}

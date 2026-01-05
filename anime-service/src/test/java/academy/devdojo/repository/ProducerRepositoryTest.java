package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProducerRepositoryTest {
    @InjectMocks
    private ProducerRepository repository;

    @Mock
    private ProducerData producerData;
    private final ArrayList<Producer> producers = new ArrayList<>();

    @BeforeEach
    void init() {
        Producer dbz = new Producer(1L, "Kyoto Animation", LocalDateTime.now());
        Producer pokemon = new Producer(2L, "Pokemon Company", LocalDateTime.now());
        producers.addAll(List.of(dbz, pokemon));
        BDDMockito.when(producerData.getData()).thenReturn(producers);
    }

    @Test
    @DisplayName("list returns a list with all producers")
    void list_ReturnAllProducers_WhenSuccessful() {
        List<Producer> list = repository.list();
        Assertions.assertThat(list).isNotNull();
        Assertions.assertThat(list).hasSize(producers.size());
    }
}
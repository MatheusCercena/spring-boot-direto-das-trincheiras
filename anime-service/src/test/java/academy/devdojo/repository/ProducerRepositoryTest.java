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
import java.util.Optional;

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
        Producer pokemon2 = new Producer(3L, "Pokemon Company", LocalDateTime.now());
        producers.addAll(List.of(dbz, pokemon, pokemon2));
        BDDMockito.when(producerData.getData()).thenReturn(producers);
    }

    @Test
    @DisplayName("list returns a list with all producers")
    void list_ReturnListWithAllProducers_WhenSuccessful() {
        List<Producer> list = repository.list();
        Assertions.assertThat(list).isNotNull().hasSameElementsAs(producers);
    }

    @Test
    @DisplayName("findById returns a producer with given Id")
    void findById_ReturnProducerWithGivenId_WhenSuccessful() {
        Producer expectedProducer = producers.getFirst();
        Optional<Producer> producer = repository.findById(expectedProducer.getId());
        Assertions.assertThat(producer).isPresent().contains(expectedProducer);
    }

    @Test
    @DisplayName("findByName returns a producer with given name")
    void findByName_ReturnProducer_WhenSuccessful() {
        Producer expectedProducer = producers.getFirst();
        Optional<Producer> producer = repository.findByName(expectedProducer.getName());
        Assertions.assertThat(producer).isPresent().contains(expectedProducer);
    }

    @Test
    @DisplayName("findByName returns list with all producers with given name")
    void filterByName_ReturnAllFoundProducersInList_WhenNameIsFound() {
        String name = "PokemonCompany";
        List<Producer> expectedProducers = producers.stream().filter(producer -> producer.getName().equals(name)).toList();
        List<Producer> producersFound = repository.filterByName(name);
        Assertions.assertThat(producersFound).containsAll(expectedProducers).hasSize(expectedProducers.size());
    }

    @Test
    @DisplayName("findByName returns an empty list when no producer with given name is found")
    void filterByName_ReturnEmptyList_WhenNameIsNotFound() {
        String name = "NameNotExistent";
        List<Producer> expectedProducers = producers.stream().filter(producer -> producer.getName().equals(name)).toList();
        List<Producer> producersFound = repository.filterByName(name);
        Assertions.assertThat(producersFound).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("save create a producer in repository")
    void save_CreateProducer_WhenSuccessful() {
        Producer producerToBeSaved = Producer.builder().id(99L).name("TestName").createdAt(LocalDateTime.now()).build();
        Producer producerSaved = repository.save(producerToBeSaved);
        Assertions.assertThat(producerSaved).isEqualTo(producerToBeSaved).hasNoNullFieldsOrProperties();
        Assertions.assertThat(producers).contains(producerSaved);
    }

    @Test
    @DisplayName("delete removes a producer in repository")
    void delete_RemoveProducer_WhenSuccessful() {
        Producer producerToBeRemoved = producers.getFirst();
        repository.delete(producerToBeRemoved);
        Assertions.assertThat(producers).doesNotContain(producerToBeRemoved);
    }

    @Test
    @DisplayName("update removes a producer in repository")
    void update_AlterProducerNameAn_WhenSuccessful() {
        Producer producerToBeUpdated = Producer.builder().id(1L).name("Test").createdAt(LocalDateTime.now()).build();
        repository.update(producerToBeUpdated);
        Assertions.assertThat(producers).contains(producerToBeUpdated);
        Optional<Producer> producerFound = repository.findById(producerToBeUpdated.getId());
        Assertions.assertThat(producerFound).isPresent();
        Assertions.assertThat(producerFound.get().getName()).isEqualTo(producerToBeUpdated.getName());
    }
}
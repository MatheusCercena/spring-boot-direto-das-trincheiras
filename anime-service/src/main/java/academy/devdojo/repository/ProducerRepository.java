package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProducerRepository {
    private final ProducerData producerData;

    public List<Producer> list() {
        return producerData.getData();
    }

    public Optional<Producer> findById(Long id) {
        return producerData.getData().stream().filter(producer -> producer.getId().equals(id)).findFirst();
    }

    public List<Producer> filterByName(String name) {
        return producerData.getData().stream().filter(producer -> producer.getName().equalsIgnoreCase(name)).toList();
    }
    public Optional<Producer> findByName(String name) {
        return producerData.getData().stream().filter(producer -> producer.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Producer save(Producer producer) {
        producerData.getData().add(producer);
        return producer;
    }

    public void delete(Producer producer) {
        producerData.getData().remove(producer);
    }

    public void update(Producer producer) {
        delete(producer);
        save(producer);
    }
}
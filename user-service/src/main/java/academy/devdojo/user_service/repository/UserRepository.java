package academy.devdojo.user_service.repository;

import academy.devdojo.user_service.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final List<User> data = new SimulatedDatabase().getUserList();

    public User save(User user) {
        data.add(user);
        return user;
    }

    public List<User> listAll() {
        return data;
    }

    public Optional<User> findById(Long id) {
        return data.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public List<User> findByFullName(String fullName) {
        return data.stream()
                .filter(user -> (user.getFirstName() + user.getLastName()).equals(fullName))
                .toList();
    }

    public User update(User user) {
        delete(user);
        return save(user);
    }

    public void delete(User user) {
        data.remove(user);
    }
}
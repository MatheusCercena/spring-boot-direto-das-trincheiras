package academy.devdojo.user_service.repository;

import academy.devdojo.user_service.domain.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class SimulatedDatabase {
    private final List<User> userList = new ArrayList<>();

    {
        User user1 = User.builder().id(1L).firstName("Matheus ").lastName("Cercena do Nascimento").email("certheus@protonmail.com").build();
        User user2 = User.builder().id(2L).firstName("Victoria ").lastName("Garcia Campos").email("certheus@protonmail.com").build();
        User user3 = User.builder().id(3L).firstName("Verci ").lastName("do Nascimento").email("certheus@protonmail.com").build();
        userList.addAll(List.of(user1, user2, user3));
    }
}

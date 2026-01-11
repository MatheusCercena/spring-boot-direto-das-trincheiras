package academy.devdojo.user_service.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class Connection {
    private String url;
    private String username;
    private String password;
}

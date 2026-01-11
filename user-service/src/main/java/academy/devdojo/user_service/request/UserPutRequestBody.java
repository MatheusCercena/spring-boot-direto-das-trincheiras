package academy.devdojo.user_service.request;

import lombok.Data;

@Data
public class UserPutRequestBody {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}

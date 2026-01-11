package academy.devdojo.user_service.mapper;

import academy.devdojo.user_service.domain.User;
import academy.devdojo.user_service.request.UserPostRequestBody;
import academy.devdojo.user_service.request.UserPutRequestBody;
import academy.devdojo.user_service.response.UserResponseBody;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User postRequestBodyToUser(UserPostRequestBody requestBody);

    User putRequestBodyToUser(UserPutRequestBody requestBody);

    UserResponseBody userToResponseBody(User user);

    List<UserResponseBody> userListToResponseBodyList(List<User> userList);

}
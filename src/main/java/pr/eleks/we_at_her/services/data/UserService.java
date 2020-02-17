package pr.eleks.we_at_her.services.data;

import pr.eleks.we_at_her.dto.UserDto;
import pr.eleks.we_at_her.entities.User;

public interface UserService {
    void createUser(UserDto userDto);

    UserDto convertToDto(User user);

    User convertToEntity(UserDto userDto);
}

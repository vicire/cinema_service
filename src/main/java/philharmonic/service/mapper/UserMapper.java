package philharmonic.service.mapper;

import org.springframework.stereotype.Component;
import philharmonic.model.User;
import philharmonic.model.dto.response.UserResponseDto;

@Component
public class UserMapper implements ResponseDtoMapper<UserResponseDto, User> {
    @Override
    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        return dto;
    }
}

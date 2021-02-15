package cinema.service.mapper;

import cinema.model.User;
import cinema.model.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setUserId(user.getId());
        dto.setEmail(user.getEmail());
        return dto;
    }
}

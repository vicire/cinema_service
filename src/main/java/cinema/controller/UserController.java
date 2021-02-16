package cinema.controller;

import cinema.model.dto.response.UserResponseDto;
import cinema.service.UserService;
import cinema.service.mapper.UserMapper;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getByEmail(@RequestParam String email) {
        return userMapper.toDto(userService.findByEmail(email).orElseThrow(()
                -> new NoSuchElementException("There is no user by email " + email)));
    }
}

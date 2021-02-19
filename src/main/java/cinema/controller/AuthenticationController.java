package cinema.controller;

import cinema.model.dto.request.UserRequestDto;
import cinema.security.AuthenticationService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody@Valid UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getEmail(),
                userRequestDto.getPassword());
    }
}

package cinema.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import cinema.model.User;
import cinema.service.UserService;
import java.util.NoSuchElementException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.findByEmail(email).orElseThrow(()
                -> new NoSuchElementException("Can`t find user by email " + email));
        UserBuilder userBuilder = withUsername(email)
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(r -> r.getRoleName().toString())
                        .toArray(String[]::new));
        return userBuilder.build();
    }
}

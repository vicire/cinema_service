package cinema.security;

import cinema.exception.AuthenticationException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.User;
import cinema.service.UserService;
import cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> foundedUser = userService.findByEmail(email);
        if (foundedUser.isEmpty() || !foundedUser.get().getPassword()
                .equals(HashUtil.hashPassword(password, foundedUser.get().getSalt()))) {
            throw new AuthenticationException("Your login or password are incorrect. "
                    + "Please, try again");
        }
        return foundedUser.get();
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        byte[] salt = HashUtil.getSalt();
        String hashedPassword = HashUtil.hashPassword(password, salt);
        user.setSalt(salt);
        user.setPassword(hashedPassword);
        userService.add(user);
        return user;
    }
}

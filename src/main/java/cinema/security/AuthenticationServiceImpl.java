package cinema.security;

import cinema.exception.AuthenticationException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.User;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

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
        user.setPassword(password);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}

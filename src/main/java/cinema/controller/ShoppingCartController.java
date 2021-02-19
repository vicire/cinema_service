package cinema.controller;

import cinema.model.MovieSession;
import cinema.model.User;
import cinema.model.dto.response.ShoppingCartResponseDto;
import cinema.service.MovieSessionService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import cinema.service.mapper.ShoppingCartMapper;
import java.util.NoSuchElementException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserService userService;
    private final MovieSessionService movieSessionService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  ShoppingCartMapper shoppingCartMapper, UserService userService,
                                  MovieSessionService movieSessionService) {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartMapper = shoppingCartMapper;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(Authentication authentication,
                                                  @RequestParam Long movieSessionId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(()
                -> new NoSuchElementException("There are no user with such credentials"));
        MovieSession movieSession = movieSessionService.get(movieSessionId);
        shoppingCartService.addSession(movieSession, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto get(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(()
                -> new NoSuchElementException("There are no user with such credentials"));
        return shoppingCartMapper.toDto(shoppingCartService.getByUser(user));
    }
}

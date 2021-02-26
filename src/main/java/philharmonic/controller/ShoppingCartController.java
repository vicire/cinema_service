package philharmonic.controller;

import java.util.NoSuchElementException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import philharmonic.model.ConcertSession;
import philharmonic.model.User;
import philharmonic.model.dto.response.ShoppingCartResponseDto;
import philharmonic.service.ConcertSessionService;
import philharmonic.service.ShoppingCartService;
import philharmonic.service.UserService;
import philharmonic.service.mapper.ShoppingCartMapper;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserService userService;
    private final ConcertSessionService concertSessionService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  ShoppingCartMapper shoppingCartMapper, UserService userService,
                                  ConcertSessionService concertSessionService) {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartMapper = shoppingCartMapper;
        this.userService = userService;
        this.concertSessionService = concertSessionService;
    }

    @PostMapping("/concert-sessions")
    public void addMovieSession(Authentication authentication,
                                                  @RequestParam Long concertSessionId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(()
                -> new NoSuchElementException("There are no user with such credentials"));
        ConcertSession concertSession = concertSessionService.get(concertSessionId);
        shoppingCartService.addSession(concertSession, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto get(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(()
                -> new NoSuchElementException("There are no user with such credentials"));
        return shoppingCartMapper.toDto(shoppingCartService.getByUser(user));
    }
}

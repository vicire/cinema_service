package cinema;

import cinema.exception.AuthenticationException;
import cinema.lib.Injector;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.User;
import cinema.security.AuthenticationService;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        Movie movie1 = new Movie();
        movie1.setTitle("Lord of the rings");
        movie1.setDescription("hobbits and elfs");
        movieService.add(movie1);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHall.setDescription("Blue");
        CinemaHallService cinemaHallService = (CinemaHallService) injector
                .getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.of(2021, 2, 10, 20, 23));
        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now())
                .forEach(System.out::println);
        MovieSession movieSession1 = new MovieSession();
        movieSession1.setMovie(movie1);
        movieSession1.setCinemaHall(cinemaHall);
        movieSession1.setShowTime(LocalDateTime.of(2021, 4, 4, 15, 45));
        movieSessionService.add(movieSession1);

        AuthenticationService service = (AuthenticationService) injector
                .getInstance(AuthenticationService.class);
        User user = service.register("Human", "qwerty");
        User user1 = service.register("Animal", "12345");
        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, user);
        System.out.println(shoppingCartService.getByUser(user));
        shoppingCartService.addSession(movieSession, user1);
        System.out.println(shoppingCartService.getByUser(user1));
        shoppingCartService.clear(shoppingCartService.getByUser(user1));
        System.out.println(shoppingCartService.getByUser(user1));

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(shoppingCartService.getByUser(user));
        shoppingCartService.addSession(movieSession1, user);
        System.out.println(orderService.completeOrder(shoppingCartService.getByUser(user)));
        System.out.println(orderService.getOrderHistory(user));
        try {
            System.out.println(service.login("Human", "qwerty"));
            System.out.println(service.login("Human", "qwerty1"));
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }
    }
}

package cinema.controller;

import cinema.model.dto.response.OrderResponseDto;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import cinema.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public OrderController(OrderService orderService, OrderMapper orderMapper,
                           ShoppingCartService shoppingCartService, UserService userService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @PostMapping("/complete")
    public void complete(@RequestParam Long userId) {
        orderService.completeOrder(shoppingCartService.getByUser(userService.get(userId)));
    }

    @GetMapping
    public List<OrderResponseDto> getHistory(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.get(userId)).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}

package cinema.service.mapper;

import cinema.model.Order;
import cinema.model.Ticket;
import cinema.model.dto.response.OrderResponseDto;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setTicketIds(order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        dto.setUserEmail(order.getUser().getEmail());
        dto.setOrderDate(order.getOrderDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        return dto;
    }
}

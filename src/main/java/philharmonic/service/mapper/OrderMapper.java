package philharmonic.service.mapper;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import philharmonic.model.Order;
import philharmonic.model.Ticket;
import philharmonic.model.dto.response.OrderResponseDto;

@Component
public class OrderMapper implements ResponseDtoMapper<OrderResponseDto, Order> {
    @Override
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

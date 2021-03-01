package philharmonic.service.mapper;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import philharmonic.model.ShoppingCart;
import philharmonic.model.Ticket;
import philharmonic.model.dto.response.ShoppingCartResponseDto;

@Component
public class ShoppingCartMapper implements
        ResponseDtoMapper<ShoppingCartResponseDto, ShoppingCart> {
    @Override
    public ShoppingCartResponseDto toDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        dto.setTicketIds(shoppingCart.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}

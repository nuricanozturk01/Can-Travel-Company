package nuricanozturk.dev.service.booking.mapper;

import nuricanozturk.dev.data.entity.Customer;
import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.data.entity.Location;
import nuricanozturk.dev.data.entity.Reservation;
import nuricanozturk.dev.service.booking.dto.BookingResponseDTO;
import nuricanozturk.dev.service.booking.dto.BookingSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(implementationName = "BookingMapperImpl", componentModel = "spring", uses = {Reservation.class, Customer.class, House.class, Location.class, BookingSaveDTO.class})
public interface IBookingMapper
{
    @Mappings({
            @Mapping(source = "reservation.startDate", target = "startDate"),
            @Mapping(source = "reservation.finishDate", target = "finishDate"),
            @Mapping(source = "house.location.city", target = "city"),
            @Mapping(source = "house.location.country", target = "country")

    })
    BookingResponseDTO toBookingResponseDTO(Reservation reservation, Customer customer, House house, BookingSaveDTO bookingSaveDTO);
}

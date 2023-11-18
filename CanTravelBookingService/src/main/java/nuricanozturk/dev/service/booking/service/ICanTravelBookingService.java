package nuricanozturk.dev.service.booking.service;

import nuricanozturk.dev.service.booking.dto.BookingSaveDTO;
import nuricanozturk.dev.service.booking.dto.ResponseDTO;

public interface ICanTravelBookingService
{
    ResponseDTO saveReservationByUsername(BookingSaveDTO bookingSaveDTO);
}

package nuricanozturk.dev.service.booking.service.v1;

import nuricanozturk.dev.service.booking.dto.BookingSaveDTO;
import nuricanozturk.dev.service.booking.dto.ResponseDTO;

public interface ICanTravelBookingService
{
    ResponseDTO saveReservation(BookingSaveDTO bookingSaveDTO, String request);
}

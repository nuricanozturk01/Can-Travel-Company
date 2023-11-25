package nuricanozturk.dev.service.booking.service.v1;

import nuricanozturk.dev.service.booking.dto.BookingSaveDTO;
import nuricanozturk.dev.service.booking.dto.ResponseDTO;

public interface ICanTravelBookingService
{
    /**
     * Save Reservation.
     *
     * @param bookingSaveDTO is bookingSaveDTO
     * @param request        is request
     * @return ResponseDTO
     */
    ResponseDTO saveReservation(BookingSaveDTO bookingSaveDTO, String request);
}

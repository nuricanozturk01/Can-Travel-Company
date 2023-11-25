package nuricanozturk.dev.service.booking.service.v2;

import nuricanozturk.dev.service.booking.dto.BookingSaveDTOv2;
import nuricanozturk.dev.service.booking.dto.ResponseDTO;

public interface ICanTravelBookingServiceV2
{
    /**
     * Save Reservation.
     *
     * @param bookingSaveDTO is bookingSaveDTO
     * @param token          is token
     * @return ResponseDTO
     */
    ResponseDTO saveReservation(BookingSaveDTOv2 bookingSaveDTO, String token);
}

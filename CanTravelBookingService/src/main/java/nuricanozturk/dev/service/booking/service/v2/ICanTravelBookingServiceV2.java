package nuricanozturk.dev.service.booking.service.v2;

import nuricanozturk.dev.service.booking.dto.BookingSaveDTOv2;
import nuricanozturk.dev.service.booking.dto.ResponseDTO;

public interface ICanTravelBookingServiceV2
{

    ResponseDTO saveReservation(BookingSaveDTOv2 bookingSaveDTO);
}

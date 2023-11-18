package nuricanozturk.dev.service.booking.controller;

import nuricanozturk.dev.service.booking.dto.BookingSaveDTO;
import nuricanozturk.dev.service.booking.dto.ResponseDTO;
import nuricanozturk.dev.service.booking.service.ICanTravelBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static callofproject.dev.library.exception.util.ExceptionUtil.subscribe;
import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/api/booking")
public class CanTravelBookingController
{
    private final ICanTravelBookingService m_bookingService;

    public CanTravelBookingController(ICanTravelBookingService bookingService)
    {
        m_bookingService = bookingService;
    }

    @PostMapping("reservation")
    public ResponseEntity<Object> saveReservationWithCustomerUsername(@RequestBody BookingSaveDTO bookingSaveDTO)
    {
        return subscribe(() -> ok(m_bookingService.saveReservationByUsername(bookingSaveDTO)),
                ex -> internalServerError().body(new ResponseDTO("false", false, null)));
    }
}
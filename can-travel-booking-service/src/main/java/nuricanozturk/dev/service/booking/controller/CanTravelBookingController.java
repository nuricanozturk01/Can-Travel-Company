package nuricanozturk.dev.service.booking.controller;

import nuricanozturk.dev.service.booking.dto.BookingSaveDTO;
import nuricanozturk.dev.service.booking.service.ICanTravelBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
        return ResponseEntity.ok(m_bookingService.saveReservation(bookingSaveDTO));
    }
}

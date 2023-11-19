package nuricanozturk.dev.service.booking.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import nuricanozturk.dev.service.booking.dto.BookingSaveDTO;
import nuricanozturk.dev.service.booking.dto.ResponseDTO;
import nuricanozturk.dev.service.booking.service.v1.ICanTravelBookingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static callofproject.dev.library.exception.util.ExceptionUtil.subscribe;
import static nuricanozturk.dev.service.booking.util.Constants.BOOKING_SERVICE_V1;
import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/api/v1/booking")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Booking Service Controller", description = "")
public class CanTravelBookingController
{
    private final ICanTravelBookingService m_bookingService;

    public CanTravelBookingController(@Qualifier(BOOKING_SERVICE_V1) ICanTravelBookingService bookingService)
    {
        m_bookingService = bookingService;
    }


    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Date Format must be (dd/MM/yyyy): ex.[29/12/2023]")
    @PostMapping("reservation")
    public ResponseEntity<Object> saveReservationWithCustomerUsername(@RequestBody BookingSaveDTO bookingSaveDTO, HttpServletRequest request)
    {
        var token = request.getHeader("Authorization").substring(7);

        return subscribe(() -> ok(m_bookingService.saveReservation(bookingSaveDTO, token)),
                ex -> internalServerError().body(new ResponseDTO(ex.getMessage(), false, null)));
    }

}

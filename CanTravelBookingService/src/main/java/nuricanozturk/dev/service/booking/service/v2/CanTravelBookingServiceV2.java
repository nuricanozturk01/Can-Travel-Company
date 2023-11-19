package nuricanozturk.dev.service.booking.service.v2;

import callofproject.dev.library.exception.service.DataServiceException;
import callofproject.dev.service.jwt.JwtUtil;
import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.data.entity.Customer;
import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.data.entity.Reservation;
import nuricanozturk.dev.service.booking.dto.BookingSaveDTOv2;
import nuricanozturk.dev.service.booking.dto.ResponseDTO;
import nuricanozturk.dev.service.booking.mapper.IBookingMapper;
import nuricanozturk.dev.service.booking.util.Constants;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;
import static java.time.LocalDate.now;

@Service(Constants.BOOKING_SERVICE_V2)
@Lazy
public class CanTravelBookingServiceV2 implements ICanTravelBookingServiceV2
{
    private final CanTravelServiceHelper m_travelServiceHelper;
    private final IBookingMapper m_bookingMapper;

    public CanTravelBookingServiceV2(CanTravelServiceHelper travelServiceHelper, IBookingMapper bookingMapper)
    {
        m_travelServiceHelper = travelServiceHelper;
        m_bookingMapper = bookingMapper;
    }

    @Override
    public ResponseDTO saveReservation(BookingSaveDTOv2 bookingSaveDTO, String token)
    {
        return doForDataService(() -> saveReservationCallback(bookingSaveDTO, token), "CanTravelBookingService::saveReservation");
    }


    public ResponseDTO saveReservationCallback(BookingSaveDTOv2 bookingSaveDTO, String token)
    {
        if (bookingSaveDTO.startDate().isAfter(bookingSaveDTO.finishDate()) || bookingSaveDTO.startDate().isAfter(now())
                || bookingSaveDTO.finishDate().isBefore(now()))
            throw new DataServiceException("Invalid Date Range!");

        var customer = checkAndReturnOptionalUser(token, bookingSaveDTO.customerId());

        var house = m_travelServiceHelper.findHouseById(UUID.fromString(bookingSaveDTO.houseUUID()));

        if (!isValidUserAndHouse(house, customer, bookingSaveDTO))
            throw new DataServiceException("Please check your information!");

        var reservation = new Reservation(house.get(), customer.get(), bookingSaveDTO.startDate(), bookingSaveDTO.finishDate());

        var savedReservation = m_travelServiceHelper.saveReservation(reservation);

        return prepareResponseMessage(savedReservation, customer.get(), house.get(), bookingSaveDTO);

    }

    private Optional<Customer> checkAndReturnOptionalUser(String request, String id)
    {
        var user = m_travelServiceHelper.findCustomerById(UUID.fromString(id));

        if (user.isEmpty() || !JwtUtil.extractUsername(request).equals(user.get().getUsername()))
            throw new DataServiceException("Permission denied! You are not real user!");

        return user;
    }

    private boolean isValidUserAndHouse(Optional<House> house, Optional<Customer> customer, BookingSaveDTOv2 bookingSaveDTO)
    {
        if (customer.isEmpty())
            throw new DataServiceException("Customer does not found!");

        var isAvailableHouse = m_travelServiceHelper.isHouseAvailableBetweenDates(UUID.fromString(bookingSaveDTO.houseUUID()),
                bookingSaveDTO.startDate(), bookingSaveDTO.finishDate());
        if (!isAvailableHouse)
            throw new DataServiceException("House is not available between dates!");

        if (house.get().getMaxParticipantCount() < bookingSaveDTO.participantCount())
            throw new DataServiceException("Max participant count is: " + house.get().getMaxParticipantCount());

        return true;
    }

    private ResponseDTO prepareResponseMessage(Reservation reservation, Customer customer, House house, BookingSaveDTOv2 booking)
    {

        var dto = m_bookingMapper.toBookingResponseDTOv2(reservation, customer, house, booking);

        return new ResponseDTO("Reservation operation is successful!", true, dto);
    }
}

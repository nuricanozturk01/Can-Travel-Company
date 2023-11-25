package nuricanozturk.dev.service.booking.service.v1;

import callofproject.dev.library.exception.service.DataServiceException;
import callofproject.dev.service.jwt.JwtUtil;
import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.data.entity.Customer;
import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.data.entity.Reservation;
import nuricanozturk.dev.service.booking.dto.BookingSaveDTO;
import nuricanozturk.dev.service.booking.dto.ResponseDTO;
import nuricanozturk.dev.service.booking.mapper.IBookingMapper;
import nuricanozturk.dev.service.booking.util.Constants;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;
import static java.time.LocalDate.now;

@Service(Constants.BOOKING_SERVICE_V1)
@Lazy
public class CanTravelBookingService implements ICanTravelBookingService
{
    private final CanTravelServiceHelper m_travelServiceHelper;
    private final IBookingMapper m_bookingMapper;

    public CanTravelBookingService(CanTravelServiceHelper travelServiceHelper, IBookingMapper bookingMapper)
    {
        m_travelServiceHelper = travelServiceHelper;
        m_bookingMapper = bookingMapper;
    }

    /**
     * Save Reservation with given dto class.
     *
     * @param bookingSaveDTO represent the dto class
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTO saveReservation(BookingSaveDTO bookingSaveDTO, String request)
    {
        return doForDataService(() -> saveReservationCallback(bookingSaveDTO, request), "CanTravelBookingService::saveReservation");
    }

    /**
     * Save Reservation with given dto class.
     *
     * @param bookingSaveDTO represent the dto class
     * @return ResponseDTO.
     */
    public ResponseDTO saveReservationCallback(BookingSaveDTO bookingSaveDTO, String request)
    {
        if (bookingSaveDTO.startDate().isAfter(bookingSaveDTO.finishDate()) || bookingSaveDTO.finishDate().isBefore(now()))
            throw new DataServiceException("Invalid Date Range!");

        var customer = checkAndReturnOptionalUser(request, bookingSaveDTO.customer_username());

        var house = m_travelServiceHelper.findHouseById(UUID.fromString(bookingSaveDTO.houseUUID()));

        if (!isValidUserAndHouse(house, customer, bookingSaveDTO))
            throw new DataServiceException("Please check your information!");

        var reservation = new Reservation(house.get(), customer.get(), bookingSaveDTO.startDate(), bookingSaveDTO.finishDate());

        var savedReservation = m_travelServiceHelper.saveReservation(reservation);

        return prepareResponseMessage(savedReservation, customer.get(), house.get(), bookingSaveDTO);

    }

    /**
     * Find user with given username
     *
     * @param username represent the username.
     * @return UserDTO class.
     */
    private Optional<Customer> checkAndReturnOptionalUser(String request, String username)
    {
        if (!JwtUtil.extractUsername(request).equals(username))
            throw new DataServiceException("Permission denied! You are not real user!");

        return m_travelServiceHelper.findCustomerByUsername(username);
    }

    /**
     * Check user and house is valid.
     *
     * @param house          is house
     * @param customer       is customer
     * @param bookingSaveDTO is bookingSaveDTO
     * @return true if valid else throw exception
     */
    private boolean isValidUserAndHouse(Optional<House> house, Optional<Customer> customer, BookingSaveDTO bookingSaveDTO)
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

    /**
     * Prepare response message.
     *
     * @param reservation is reservation
     * @param customer    is customer
     * @param house       is house
     * @param booking     is booking
     * @return ResponseDTO
     */
    private ResponseDTO prepareResponseMessage(Reservation reservation, Customer customer, House house, BookingSaveDTO booking)
    {

        var dto = m_bookingMapper.toBookingResponseDTO(reservation, customer, house, booking);

        return new ResponseDTO("Reservation operation is successful!", true, dto);
    }
}

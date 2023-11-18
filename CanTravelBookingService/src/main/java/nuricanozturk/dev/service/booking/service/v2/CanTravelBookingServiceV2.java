package nuricanozturk.dev.service.booking.service.v2;

import callofproject.dev.library.exception.service.DataServiceException;
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

import java.util.UUID;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;

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
    public ResponseDTO saveReservation(BookingSaveDTOv2 bookingSaveDTO)
    {
        return doForDataService(() -> saveReservationCallback(bookingSaveDTO), "CanTravelBookingService::saveReservation");
    }


    public ResponseDTO saveReservationCallback(BookingSaveDTOv2 bookingSaveDTO)
    {
        var isAvailableHouse = m_travelServiceHelper.isHouseAvailableBetweenDates(UUID.fromString(bookingSaveDTO.houseUUID()),
                bookingSaveDTO.startDate(), bookingSaveDTO.finishDate());

        if (!isAvailableHouse)
            return new ResponseDTO("House is not available between dates!", false, null);

        var house = m_travelServiceHelper.findHouseById(UUID.fromString(bookingSaveDTO.houseUUID()));
        var customer = m_travelServiceHelper.findCustomerById(UUID.fromString(bookingSaveDTO.customerId()));

        if (house.isEmpty() || customer.isEmpty())
            throw new DataServiceException("Something wrong in server!");

        if (house.get().getMaxParticipantCount() < bookingSaveDTO.participantCount())
            return new ResponseDTO("Max participant count is: " + house.get().getMaxParticipantCount(), false, null);

        var reservation = new Reservation(house.get(), customer.get(), bookingSaveDTO.startDate(), bookingSaveDTO.finishDate());

        var savedReservation = m_travelServiceHelper.saveReservation(reservation);

        return prepareResponseMessage(savedReservation, customer.get(), house.get(), bookingSaveDTO);

    }

    private ResponseDTO prepareResponseMessage(Reservation reservation, Customer customer, House house, BookingSaveDTOv2 booking)
    {

        var dto = m_bookingMapper.toBookingResponseDTOv2(reservation, customer, house, booking);

        return new ResponseDTO("Reservation operation is successful!", true, dto);
    }
}

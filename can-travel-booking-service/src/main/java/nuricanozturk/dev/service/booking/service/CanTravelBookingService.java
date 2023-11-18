package nuricanozturk.dev.service.booking.service;

import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.data.entity.Reservation;
import nuricanozturk.dev.service.booking.dto.BookingSaveDTO;
import nuricanozturk.dev.service.booking.dto.ResponseDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class CanTravelBookingService implements ICanTravelBookingService
{
    private final CanTravelServiceHelper m_travelServiceHelper;

    public CanTravelBookingService(CanTravelServiceHelper travelServiceHelper)
    {
        m_travelServiceHelper = travelServiceHelper;
    }

    @Override
    public ResponseDTO saveReservation(BookingSaveDTO bookingSaveDTO)
    {

        m_travelServiceHelper.saveReservationIfAvailable(new Reservation());
        return new ResponseDTO();
    }
}

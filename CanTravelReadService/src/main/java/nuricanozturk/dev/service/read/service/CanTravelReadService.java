package nuricanozturk.dev.service.read.service;

import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.data.entity.House;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Lazy
public class CanTravelReadService
{
    private final CanTravelServiceHelper m_travelServiceHelper;

    public CanTravelReadService(CanTravelServiceHelper travelServiceHelper)
    {
        m_travelServiceHelper = travelServiceHelper;
    }


    public Iterable<House> findAllHouse(int page)
    {
        var k = m_travelServiceHelper.findAvailableHousesBetweenDates(LocalDate.of(2023, 10, 16),
                LocalDate.of(2023, 12, 23),10, page);
        return k;
    }
}

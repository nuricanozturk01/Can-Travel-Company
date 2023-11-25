package nuricanozturk.dev.service.booking.service;

import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.data.entity.Customer;
import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.data.entity.Location;
import nuricanozturk.dev.data.entity.Reservation;
import nuricanozturk.dev.service.booking.dto.HouseSaveDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;
import static callofproject.dev.util.stream.StreamUtil.toStream;

@Service
@Lazy
public class AdminService implements IAdminService
{
    private final CanTravelServiceHelper m_travelServiceHelper;

    public AdminService(CanTravelServiceHelper travelServiceHelper)
    {
        m_travelServiceHelper = travelServiceHelper;
    }

    /**
     * Find all house.
     *
     * @return Iterable<House>.
     */
    @Override
    public Iterable<House> findAllHouse()
    {
        return doForDataService(() -> m_travelServiceHelper.findAllHouse(), "AdminService::findAllHouse");
    }

    /**
     * Find all customers.
     *
     * @return Iterable<Customer>.
     */
    @Override
    public Iterable<Customer> findAllCustomers()
    {
        return doForDataService(() -> m_travelServiceHelper.findAllCustomersByUsernameNotContainsIgnoreCase("travel_admin"),
                "AdminService::findAllCustomersByUsernameNotContainsIgnoreCase");
    }

    /**
     * Find all locations.
     *
     * @return Iterable<Location>.
     */
    @Override
    public Iterable<Location> findAllLocations()
    {
        return doForDataService(m_travelServiceHelper::findAllLocation, "AdminService::findAllLocations");
    }

    /**
     * Find all reservations.
     *
     * @return Iterable<Reservation>.
     */
    @Override
    public Iterable<Reservation> findAllReservations()
    {
        return doForDataService(m_travelServiceHelper::findAllReservation, "AdminService::findAllReservations");
    }

    /**
     * Save House with given dto class.
     *
     * @param houseSaveDTO represent the dto class
     * @return House.
     */
    @Override
    public House saveHouse(HouseSaveDTO houseSaveDTO)
    {
        return doForDataService(() -> saveHouseCallback(houseSaveDTO), "AdminService::saveHouse");
    }

    /**
     * Save House with given dto class.
     *
     * @param houseSaveDTO represent the dto class
     * @return House.
     */
    private House saveHouseCallback(HouseSaveDTO houseSaveDTO)
    {
        var location = toStream(m_travelServiceHelper.findAllLocationByCityAndCountry(houseSaveDTO.city(), houseSaveDTO.country())).toList();

        var saveLocation = Optional.of(location.isEmpty() ? new Location(houseSaveDTO.city(), houseSaveDTO.country()) : location.get(0));

        var house = new House.Builder()
                .setHouseName(houseSaveDTO.houseName())
                .setHousePhoto(houseSaveDTO.housePhoto())
                .setHouseType(houseSaveDTO.houseType())
                .setView(houseSaveDTO.viewType())
                .setPrice(houseSaveDTO.price())
                .setDescription(houseSaveDTO.description())
                .setLocation(saveLocation.get())
                .setMaxParticipantCount(houseSaveDTO.maxParticipantCount())
                .build();

        return m_travelServiceHelper.saveHouse(house);
    }
}

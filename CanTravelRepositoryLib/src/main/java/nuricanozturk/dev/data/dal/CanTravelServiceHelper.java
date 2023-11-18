package nuricanozturk.dev.data.dal;

import nuricanozturk.dev.data.entity.*;
import nuricanozturk.dev.data.repository.ICustomerRepository;
import nuricanozturk.dev.data.repository.IHouseRepository;
import nuricanozturk.dev.data.repository.ILocationRepository;
import nuricanozturk.dev.data.repository.IReservationRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static callofproject.dev.library.exception.util.CopDataUtil.doForRepository;
import static java.util.Optional.of;

@Component
@Lazy
@SuppressWarnings("all")
public class CanTravelServiceHelper
{
    private final int m_defaultPageSize = 20;
    private final IHouseRepository m_houseRepository;
    private final IReservationRepository m_reservationRepository;
    private final ICustomerRepository m_customerRepository;
    private final ILocationRepository m_locationRepository;

    public CanTravelServiceHelper(IHouseRepository houseRepository, IReservationRepository reservationRepository,
                                  ICustomerRepository customerRepository, ILocationRepository locationRepository)
    {
        m_houseRepository = houseRepository;
        m_reservationRepository = reservationRepository;
        m_customerRepository = customerRepository;
        m_locationRepository = locationRepository;
    }

    public long getPageSize()
    {
        return doForRepository(() -> ((m_houseRepository.count() / m_defaultPageSize) + 1), "CanTravelServiceHelper::count");
    }


    public long getHouseCount()
    {
        return doForRepository(() -> m_houseRepository.count(), "ServiceHelper::getHouseCount");
    }

    public long getLocationCount()
    {
        return doForRepository(() -> m_locationRepository.count(), "ServiceHelper::getLocationCount");
    }

    public long getReservationCount()
    {
        return doForRepository(() -> m_reservationRepository.count(), "ServiceHelper::getReservationCount");
    }

    public long getCustomerCount()
    {
        return doForRepository(() -> m_customerRepository.count(), "ServiceHelper::getCustomerCount");
    }

    public Iterable<House> saveAllHouses(Iterable<House> houses)
    {
        return doForRepository(() -> m_houseRepository.saveAll(houses), "ServiceHelper::saveAllHouses");
    }

    public Iterable<Customer> saveAllCustomers(Iterable<Customer> entities)
    {
        return doForRepository(() -> m_customerRepository.saveAll(entities), "ServiceHelper::saveAllCustomers");
    }

    public Iterable<Location> saveAllLocations(Iterable<Location> entities)
    {
        return doForRepository(() -> m_locationRepository.saveAll(entities), "ServiceHelper::saveAllLocations");
    }

    public void saveAllReservations(Iterable<Reservation> entities)
    {
        doForRepository(() -> entities.forEach(this::saveReservation), "ServiceHelper::saveAllReservations");
    }

    public House saveHouse(House house)
    {
        return doForRepository(() -> m_houseRepository.save(house), "ServiceHelper::saveHouse");
    }

    public Reservation saveReservation(Reservation reservation)
    {
        return doForRepository(() -> m_reservationRepository.save(reservation), "ServiceHelper::saveReservation");
    }

    public Optional<Reservation> saveReservationIfAvailable(Reservation reservation)
    {
        var isAvailable = isHouseAvailableBetweenDates(reservation.getHouse().getHouseId(),
                reservation.getStartDate(), reservation.getFinishDate());
        if (isAvailable)
            return doForRepository(() -> of(m_reservationRepository.save(reservation)), "ServiceHelper::saveReservationIfAvailable");
        return Optional.empty();
    }

    public Customer saveCustomer(Customer customer)
    {
        return doForRepository(() -> m_customerRepository.save(customer), "ServiceHelper::saveCustomer");
    }

    public Location saveLocation(Location location)
    {
        return doForRepository(() -> m_locationRepository.save(location), "ServiceHelper::saveLocation");
    }

    public Iterable<House> findAllHousePageable(int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAll(pageable), "ServiceHelper::findAllHouse");
    }

    public Optional<House> findHouseById(UUID houseId)
    {
        return doForRepository(() -> m_houseRepository.findById(houseId), "ServiceHelper::findHouseById");
    }

    public Iterable<House> findAllHouse()
    {
        return doForRepository(() -> m_houseRepository.findAll(), "ServiceHelper::findAllHouse");
    }

    public Iterable<Location> findAllLocation()
    {
        return doForRepository(m_locationRepository::findAll, "ServiceHelper::findAllLocation");
    }


    public Iterable<Reservation> findAllReservation()
    {
        return doForRepository(m_reservationRepository::findAll, "ServiceHelper::findAllReservation");
    }

    public Iterable<Customer> findAllCustomer()
    {
        return doForRepository(m_customerRepository::findAll, "ServiceHelper::findAllCustomer");
    }

    public Optional<Customer> findCustomerById(UUID uuid)
    {
        return doForRepository(() -> m_customerRepository.findById(uuid), "ServiceHelper::findCustomerById");
    }

    public Iterable<Location> findAllByCity(String city)
    {
        return doForRepository(() -> m_locationRepository.findAllByCity(city), "ServiceHelper::findAllByCity");
    }

    public Iterable<Location> findAllByCountry(String country)
    {
        return doForRepository(() -> m_locationRepository.findAllByCountry(country), "ServiceHelper::findAllByCountry");
    }

    public Optional<Customer> findCustomerByUsername(String username)
    {
        return doForRepository(() -> m_customerRepository.findByUsername(username), "ServiceHelper::findCustomerByUsername");
    }

    public Optional<Customer> findCustomerByEmail(String email)
    {
        return doForRepository(() -> m_customerRepository.findByEmail(email), "ServiceHelper::findCustomerByEmail");
    }

    public Iterable<House> findHouseByViewType(ViewType viewType, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findByViewType(viewType, pageable), "ServiceHelper::findHouseByViewType");
    }

    public Iterable<House> findAllHouseByHouseNameContainsIgnoreCase(String word, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByHouseNameContainsIgnoreCase(word, pageable),
                "ServiceHelper::findAllHouseByHouseNameContainsIgnoreCase");
    }

    public Iterable<House> findAllHouseByHouseType(HouseType houseType, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByHouseType(houseType, pageable),
                "ServiceHelper::findAllHouseByHouseType");
    }

    public Iterable<House> findAllHouseByPriceBetween(double min, double max, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByPriceBetween(min, max, pageable),
                "ServiceHelper::findAllHouseByPriceBetween");
    }

    public Iterable<House> findAllHouseByPriceLessThanEqual(double price, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByPriceLessThanEqual(price, pageable),
                "ServiceHelper::findAllHouseByPriceLessThanEqual");
    }

    public Iterable<House> findAllHouseByPriceGreaterThanEqual(double price, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByPriceGreaterThanEqual(price, pageable),
                "ServiceHelper::findAllHouseByPriceGreaterThanEqual");
    }

    public Iterable<House> findHouseByHouseName(String homeName, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findHouseByHouseName(homeName, pageable),
                "ServiceHelper::findHouseByHouseName");
    }

    public Iterable<House> findAllHouseInCity(String city, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByCity(city, pageable),
                "ServiceHelper::findAllHouseInCity");
    }


    public Iterable<House> findAllHouseInCountry(String country, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByCountry(country, pageable),
                "ServiceHelper::findAllHouseInCountry");
    }


    public Iterable<House> findAllHouseByCountryAndCity(String country, String city, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByCountryAndCity(country, city, pageable),
                "ServiceHelper::findAllHouseByCountryAndCity");
    }


    public Iterable<House> findAvailableHousesBetweenDates(LocalDate startDate, LocalDate finishDate, int page, int participantCount)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAvailableHousesBetweenDates(startDate, finishDate, participantCount, pageable),
                "ServiceHelper::findAvailableHousesBetweenDates");
    }


    public boolean existsReservationByStartDateAfterAndFinishDateBeforeAndHouse(LocalDate start, LocalDate finish, House house)
    {
        return doForRepository(() -> m_reservationRepository.existsReservationByStartDateAfterAndFinishDateBeforeAndHouse(start, finish, house),
                "ServiceHelper::existsReservationByStartDateAfterAndFinishDateBeforeAndHouse");
    }

    public boolean isHouseAvailableBetweenDates(UUID houseId, LocalDate startDate, LocalDate finishDate)
    {
        return doForRepository(() -> m_houseRepository.isHouseAvailableBetweenDates(houseId, startDate, finishDate),
                "ServiceHelper::isHouseAvailableBetweenDates");
    }
}

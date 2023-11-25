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

    /**
     * Get page size.
     *
     * @return long value.
     */
    public long getPageSize()
    {
        return doForRepository(() -> ((m_houseRepository.count() / m_defaultPageSize) + 1), "CanTravelServiceHelper::count");
    }

    /**
     * Get house count.
     *
     * @return long value.
     */
    public long getHouseCount()
    {
        return doForRepository(() -> m_houseRepository.count(), "ServiceHelper::getHouseCount");
    }

    /**
     * Get location count.
     *
     * @return long value.
     */
    public long getLocationCount()
    {
        return doForRepository(() -> m_locationRepository.count(), "ServiceHelper::getLocationCount");
    }

    /**
     * Get reservation count.
     *
     * @return long value.
     */
    public long getReservationCount()
    {
        return doForRepository(() -> m_reservationRepository.count(), "ServiceHelper::getReservationCount");
    }

    /**
     * Get customer count.
     *
     * @return long value.
     */
    public long getCustomerCount()
    {
        return doForRepository(() -> m_customerRepository.count(), "ServiceHelper::getCustomerCount");
    }

    /**
     * Save all houses.
     *
     * @param houses represent the houses.
     */
    public Iterable<House> saveAllHouses(Iterable<House> houses)
    {
        return doForRepository(() -> m_houseRepository.saveAll(houses), "ServiceHelper::saveAllHouses");
    }

    /**
     * save all customers.
     *
     * @param entities represent the entities.
     */
    public Iterable<Customer> saveAllCustomers(Iterable<Customer> entities)
    {
        return doForRepository(() -> m_customerRepository.saveAll(entities), "ServiceHelper::saveAllCustomers");
    }

    /**
     * save all locations.
     *
     * @param entities represent the entities.
     */
    public Iterable<Location> saveAllLocations(Iterable<Location> entities)
    {
        return doForRepository(() -> m_locationRepository.saveAll(entities), "ServiceHelper::saveAllLocations");
    }

    /**
     * save all reservations.
     *
     * @param entities represent the entities.
     */
    public void saveAllReservations(Iterable<Reservation> entities)
    {
        doForRepository(() -> entities.forEach(this::saveReservation), "ServiceHelper::saveAllReservations");
    }

    /**
     * Find all customers by username not contains ignore case.
     *
     * @param username represent the username.
     */
    public Iterable<Customer> findAllCustomersByUsernameNotContainsIgnoreCase(String username)
    {
        return doForRepository(() -> m_customerRepository.findAllByUsernameNotContainsIgnoreCase(username), "ServiceHelper::findAllByUsernameNotContains");
    }

    /**
     * save house.
     *
     * @param house represent the house.
     * @return House.
     */
    public House saveHouse(House house)
    {
        return doForRepository(() -> m_houseRepository.save(house), "ServiceHelper::saveHouse");
    }

    /**
     * save reservation.
     *
     * @param reservation represent the reservation.
     * @return Reservation.
     */
    public Reservation saveReservation(Reservation reservation)
    {
        return doForRepository(() -> m_reservationRepository.save(reservation), "ServiceHelper::saveReservation");
    }

    /**
     * save reservation if available.
     *
     * @param reservation represent the reservation.
     * @return Optional<Reservation>.
     */
    public Optional<Reservation> saveReservationIfAvailable(Reservation reservation)
    {
        var isAvailable = isHouseAvailableBetweenDates(reservation.getHouse().getHouseId(),
                reservation.getStartDate(), reservation.getFinishDate());
        if (isAvailable)
            return doForRepository(() -> of(m_reservationRepository.save(reservation)), "ServiceHelper::saveReservationIfAvailable");

        return Optional.empty();
    }

    /**
     * save customer.
     *
     * @param customer represent the customer.
     * @return Customer.
     */
    public Customer saveCustomer(Customer customer)
    {
        return doForRepository(() -> m_customerRepository.save(customer), "ServiceHelper::saveCustomer");
    }

    /**
     * save location.
     *
     * @param location represent the location.
     * @return Location.
     */
    public Location saveLocation(Location location)
    {
        return doForRepository(() -> m_locationRepository.save(location), "ServiceHelper::saveLocation");
    }

    /**
     * find all houses by page.
     *
     * @param page represent the page.
     * @return Iterable<House>.
     */
    public Iterable<House> findAllHousePageable(int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAll(pageable), "ServiceHelper::findAllHouse");
    }

    /**
     * find house by id.
     *
     * @param houseId represent the house id.
     * @return Optional<House>.
     */
    public Optional<House> findHouseById(UUID houseId)
    {
        return doForRepository(() -> m_houseRepository.findById(houseId), "ServiceHelper::findHouseById");
    }

    /**
     * find all houses.
     *
     * @return Iterable<House>.
     */
    public Iterable<House> findAllHouse()
    {
        return doForRepository(() -> m_houseRepository.findAll(), "ServiceHelper::findAllHouse");
    }

    /**
     * find all houses by page.
     *
     * @param page represent the page.
     * @return Iterable<House>.
     */
    public Iterable<House> findAllHouse(int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAll(pageable), "ServiceHelper::findAllHouse");
    }

    /**
     * find all locations.
     *
     * @return Iterable<Location>.
     */
    public Iterable<Location> findAllLocation()
    {
        return doForRepository(m_locationRepository::findAll, "ServiceHelper::findAllLocation");
    }

    /**
     * find all locations by city and country.
     *
     * @param city    represent the city.
     * @param country represent the country.
     * @return Iterable<Location>.
     */
    public Iterable<Location> findAllLocationByCityAndCountry(String city, String country)
    {
        return doForRepository(() -> m_locationRepository.findAllByCityAndCountry(city, country), "ServiceHelper::findAllByCityAndCountry");
    }

    /**
     * find all reservations.
     *
     * @return Iterable<Reservation>.
     */
    public Iterable<Reservation> findAllReservation()
    {
        return doForRepository(m_reservationRepository::findAll, "ServiceHelper::findAllReservation");
    }

    /**
     * find all customer.
     *
     * @return Iterable<Customer>.
     */
    public Iterable<Customer> findAllCustomer()
    {
        return doForRepository(m_customerRepository::findAll, "ServiceHelper::findAllCustomer");
    }

    /**
     * find customer by id.
     *
     * @param uuid represent the uuid.
     * @return Optional<Customer>.
     */
    public Optional<Customer> findCustomerById(UUID uuid)
    {
        return doForRepository(() -> m_customerRepository.findById(uuid), "ServiceHelper::findCustomerById");
    }

    /**
     * find all locations by city.
     *
     * @param city represent the city.
     * @return Iterable<Location>.
     */
    public Iterable<Location> findAllByCity(String city)
    {
        return doForRepository(() -> m_locationRepository.findAllByCity(city), "ServiceHelper::findAllByCity");
    }

    /**
     * find all locations by country.
     *
     * @param country represent the country.
     * @return Iterable<Location>.
     */
    public Iterable<Location> findAllByCountry(String country)
    {
        return doForRepository(() -> m_locationRepository.findAllByCountry(country), "ServiceHelper::findAllByCountry");
    }

    /**
     * find customer by username.
     *
     * @param username represent the username.
     * @return Optional<Customer>.
     */
    public Optional<Customer> findCustomerByUsername(String username)
    {
        return doForRepository(() -> m_customerRepository.findByUsername(username), "ServiceHelper::findCustomerByUsername");
    }

    /**
     * find customer by email.
     *
     * @param email represent the email.
     * @return Optional<Customer>.
     */
    public Optional<Customer> findCustomerByEmail(String email)
    {
        return doForRepository(() -> m_customerRepository.findByEmail(email), "ServiceHelper::findCustomerByEmail");
    }

    /**
     * find all houses by view type.
     *
     * @param viewType represent the view type.
     * @param page     represent the page.
     * @return Iterable<House>.
     */
    public Iterable<House> findHouseByViewType(ViewType viewType, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findByViewType(viewType, pageable), "ServiceHelper::findHouseByViewType");
    }

    /**
     * find all houses by house name contains ignore case.
     *
     * @param word represent the word.
     * @param page represent the page.
     * @return Iterable<House>.
     */
    public Iterable<House> findAllHouseByHouseNameContainsIgnoreCase(String word, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByHouseNameContainsIgnoreCase(word, pageable),
                "ServiceHelper::findAllHouseByHouseNameContainsIgnoreCase");
    }

    /**
     * find all houses by house type.
     *
     * @param houseType represent the house type.
     * @param page      represent the page.
     * @return Iterable<House>.
     */
    public Iterable<House> findAllHouseByHouseType(HouseType houseType, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByHouseType(houseType, pageable),
                "ServiceHelper::findAllHouseByHouseType");
    }

    /**
     * find all houses by price between.
     *
     * @param min  represent the min price.
     * @param max  represent the max price.
     * @param page represent the page.
     * @return Iterable<House>.
     */
    public Iterable<House> findAllHouseByPriceBetween(double min, double max, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByPriceBetween(min, max, pageable),
                "ServiceHelper::findAllHouseByPriceBetween");
    }

    /**
     * find all houses by price less than equal.
     *
     * @param price represent the price.
     * @param page  represent the page.
     * @return Iterable<House>.
     */
    public Iterable<House> findAllHouseByPriceLessThanEqual(double price, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByPriceLessThanEqual(price, pageable),
                "ServiceHelper::findAllHouseByPriceLessThanEqual");
    }

    /**
     * find all houses by price greater than equal.
     *
     * @param price represent the price.
     * @param page  represent the page.
     * @return Iterable<House>.
     */
    public Iterable<House> findAllHouseByPriceGreaterThanEqual(double price, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByPriceGreaterThanEqual(price, pageable),
                "ServiceHelper::findAllHouseByPriceGreaterThanEqual");
    }

    /**
     * find house by house name.
     *
     * @param homeName represent the house name.
     * @param page     represent the page.
     * @return Iterable<House>.
     */
    public Iterable<House> findHouseByHouseName(String homeName, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findHouseByHouseName(homeName, pageable),
                "ServiceHelper::findHouseByHouseName");
    }

    /**
     * find all houses by city.
     *
     * @param city represent the city.
     * @param page represent the page.
     * @return Iterable<House>.
     */
    public Iterable<House> findAllHouseInCity(String city, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByCity(city, pageable),
                "ServiceHelper::findAllHouseInCity");
    }


    /**
     * find all houses by country.
     *
     * @param country represent the country.
     * @param page    represent the page.
     * @return Iterable<House>.
     */
    public Iterable<House> findAllHouseInCountry(String country, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByCountry(country, pageable),
                "ServiceHelper::findAllHouseInCountry");
    }


    /**
     * find all houses by country and city.
     *
     * @param country represent the country.
     * @param city    represent the city.
     * @param page    represent the page.
     * @return Iterable<House>.
     */
    public Iterable<House> findAllHouseByCountryAndCity(String country, String city, int page)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAllByCountryAndCity(country, city, pageable),
                "ServiceHelper::findAllHouseByCountryAndCity");
    }


    /**
     * find available houses between dates.
     *
     * @param startDate        represent the start date.
     * @param finishDate       represent the finish date.
     * @param page             represent the page.
     * @param participantCount represent the participant count.
     * @return Iterable<House>.
     */
    public Iterable<House> findAvailableHousesBetweenDates(LocalDate startDate, LocalDate finishDate, int page, int participantCount)
    {
        var pageable = PageRequest.of(page - 1, m_defaultPageSize);
        return doForRepository(() -> m_houseRepository.findAvailableHousesBetweenDates(startDate, finishDate, participantCount, pageable),
                "ServiceHelper::findAvailableHousesBetweenDates");
    }


    /**
     * exists reservation by start date after and finish date before and house.
     *
     * @param start  represent the start date.
     * @param finish represent the finish date.
     * @param house  represent the house.
     * @return boolean value.
     */
    public boolean existsReservationByStartDateAfterAndFinishDateBeforeAndHouse(LocalDate start, LocalDate finish, House house)
    {
        return doForRepository(() -> m_reservationRepository.existsReservationByStartDateAfterAndFinishDateBeforeAndHouse(start, finish, house),
                "ServiceHelper::existsReservationByStartDateAfterAndFinishDateBeforeAndHouse");
    }

    /**
     * is house available between dates.
     *
     * @param houseId    represent the house id.
     * @param startDate  represent the start date.
     * @param finishDate represent the finish date.
     * @return boolean value.
     */
    public boolean isHouseAvailableBetweenDates(UUID houseId, LocalDate startDate, LocalDate finishDate)
    {
        return doForRepository(() -> m_houseRepository.isHouseAvailableBetweenDates(houseId, startDate, finishDate),
                "ServiceHelper::isHouseAvailableBetweenDates");
    }
}

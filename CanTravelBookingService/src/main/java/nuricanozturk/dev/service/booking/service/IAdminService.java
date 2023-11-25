package nuricanozturk.dev.service.booking.service;

import nuricanozturk.dev.data.entity.Customer;
import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.data.entity.Location;
import nuricanozturk.dev.data.entity.Reservation;
import nuricanozturk.dev.service.booking.dto.HouseSaveDTO;

public interface IAdminService
{

    /**
     * Find all houses.
     *
     * @return Iterable<House>.
     */
    Iterable<House> findAllHouse();

    /**
     * Find all customers.
     *
     * @return Iterable<Customer>.
     */
    Iterable<Customer> findAllCustomers();

    /**
     * Find all locations.
     *
     * @return Iterable<Location>.
     */
    Iterable<Location> findAllLocations();

    /**
     * Find all reservations.
     *
     * @return Iterable<Reservation>.
     */
    Iterable<Reservation> findAllReservations();

    /**
     * Save house.
     *
     * @param houseSaveDTO is houseSaveDTO
     * @return House
     */
    House saveHouse(HouseSaveDTO houseSaveDTO);
}

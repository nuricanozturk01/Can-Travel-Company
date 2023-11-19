package nuricanozturk.dev.service.booking.service;

import nuricanozturk.dev.data.entity.Customer;
import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.data.entity.Location;
import nuricanozturk.dev.data.entity.Reservation;
import nuricanozturk.dev.service.booking.dto.HouseSaveDTO;

public interface IAdminService
{
    Iterable<House> findAllHouse();

    Iterable<Customer> findAllCustomers();

    Iterable<Location> findAllLocations();

    Iterable<Reservation> findAllReservations();

    House saveHouse(HouseSaveDTO houseSaveDTO);
}

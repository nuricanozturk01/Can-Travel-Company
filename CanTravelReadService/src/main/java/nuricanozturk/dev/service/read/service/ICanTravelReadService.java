package nuricanozturk.dev.service.read.service;

import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.service.read.dto.AvailableHouseQueryDTO;
import nuricanozturk.dev.service.read.dto.HousesDTO;

public interface ICanTravelReadService
{
    HousesDTO findAvailableHousesBetweenDates(AvailableHouseQueryDTO queryDTO);

    HousesDTO findAllHouseByCountryAndCity(String country, String city, int page);

    HousesDTO findAllHouseInCountry(String country, int page);

    HousesDTO findAllHouseInCity(String city, int page);

    HousesDTO findHouseByHouseName(String homeName, int page);

    HousesDTO findAllHouseByPriceGreaterThanEqual(double price, int page);

    HousesDTO findAllHouseByPriceLessThanEqual(double price, int page);

    HousesDTO findAllHouseByPriceBetween(double min, double max, int page);

    HousesDTO findAllHouseByHouseType(HouseType houseType, int page);

    HousesDTO findAllHouse(int page);

    long getTotalPage();
}

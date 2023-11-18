package nuricanozturk.dev.service.read.service;

import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.data.entity.ViewType;
import nuricanozturk.dev.service.read.dto.AvailableHouseQueryDTO;
import nuricanozturk.dev.service.read.dto.ResponseDTO;

public interface ICanTravelReadService
{
    ResponseDTO findAvailableHousesBetweenDates(AvailableHouseQueryDTO queryDTO);

    ResponseDTO findAllHouseByCountryAndCity(String country, String city, int page);

    ResponseDTO findAllHouseInCountry(String country, int page);

    ResponseDTO findAllHouseInCity(String city, int page);

    ResponseDTO findHouseByHouseName(String homeName, int page);

    ResponseDTO findAllHouseByPriceGreaterThanEqual(double price, int page);

    ResponseDTO findAllHouseByPriceLessThanEqual(double price, int page);

    ResponseDTO findAllHouseByPriceBetween(double min, double max, int page);

    ResponseDTO findAllHouseByHouseType(HouseType houseType, int page);

    ResponseDTO findAllHouseByView(ViewType viewType, int page);

    ResponseDTO findAllHouse(int page);

}

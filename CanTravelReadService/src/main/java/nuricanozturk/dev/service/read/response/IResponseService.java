package nuricanozturk.dev.service.read.response;

import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.service.read.dto.AvailableHouseQueryDTO;
import nuricanozturk.dev.service.read.dto.HousesDTO;
import nuricanozturk.dev.service.read.dto.ResponseDTO;

public interface IResponseService
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
    ResponseDTO findAllHouse(int page);
}

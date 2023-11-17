package nuricanozturk.dev.service.read.service;

import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.service.read.dto.HousesDTO;
import nuricanozturk.dev.service.read.mapper.IHouseMapper;
import nuricanozturk.dev.service.read.mapper.ILocationMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;
import static nuricanozturk.dev.service.read.util.StreamUtil.toListConcurrent;

@Service
@Lazy
public class CanTravelReadService
{
    private final CanTravelServiceHelper m_travelServiceHelper;
    private final IHouseMapper m_houseMapper;

    public CanTravelReadService(CanTravelServiceHelper travelServiceHelper, IHouseMapper houseMapper)
    {
        m_travelServiceHelper = travelServiceHelper;
        m_houseMapper = houseMapper;
    }


    public HousesDTO findAllHouse(int page)
    {
        return m_houseMapper.toHousesDTO(toListConcurrent(m_travelServiceHelper.findAllHouse(), m_houseMapper::toHouseDTO));
    }


    public HousesDTO findAllHouseByHouseType(HouseType houseType, int page)
    {
        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(m_travelServiceHelper
                        .findAllHouseByHouseType(houseType, page), m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByHouseType");
    }

    public HousesDTO findAllHouseByPriceBetween(double min, double max, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseByPriceBetween(min, max, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByPriceBetween");
    }

    public HousesDTO findAllHouseByPriceLessThanEqual(double price, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseByPriceLessThanEqual(price, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByPriceLessThanEqual");
    }

    public HousesDTO findAllHouseByPriceGreaterThanEqual(double price, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseByPriceGreaterThanEqual(price, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByPriceGreaterThanEqual");
    }

    public HousesDTO findHouseByHouseName(String homeName, int page)
    {
        var houses = m_travelServiceHelper.findHouseByHouseName(homeName, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findHouseByHouseName");
    }

    public HousesDTO findAllHouseInCity(String city, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseInCity(city, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseInCity");
    }

    public HousesDTO findAllHouseInCountry(String country, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseInCountry(country, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseInCountry");
    }

    public HousesDTO findAllHouseByCountryAndCity(String country, String city, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseByCountryAndCity(country, city, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByCountryAndCity");
    }

    public HousesDTO findAvailableHousesBetweenDates(LocalDate startDate, LocalDate finishDate, int page, int participantCount)
    {
        var houses = m_travelServiceHelper.findAvailableHousesBetweenDates(startDate, finishDate, page, participantCount);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAvailableHousesBetweenDates");
    }
}

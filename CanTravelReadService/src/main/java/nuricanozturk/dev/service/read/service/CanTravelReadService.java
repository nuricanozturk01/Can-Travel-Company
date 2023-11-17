package nuricanozturk.dev.service.read.service;

import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.data.entity.ViewType;
import nuricanozturk.dev.service.read.dto.AvailableHouseQueryDTO;
import nuricanozturk.dev.service.read.dto.HousesDTO;
import nuricanozturk.dev.service.read.mapper.IHouseMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;
import static nuricanozturk.dev.service.read.util.StreamUtil.toListConcurrent;

@Service
@Lazy
public class CanTravelReadService implements ICanTravelReadService
{
    private final CanTravelServiceHelper m_travelServiceHelper;
    private final IHouseMapper m_houseMapper;

    public CanTravelReadService(CanTravelServiceHelper travelServiceHelper, IHouseMapper houseMapper)
    {
        m_travelServiceHelper = travelServiceHelper;
        m_houseMapper = houseMapper;
    }

    @Override
    public HousesDTO findAllHouse(int page)
    {
        return m_houseMapper.toHousesDTO(toListConcurrent(m_travelServiceHelper.findAllHouse(), m_houseMapper::toHouseDTO));
    }

    @Override
    public long getTotalPage()
    {
        return doForDataService(m_travelServiceHelper::getPageSize, "CanTravelServiceHelper::getTotalPage");
    }

    @Override
    public HousesDTO findAllHouseByHouseType(HouseType houseType, int page)
    {
        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(m_travelServiceHelper
                        .findAllHouseByHouseType(houseType, page), m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByHouseType");
    }

    @Override
    public HousesDTO findAllHouseByView(ViewType viewType, int page)
    {
        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(m_travelServiceHelper
                        .findHouseByViewType(viewType, page), m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByView");
    }

    @Override
    public HousesDTO findAllHouseByPriceBetween(double min, double max, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseByPriceBetween(min, max, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByPriceBetween");
    }


    @Override
    public HousesDTO findAllHouseByPriceLessThanEqual(double price, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseByPriceLessThanEqual(price, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByPriceLessThanEqual");
    }

    @Override
    public HousesDTO findAllHouseByPriceGreaterThanEqual(double price, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseByPriceGreaterThanEqual(price, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByPriceGreaterThanEqual");
    }

    @Override
    public HousesDTO findHouseByHouseName(String homeName, int page)
    {
        var houses = m_travelServiceHelper.findHouseByHouseName(homeName, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findHouseByHouseName");
    }

    @Override
    public HousesDTO findAllHouseInCity(String city, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseInCity(city, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseInCity");
    }

    @Override
    public HousesDTO findAllHouseInCountry(String country, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseInCountry(country, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseInCountry");
    }

    @Override
    public HousesDTO findAllHouseByCountryAndCity(String country, String city, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseByCountryAndCity(country, city, page);

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByCountryAndCity");
    }

    @Override
    public HousesDTO findAvailableHousesBetweenDates(AvailableHouseQueryDTO queryDTO)
    {
        var houses = m_travelServiceHelper.findAvailableHousesBetweenDates(queryDTO.startDate(), queryDTO.finishDate(),
                queryDTO.page(), queryDTO.participantCount());

        return doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAvailableHousesBetweenDates");
    }
}

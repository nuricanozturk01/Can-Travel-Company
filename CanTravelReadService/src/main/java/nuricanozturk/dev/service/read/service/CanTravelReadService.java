package nuricanozturk.dev.service.read.service;

import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.data.entity.ViewType;
import nuricanozturk.dev.service.read.dto.AvailableHouseQueryDTO;
import nuricanozturk.dev.service.read.dto.HousesDTO;
import nuricanozturk.dev.service.read.dto.ResponseDTO;
import nuricanozturk.dev.service.read.mapper.IHouseMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;
import static java.lang.String.format;
import static nuricanozturk.dev.service.read.util.Constants.READ_SERVICE_V1;
import static nuricanozturk.dev.service.read.util.StreamUtil.toListConcurrent;

@Service(READ_SERVICE_V1)
@Lazy
@Primary
public class CanTravelReadService implements ICanTravelReadService
{
    protected final CanTravelServiceHelper m_travelServiceHelper;
    protected final IHouseMapper m_houseMapper;

    public CanTravelReadService(CanTravelServiceHelper travelServiceHelper, IHouseMapper houseMapper)
    {
        m_travelServiceHelper = travelServiceHelper;
        m_houseMapper = houseMapper;
    }

    /**
     * Prepare response message.
     *
     * @param houses represent the houses.
     * @param page   represent the page.
     * @return ResponseDTO.
     */
    protected ResponseDTO prepareMessage(HousesDTO houses, int page)
    {
        var totalPage = m_travelServiceHelper.getPageSize();

        var msg = format("%d item found successfully!", houses.houses().size());

        return new ResponseDTO(msg, totalPage, page, houses.houses().size(), true, houses.houses());
    }

    /**
     * Find all house pageable
     *
     * @param page represent the page.
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTO findAllHouse(int page)
    {
        return prepareMessage(m_houseMapper.toHousesDTO(toListConcurrent(m_travelServiceHelper.findAllHousePageable(page),
                m_houseMapper::toHouseDTO)), page);
    }

    /**
     * Find house HouseType.
     *
     * @param houseType represent the houseType. (Enum)
     * @param page      represent the page.
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTO findAllHouseByHouseType(HouseType houseType, int page)
    {
        var housesDTO = doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(m_travelServiceHelper
                        .findAllHouseByHouseType(houseType, page), m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByHouseType");

        return prepareMessage(housesDTO, page);
    }

    /**
     * Find house ViewType.
     *
     * @param viewType represent the viewType. (Enum)
     * @param page     represent the page.
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTO findAllHouseByView(ViewType viewType, int page)
    {
        var housesDTO = doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(m_travelServiceHelper
                        .findHouseByViewType(viewType, page), m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByView");

        return prepareMessage(housesDTO, page);
    }

    /**
     * Find house by price between.
     *
     * @param min  represent the min price.
     * @param max  represent the max price.
     * @param page represent the page.
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTO findAllHouseByPriceBetween(double min, double max, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseByPriceBetween(min, max, page);

        return prepareMessage(doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByPriceBetween"), page);
    }

    /**
     * Find house by price less than equal.
     *
     * @param price represent the price.
     * @param page  represent the page.
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTO findAllHouseByPriceLessThanEqual(double price, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseByPriceLessThanEqual(price, page);

        return prepareMessage(doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByPriceLessThanEqual"), page);
    }

    /**
     * Find house by price greater than equal.
     *
     * @param price represent the price.
     * @param page  represent the page.
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTO findAllHouseByPriceGreaterThanEqual(double price, int page)
    {
        var houses = m_travelServiceHelper.findAllHouseByPriceGreaterThanEqual(price, page);

        return prepareMessage(doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAllHouseByPriceGreaterThanEqual"), page);
    }

    /**
     * Find house by house name.
     *
     * @param homeName represent the house name.
     * @param page     represent the page.
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTO findHouseByHouseName(String homeName, int page)
    {
        var houses = m_travelServiceHelper.findHouseByHouseName(homeName, page);

        return prepareMessage(doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findHouseByHouseName"), page);
    }

    /**
     * Find house by city.
     *
     * @param city city name.
     * @param page represent the page.
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTO findAllHouseInCity(String city, int page)
    {
        var housesDTO = doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(m_travelServiceHelper
                .findAllHouseInCity(city, page), m_houseMapper::toHouseDTO)), "CanTravelReadService::findAllHouseInCity");

        return prepareMessage(housesDTO, page);
    }

    /**
     * Find house by country.
     *
     * @param country represent the country.
     * @param page    represent the page.
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTO findAllHouseInCountry(String country, int page)
    {
        var housesDTO = doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(m_travelServiceHelper
                .findAllHouseInCountry(country, page), m_houseMapper::toHouseDTO)), "CanTravelReadService::findAllHouseInCountry");

        return prepareMessage(housesDTO, page);
    }

    /**
     * Find house by country and city.
     *
     * @param country represent the country.
     * @param city    represent the city.
     * @param page    represent the page.
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTO findAllHouseByCountryAndCity(String country, String city, int page)
    {
        var housesDTO = doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(m_travelServiceHelper
                .findAllHouseByCountryAndCity(country, city, page), m_houseMapper::toHouseDTO)), "CanTravelReadService::findAllHouseByCountryAndCity");

        return prepareMessage(housesDTO, page);
    }

    /**
     * Find available houses with given dates and participant count.
     *
     * @param queryDTO represent the queryDTO.
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTO findAvailableHousesBetweenDates(AvailableHouseQueryDTO queryDTO)
    {
        var houses = m_travelServiceHelper.findAvailableHousesBetweenDates(queryDTO.startDate(), queryDTO.finishDate(),
                queryDTO.page(), queryDTO.participantCount());

        var housesDTO = doForDataService(() -> m_houseMapper.toHousesDTO(toListConcurrent(houses, m_houseMapper::toHouseDTO)),
                "CanTravelReadService::findAvailableHousesBetweenDates");

        return prepareMessage(housesDTO, queryDTO.page());
    }
}

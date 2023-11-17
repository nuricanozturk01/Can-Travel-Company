package nuricanozturk.dev.service.read.response;

import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.service.read.dto.AvailableHouseQueryDTO;
import nuricanozturk.dev.service.read.dto.HousesDTO;
import nuricanozturk.dev.service.read.dto.ResponseDTO;
import nuricanozturk.dev.service.read.service.ICanTravelReadService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Lazy
public class ResponseService implements IResponseService
{
    private final ICanTravelReadService m_travelReadService;

    public ResponseService(ICanTravelReadService travelReadService)
    {
        m_travelReadService = travelReadService;
    }

    public ResponseDTO prepareMessage(HousesDTO houses, int page)
    {
        var totalPage = m_travelReadService.getTotalPage();

        var msg = format("%d item found successfully!", houses.houses().size());

        return new ResponseDTO(msg, totalPage, page, houses.houses().size(), true, houses.houses());
    }

    @Override
    public ResponseDTO findAvailableHousesBetweenDates(AvailableHouseQueryDTO queryDTO)
    {
        return prepareMessage(m_travelReadService.findAvailableHousesBetweenDates(queryDTO), queryDTO.page());
    }

    @Override
    public ResponseDTO findAllHouseByCountryAndCity(String country, String city, int page)
    {
        return prepareMessage(m_travelReadService.findAllHouseByCountryAndCity(country, city, page), page);
    }

    @Override
    public ResponseDTO findAllHouseInCountry(String country, int page)
    {
        return prepareMessage(m_travelReadService.findAllHouseInCountry(country, page), page);
    }

    @Override
    public ResponseDTO findAllHouseInCity(String city, int page)
    {
        return prepareMessage(m_travelReadService.findAllHouseInCity(city, page), page);
    }

    @Override
    public ResponseDTO findHouseByHouseName(String homeName, int page)
    {
        return prepareMessage(m_travelReadService.findHouseByHouseName(homeName, page), page);
    }

    @Override
    public ResponseDTO findAllHouseByPriceGreaterThanEqual(double price, int page)
    {
        return prepareMessage(m_travelReadService.findAllHouseByPriceGreaterThanEqual(price, page), page);
    }

    @Override
    public ResponseDTO findAllHouseByPriceLessThanEqual(double price, int page)
    {
        return prepareMessage(m_travelReadService.findAllHouseByPriceLessThanEqual(price, page), page);
    }

    @Override
    public ResponseDTO findAllHouseByPriceBetween(double min, double max, int page)
    {
        return prepareMessage(m_travelReadService.findAllHouseByPriceBetween(min, max, page), page);
    }

    @Override
    public ResponseDTO findAllHouseByHouseType(HouseType houseType, int page)
    {
        return prepareMessage(m_travelReadService.findAllHouseByHouseType(houseType, page), page);
    }

    @Override
    public ResponseDTO findAllHouse(int page)
    {
        return prepareMessage(m_travelReadService.findAllHouse(page), page);
    }
}

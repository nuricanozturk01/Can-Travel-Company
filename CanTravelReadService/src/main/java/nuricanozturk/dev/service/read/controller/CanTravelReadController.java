package nuricanozturk.dev.service.read.controller;

import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.data.entity.ViewType;
import nuricanozturk.dev.service.read.dto.AvailableHouseQueryDTO;
import nuricanozturk.dev.service.read.service.ICanTravelReadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static callofproject.dev.library.exception.util.ExceptionUtil.subscribe;
import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/read")
public class CanTravelReadController
{
    private final ICanTravelReadService m_travelReadService;

    public CanTravelReadController(ICanTravelReadService travelReadService)
    {
        m_travelReadService = travelReadService;
    }


    /**
     * Find all house.
     *
     * @param page represent the page.
     * @return ResponseDTO.
     */
    @GetMapping("houses")
    public ResponseEntity<Object> findAllHouse(@RequestParam("p") int page)
    {

        return subscribe(() -> ok(m_travelReadService.findAllHouse(page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    /**
     * Find all house by house type.
     *
     * @param houseType represent the house type.
     * @param page      represent the page.
     * @return ResponseDTO.
     */
    @GetMapping("find/house/type")
    public ResponseEntity<Object> findAllHouseByHouseType(@RequestParam("type") HouseType houseType,
                                                          @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_travelReadService.findAllHouseByHouseType(houseType, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    /**
     * Find all house by view.
     *
     * @param view represent the view.
     * @param page represent the page.
     * @return ResponseDTO.
     */
    @GetMapping("find/house/view")
    public ResponseEntity<Object> findAllHouseByHouseView(@RequestParam("view") ViewType view,
                                                          @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_travelReadService.findAllHouseByView(view, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    /**
     * Find all house by price between.
     *
     * @param min  represent the min price.
     * @param max  represent the max price.
     * @param page represent the page.
     * @return ResponseDTO.
     */
    @GetMapping("find/house/price/between")
    public ResponseEntity<Object> findAllHouseByPriceBetween(@RequestParam("min") double min,
                                                             @RequestParam("max") double max,
                                                             @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_travelReadService.findAllHouseByPriceBetween(min, max, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    /**
     * Find all house by price max.
     *
     * @param price represent the price.
     * @param page  represent the page.
     * @return ResponseDTO.
     */
    @GetMapping("find/house/price/max")
    public ResponseEntity<Object> findAllHouseByPriceLessThanEqual(@RequestParam("price") double price,
                                                                   @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_travelReadService.findAllHouseByPriceLessThanEqual(price, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    /**
     * Find all house by price min.
     *
     * @param price represent the price.
     * @param page  represent the page.
     * @return ResponseDTO.
     */
    @GetMapping("/find/house/price/min")
    public ResponseEntity<Object> findAllHouseByPriceGreaterThanEqual(@RequestParam("price") double price,
                                                                      @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_travelReadService.findAllHouseByPriceGreaterThanEqual(price, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    /**
     * Find house by house name.
     *
     * @param homeName represent the house name.
     * @param page     represent the page.
     * @return ResponseDTO.
     */
    @GetMapping("find/house/name")
    public ResponseEntity<Object> findHouseByHouseName(@RequestParam("n") String homeName, @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_travelReadService.findHouseByHouseName(homeName, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    /**
     * Find all houses by city.
     *
     * @param city represent the city.
     * @param page represent the page.
     * @return ResponseDTO.
     */
    @GetMapping("find/house/city")
    public ResponseEntity<Object> findAllHouseInCity(@RequestParam("city") String city, @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_travelReadService.findAllHouseInCity(city, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    /**
     * Find all houses by country.
     *
     * @param country represent the country.
     * @param page    represent the page.
     * @return ResponseDTO.
     */
    @GetMapping("find/house/country")
    public ResponseEntity<Object> findAllHouseInCountry(@RequestParam("country") String country,
                                                        @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_travelReadService.findAllHouseInCountry(country, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    /**
     * Find all houses by country and city.
     *
     * @param country represent the country.
     * @param city    represent the city.
     * @param page    represent the page.
     * @return ResponseDTO.
     */
    @GetMapping("/find/house/country-city")
    public ResponseEntity<Object> findAllHouseByCountryAndCity(@RequestParam("country") String country,
                                                               @RequestParam("city") String city,
                                                               @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_travelReadService.findAllHouseByCountryAndCity(country, city, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    /**
     * Find available houses between dates.
     *
     * @param queryDTO represent the queryDTO.
     * @return ResponseDTO.
     */
    @PostMapping("find/house/date/between")
    public ResponseEntity<Object> findAvailableHousesBetweenDates(@RequestBody AvailableHouseQueryDTO queryDTO)
    {
        return subscribe(() -> ok(m_travelReadService.findAvailableHousesBetweenDates(queryDTO)),
                msg -> internalServerError().body(msg.getMessage()));
    }
}

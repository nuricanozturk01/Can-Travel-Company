package nuricanozturk.dev.service.read.controller;

import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.data.entity.ViewType;
import nuricanozturk.dev.service.read.dto.AvailableHouseQueryDTO;
import nuricanozturk.dev.service.read.response.IResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static callofproject.dev.library.exception.util.ExceptionUtil.subscribe;
import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/read")
public class CanTravelReadController
{
    private final IResponseService m_responseService;

    public CanTravelReadController(IResponseService responseService)
    {
        m_responseService = responseService;
    }


    @GetMapping("houses")
    public ResponseEntity<Object> findAllHouse(@RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_responseService.findAllHouse(page)), msg -> internalServerError().body(msg.getMessage()));
    }

    @GetMapping("find/house/type")
    public ResponseEntity<Object> findAllHouseByHouseType(@RequestParam("type") HouseType houseType, @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_responseService.findAllHouseByHouseType(houseType, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    @GetMapping("find/house/view")
    public ResponseEntity<Object> findAllHouseByHouseView(@RequestParam("view") ViewType view, @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_responseService.findAllHouseByView(view, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }


    @GetMapping("find/house/price/between")
    public ResponseEntity<Object> findAllHouseByPriceBetween(@RequestParam("min") double min, @RequestParam("max") double max, @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_responseService.findAllHouseByPriceBetween(min, max, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    @GetMapping("find/house/price/max")
    public ResponseEntity<Object> findAllHouseByPriceLessThanEqual(@RequestParam("price") double price, @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_responseService.findAllHouseByPriceLessThanEqual(price, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    @GetMapping("/find/house/price/min")
    public ResponseEntity<Object> findAllHouseByPriceGreaterThanEqual(@RequestParam("price") double price, @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_responseService.findAllHouseByPriceGreaterThanEqual(price, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    @GetMapping("find/house/name")
    public ResponseEntity<Object> findHouseByHouseName(@RequestParam("n") String homeName, @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_responseService.findHouseByHouseName(homeName, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    @GetMapping("find/house/city")
    public ResponseEntity<Object> findAllHouseInCity(@RequestParam("city") String city, @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_responseService.findAllHouseInCity(city, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    @GetMapping("find/house/country")
    public ResponseEntity<Object> findAllHouseInCountry(@RequestParam("country") String country, @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_responseService.findAllHouseInCountry(country, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    @GetMapping("/find/house/country-city")
    public ResponseEntity<Object> findAllHouseByCountryAndCity(@RequestParam("country") String country, @RequestParam("city") String city, @RequestParam("p") int page)
    {
        return subscribe(() -> ok(m_responseService.findAllHouseByCountryAndCity(country, city, page)),
                msg -> internalServerError().body(msg.getMessage()));
    }

    @GetMapping("find/house/date/between")
    public ResponseEntity<Object> findAvailableHousesBetweenDates(@RequestBody AvailableHouseQueryDTO queryDTO)
    {
        return subscribe(() -> ok(m_responseService.findAvailableHousesBetweenDates(queryDTO)),
                msg -> internalServerError().body(msg.getMessage()));
    }
}

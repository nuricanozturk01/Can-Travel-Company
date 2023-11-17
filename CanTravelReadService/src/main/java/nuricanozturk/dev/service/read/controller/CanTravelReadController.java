package nuricanozturk.dev.service.read.controller;

import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.service.read.dto.HousesDTO;
import nuricanozturk.dev.service.read.service.CanTravelReadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/read")
public class CanTravelReadController
{
    private final CanTravelReadService canTravelReadService;

    public CanTravelReadController(CanTravelReadService canTravelReadService)
    {
        this.canTravelReadService = canTravelReadService;
    }

    @GetMapping("/houses")
    public HousesDTO findAllHouse(@RequestParam("p") int page)
    {
        return canTravelReadService.findAllHouse(page);
    }

    @GetMapping("/housesByHouseType")
    public HousesDTO findAllHouseByHouseType(@RequestParam("type") HouseType houseType, @RequestParam("p") int page)
    {
        return canTravelReadService.findAllHouseByHouseType(houseType, page);
    }

    @GetMapping("/housesByPriceBetween")
    public HousesDTO findAllHouseByPriceBetween(@RequestParam("min") double min, @RequestParam("max") double max, @RequestParam("p") int page)
    {
        return canTravelReadService.findAllHouseByPriceBetween(min, max, page);
    }

    @GetMapping("/housesByPriceLessThanEqual")
    public HousesDTO findAllHouseByPriceLessThanEqual(@RequestParam("price") double price, @RequestParam("p") int page)
    {
        return canTravelReadService.findAllHouseByPriceLessThanEqual(price, page);
    }

    @GetMapping("/housesByPriceGreaterThanEqual")
    public HousesDTO findAllHouseByPriceGreaterThanEqual(@RequestParam("price") double price, @RequestParam("p") int page)
    {
        return canTravelReadService.findAllHouseByPriceGreaterThanEqual(price, page);
    }

    @GetMapping("/houseByHouseName")
    public HousesDTO findHouseByHouseName(@RequestParam("name") String homeName, @RequestParam("p") int page)
    {
        return canTravelReadService.findHouseByHouseName(homeName, page);
    }

    @GetMapping("/housesInCity")
    public HousesDTO findAllHouseInCity(@RequestParam("city") String city, @RequestParam("p") int page)
    {
        return canTravelReadService.findAllHouseInCity(city, page);
    }

    @GetMapping("/housesInCountry")
    public HousesDTO findAllHouseInCountry(@RequestParam("country") String country, @RequestParam("p") int page)
    {
        return canTravelReadService.findAllHouseInCountry(country, page);
    }

    @GetMapping("/housesByCountryAndCity")
    public HousesDTO findAllHouseByCountryAndCity(@RequestParam("country") String country, @RequestParam("city") String city, @RequestParam("p") int page)
    {
        return canTravelReadService.findAllHouseByCountryAndCity(country, city, page);
    }

    @GetMapping("/availableHousesBetweenDates")
    public HousesDTO findAvailableHousesBetweenDates(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("finishDate") LocalDate finishDate,
            @RequestParam("page") int page,
            @RequestParam("participantCount") int participantCount)
    {
        return canTravelReadService.findAvailableHousesBetweenDates(startDate, finishDate, page, participantCount);
    }
}

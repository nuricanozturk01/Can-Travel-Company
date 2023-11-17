package nuricanozturk.dev.service.read.controller;

import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.service.read.service.CanTravelReadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/read")
public class CanTravelReadController
{
    private final CanTravelReadService m_readService;

    public CanTravelReadController(CanTravelReadService readService)
    {
        m_readService = readService;
    }

    @GetMapping("find")
    public Iterable<House> findAllHouse(@RequestParam int page)
    {
        return m_readService.findAllHouse(page);
    }
}

package nuricanozturk.dev.service.read.controller;

import nuricanozturk.dev.service.read.service.ICanTravelReadService;
import nuricanozturk.dev.service.read.service.ICanTravelReadServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static callofproject.dev.library.exception.util.ExceptionUtil.subscribe;
import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v2/read")
public class CanTravelReadControllerV2 extends CanTravelReadController
{
    private final ICanTravelReadServiceV2 m_travelReadServiceV2;

    public CanTravelReadControllerV2(ICanTravelReadService travelReadService, ICanTravelReadServiceV2 travelReadServiceV2)
    {
        super(travelReadService);
        m_travelReadServiceV2 = travelReadServiceV2;
    }

    @Override
    @Deprecated
    public ResponseEntity<Object> findAllHouse(int page)
    {
        return super.findAllHouse(page);
    }

    /**
     * Find all house.
     *
     * @return ResponseDTO.
     */
    @GetMapping("find/all/house")
    public ResponseEntity<Object> findAllHouse()
    {
        return subscribe(() -> ok(m_travelReadServiceV2.findAllHouse()), msg -> internalServerError().body(msg.getMessage()));
    }

}

package nuricanozturk.dev.service.booking.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import nuricanozturk.dev.service.booking.dto.HouseSaveDTO;
import nuricanozturk.dev.service.booking.dto.ResponseDTO;
import nuricanozturk.dev.service.booking.service.IAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static callofproject.dev.library.exception.util.ExceptionUtil.subscribe;
import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/admin")
@SecurityRequirement(name = "Authorization")
public class CanTravelAdminController
{

    private final IAdminService m_adminService;

    public CanTravelAdminController(IAdminService adminService)
    {
        m_adminService = adminService;
    }


    /**
     * Find all houses
     *
     * @return if success HouseDTO else return Error Message
     */
    @GetMapping("/find/all/house")
    public ResponseEntity<Object> findAllHouses()
    {
        return subscribe(() -> ok(m_adminService.findAllHouse()),
                ex -> internalServerError().body(new ResponseDTO(ex.getMessage(), false, null)));
    }

  /*  @Deprecated(since = "19/11/2023", forRemoval = true)
    @Tag(name = "Cannot use find all customers now!", description = "Do not use!")
    @GetMapping("/find/all/customers")
    public ResponseEntity<Object> findAllCustomers()
    {
        return subscribe(() -> ok(m_adminService.findAllCustomers()),
                ex -> internalServerError().body(new ResponseDTO(ex.getMessage(), false, null)));
    }*/

    /**
     * Find all locations
     *
     * @return if success LocationDTO else return Error Message
     */
    @GetMapping("/find/all/locations")
    public ResponseEntity<Object> findAllLocations()
    {
        return subscribe(() -> ok(m_adminService.findAllLocations()),
                ex -> internalServerError().body(new ResponseDTO(ex.getMessage(), false, null)));
    }

    /*@Deprecated(since = "19/11/2023", forRemoval = true)
    @GetMapping("/find/all/reservations")
    @Tag(name = "Cannot use all reservations now!", description = "Do not use!")
    public ResponseEntity<Object> findAllReservations()
    {
        return subscribe(() -> ok(m_adminService.findAllReservations()),
                ex -> internalServerError().body(new ResponseDTO(ex.getMessage(), false, null)));
    }*/

    /**
     * Save house
     *
     * @return if success HouseDTO else return Error Message
     */
    @PostMapping("/save/house")
    public ResponseEntity<Object> saveHouse(@RequestBody HouseSaveDTO houseSaveDTO)
    {
        return subscribe(() -> ok(m_adminService.saveHouse(houseSaveDTO)),
                ex -> internalServerError().body(new ResponseDTO(ex.getMessage(), false, null)));
    }
}

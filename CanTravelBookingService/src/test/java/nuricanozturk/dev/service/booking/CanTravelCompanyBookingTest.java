package nuricanozturk.dev.service.booking;

import callofproject.dev.library.exception.service.DataServiceException;
import callofproject.dev.util.stream.StreamUtil;
import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.service.booking.dto.BookingResponseDTO;
import nuricanozturk.dev.service.booking.dto.BookingSaveDTO;
import nuricanozturk.dev.service.booking.dto.LoginDTO;
import nuricanozturk.dev.service.booking.service.IAuthenticationService;
import nuricanozturk.dev.service.booking.service.v1.ICanTravelBookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Random;

import static nuricanozturk.dev.data.util.BeanName.READ_REPO_ENTITIY_PACKAGE_NAME;
import static nuricanozturk.dev.data.util.BeanName.READ_REPO_PACKAGE_NAME;
import static nuricanozturk.dev.service.booking.util.Constants.BOOKING_SERVICE_BEAN_NAME;
import static nuricanozturk.dev.service.booking.util.Constants.TEST_PROPERTIES_FILE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = {READ_REPO_PACKAGE_NAME, BOOKING_SERVICE_BEAN_NAME})
@EntityScan(basePackages = {READ_REPO_ENTITIY_PACKAGE_NAME, BOOKING_SERVICE_BEAN_NAME})
@TestPropertySource(locations = TEST_PROPERTIES_FILE)
public class CanTravelCompanyBookingTest
{
    @Autowired
    private ICanTravelBookingService m_bookingService;

    @Autowired
    private CanTravelServiceHelper m_travelServiceHelper;

    @Autowired
    private IAuthenticationService m_authenticationService;
    private static String TOKEN;

    @Autowired
    private Random m_random;

    @BeforeEach
    public void provideToken()
    {
        TOKEN = m_authenticationService.login(new LoginDTO("nuricanozturk", "pass123")).token();
    }

    @Test
    public void testBooking_withGivenValidData_shouldReturnTrue()
    {
        var year = m_random.nextInt(2024, 2800);
        var start = LocalDate.of(year, 3, 24);
        var end = LocalDate.of(year, m_random.nextInt(4, 11), 24);

        var house = (House) StreamUtil.toList(m_travelServiceHelper.findHouseByHouseName("House-1", 1)).get(0);

        var dto = new BookingSaveDTO(house.getHouseId().toString(), "nuricanozturk", 1, start, end);

        var reservationResult = m_bookingService.saveReservation(dto, TOKEN);
        assertNotNull(reservationResult);
        assertTrue(reservationResult.success());

        var object = (BookingResponseDTO) reservationResult.object();
        assertNotNull(object);
        assertEquals(start, object.startDate());
        assertEquals(end, object.finishDate());
        assertEquals("House-1", object.houseName());
    }

    @Test
    public void testBooking_withGivenInValidDates_shouldReturnFalse()
    {
        var start = LocalDate.of(2023, 11, 17);
        var end = LocalDate.of(2023, 11, 23);
        var house = (House) StreamUtil.toList(m_travelServiceHelper.findHouseByHouseName("House-1", 1)).get(0);

        var dto = new BookingSaveDTO(house.getHouseId().toString(), "nuricanozturk", 1, start, end);

        assertThrows(DataServiceException.class, () -> m_bookingService.saveReservation(dto, TOKEN),
                "CanTravelBookingService::saveReservation , Cause Message:Message: House is not available between dates!");
    }


    @Test
    public void testBooking_withGivenInValidParticipantCount_shouldReturnFalse()
    {
        var start = LocalDate.of(2024, 5, 10);
        var finish = LocalDate.of(2024, 5, 15);
        var house = (House) StreamUtil.toList(m_travelServiceHelper.findHouseByHouseName("House-1", 1)).get(0);

        var dto = new BookingSaveDTO(house.getHouseId().toString(), "nuricanozturk", 165, start, finish);

        assertThrows(DataServiceException.class, () -> m_bookingService.saveReservation(dto, TOKEN),
                "CanTravelBookingService::saveReservation , Cause Message:Message: Max participant count is: 3");
    }


    @Test
    public void testBooking_withGivenInValidUsername_shouldReturnFalse()
    {
        var start = LocalDate.of(2024, 5, 10);
        var finish = LocalDate.of(2024, 5, 15);
        var house = (House) StreamUtil.toList(m_travelServiceHelper.findHouseByHouseName("House-1", 1)).get(0);

        var dto = new BookingSaveDTO(house.getHouseId().toString(), "owowochukwuemeka", 2, start, finish);

        assertThrows(DataServiceException.class, () -> m_bookingService.saveReservation(dto, TOKEN),
                "Message: CanTravelBookingService::saveReservation , Cause Message:Message: Something wrong in server!");
    }

    //....
}

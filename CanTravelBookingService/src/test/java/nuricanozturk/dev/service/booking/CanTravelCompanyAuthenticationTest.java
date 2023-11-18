package nuricanozturk.dev.service.booking;

import callofproject.dev.library.exception.service.DataServiceException;
import nuricanozturk.dev.service.booking.dto.LoginDTO;
import nuricanozturk.dev.service.booking.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import static nuricanozturk.dev.data.util.BeanName.READ_REPO_ENTITIY_PACKAGE_NAME;
import static nuricanozturk.dev.data.util.BeanName.READ_REPO_PACKAGE_NAME;
import static nuricanozturk.dev.service.booking.util.Constants.BOOKING_SERVICE_BEAN_NAME;
import static nuricanozturk.dev.service.booking.util.Constants.TEST_PROPERTIES_FILE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = {READ_REPO_PACKAGE_NAME, BOOKING_SERVICE_BEAN_NAME})
@EntityScan(basePackages = {READ_REPO_ENTITIY_PACKAGE_NAME, BOOKING_SERVICE_BEAN_NAME})
@TestPropertySource(locations = TEST_PROPERTIES_FILE)
public class CanTravelCompanyAuthenticationTest
{
    @Autowired
    private AuthenticationService m_authenticationService;


    @Test
    public void testLoginOperation_withGivenUsernameAndPassword_shouldReturnTrue()
    {
        var username = "nuricanozturk";
        var passwd = "pass123";

        var auth = m_authenticationService.login(new LoginDTO(username, passwd));

        assertTrue(auth.status());
        assertNotNull(auth.token());
    }


    @Test
    public void testLoginOperation_withGivenUsernameAndPassword_shouldThrowDataServiceException()
    {
        var username = "nuricanozturkfdsfsd";
        var passwd = "pass12fdssd3";

        assertThrows(DataServiceException.class, () -> m_authenticationService.login(new LoginDTO(username, passwd)),
                "Message: AuthenticationService::login , Cause Message:No user registered with this details!");
    }


    @Test
    public void testLoginOperation_withGivenUsernameAndInvalidPassword_shouldReturnFalse()
    {
        assertThrows(DataServiceException.class, () -> m_authenticationService.login(new LoginDTO("nuricanozturk", "pass1fsd23")),
                "Message: AuthenticationService::login , Cause Message:Invalid password!");
    }

    //....
}

package nuricanozturk.dev.service.booking;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.data.entity.Customer;
import nuricanozturk.dev.data.entity.Role;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static nuricanozturk.dev.data.util.BeanName.READ_REPO_ENTITIY_PACKAGE_NAME;
import static nuricanozturk.dev.data.util.BeanName.READ_REPO_PACKAGE_NAME;
import static nuricanozturk.dev.service.booking.util.Constants.BOOKING_SERVICE_BEAN_NAME;

@SpringBootApplication
@ComponentScan(basePackages = {READ_REPO_PACKAGE_NAME, BOOKING_SERVICE_BEAN_NAME})
@EnableJpaRepositories({READ_REPO_PACKAGE_NAME})
@EntityScan(basePackages = {READ_REPO_ENTITIY_PACKAGE_NAME, BOOKING_SERVICE_BEAN_NAME})
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class CanTravelBookingServiceApplication implements ApplicationRunner
{
    private final CanTravelServiceHelper m_travelServiceHelper;
    private final PasswordEncoder m_passwordEncoder;

    public CanTravelBookingServiceApplication(CanTravelServiceHelper travelServiceHelper, PasswordEncoder passwordEncoder)
    {
        m_travelServiceHelper = travelServiceHelper;
        m_passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(CanTravelBookingServiceApplication.class, args);
    }

    /**
     * Run method for ApplicationRunner.
     * Save admin user if not exists.
     *
     * @param args represent the ApplicationArguments
     * @throws Exception if any exception occurs
     */
    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        if (m_travelServiceHelper.findCustomerByUsername("travel_admin").isEmpty())
        {
            var admin = new Customer("travel_admin", m_passwordEncoder.encode("admin?pass6055!"),
                    "ADMIN", "ADMIN", "ADMIN", "nuricanozturk01@gmail.com", Role.ROLE_ADMIN);

            m_travelServiceHelper.saveCustomer(admin);
        }
    }
}
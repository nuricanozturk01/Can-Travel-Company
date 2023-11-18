package nuricanozturk.dev.service.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static nuricanozturk.dev.data.util.BeanName.READ_REPO_ENTITIY_PACKAGE_NAME;
import static nuricanozturk.dev.data.util.BeanName.READ_REPO_PACKAGE_NAME;
import static nuricanozturk.dev.service.booking.util.Constants.BOOKING_SERVICE_BEAN_NAME;

@SpringBootApplication
@ComponentScan(basePackages = {READ_REPO_PACKAGE_NAME, BOOKING_SERVICE_BEAN_NAME})
@EnableJpaRepositories({READ_REPO_PACKAGE_NAME})
@EntityScan(basePackages = {READ_REPO_ENTITIY_PACKAGE_NAME, BOOKING_SERVICE_BEAN_NAME})
public class CanTravelBookingServiceApplication
{
    @Autowired
    private PasswordEncoder m_passwordEncoder;

    public static void main(String[] args)
    {
        SpringApplication.run(CanTravelBookingServiceApplication.class, args);
    }

}

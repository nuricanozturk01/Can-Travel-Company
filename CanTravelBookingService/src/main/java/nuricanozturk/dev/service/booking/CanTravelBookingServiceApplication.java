package nuricanozturk.dev.service.booking;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

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
public class CanTravelBookingServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(CanTravelBookingServiceApplication.class, args);
    }
}
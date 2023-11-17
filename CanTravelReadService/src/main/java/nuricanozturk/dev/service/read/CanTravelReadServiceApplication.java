package nuricanozturk.dev.service.read;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static nuricanozturk.dev.data.util.BeanName.READ_REPO_ENTITIY_PACKAGE_NAME;
import static nuricanozturk.dev.data.util.BeanName.READ_REPO_PACKAGE_NAME;
import static nuricanozturk.dev.service.read.util.Constants.READ_SERVICE_PACKAGE_NAME;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {READ_REPO_PACKAGE_NAME})
@EntityScan(basePackages = READ_REPO_ENTITIY_PACKAGE_NAME)
@ComponentScan(basePackages = {READ_SERVICE_PACKAGE_NAME, READ_REPO_PACKAGE_NAME})
public class CanTravelReadServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(CanTravelReadServiceApplication.class, args);
    }
}

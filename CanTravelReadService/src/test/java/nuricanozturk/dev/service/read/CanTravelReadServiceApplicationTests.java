package nuricanozturk.dev.service.read;

import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.data.entity.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static java.time.LocalDate.of;
import static nuricanozturk.dev.data.util.BeanName.READ_REPO_ENTITIY_PACKAGE_NAME;
import static nuricanozturk.dev.data.util.BeanName.READ_REPO_PACKAGE_NAME;
import static nuricanozturk.dev.service.read.util.Constants.READ_SERVICE_PACKAGE_NAME;
import static nuricanozturk.dev.service.read.util.Constants.TEST_PROPERTIES_FILE;

@Disabled
@SpringBootTest
@EntityScan(basePackages = READ_REPO_ENTITIY_PACKAGE_NAME)
@ComponentScan(basePackages = {READ_SERVICE_PACKAGE_NAME, READ_REPO_PACKAGE_NAME})
@TestPropertySource(locations = TEST_PROPERTIES_FILE)
class CanTravelReadServiceApplicationTests
{
    @Autowired
    private CanTravelServiceHelper m_travelServiceHelper;
    @Test
    void contextLoads()
    {
        var customer1 = new Customer("nuricanozturk", "$2a$10$YTGH7.yZzzbSCeCafM1ChOkpb/0Gqv5JQ.usM/5ZgbaKi7.ezTW42", "Nuri", "Can", "ÖZTÜRK", "canozturk309@gmail.com");
        var customer2 = new Customer("halilcanozturk", "$2a$10$YTGH7.yZzzbSCeCafM1ChOkpb/0Gqv5JQ.usM/5ZgbaKi7.ezTW42", "Halil", "Can", "OZTURK", "nuricanozturk02@gmail.com");
        m_travelServiceHelper.saveAllCustomers(List.of(customer1, customer2));

        var location1 = new Location("Istanbul", "Turkey");
        var location2 = new Location("New York", "USA");

        // Houses
        var house1 = new House.Builder()
                .setHouseName("House-1")
                .setHousePhoto("house_photo_1")
                .setDescription("Description - 1")
                .setPrice(150D)
                .setMaxParticipantCount(3)
                .setLocation(location1)
                .setView(ViewType.FOREST)
                .setHouseType(HouseType.APARTMENT)
                .build();

        var house2 = new House.Builder()
                .setHouseName("House-2")
                .setHousePhoto("house_photo_2")
                .setDescription("Description - 2")
                .setPrice(400D)
                .setMaxParticipantCount(10)
                .setLocation(location2)
                .setView(ViewType.FOREST)
                .setHouseType(HouseType.TRIPLEX)
                .build();

        var house3 = new House.Builder()
                .setHouseName("House-3")
                .setHousePhoto("house_photo_3")
                .setDescription("Description - 3")
                .setPrice(200D)
                .setMaxParticipantCount(5)
                .setLocation(location1)
                .setView(ViewType.SEA)
                .setHouseType(HouseType.DUPLEX)
                .build();

        m_travelServiceHelper.saveAllHouses(List.of(house1, house2, house3));

        // Reservations

        // start: 17/11/2023 - finish: 23/11/2023
        var reservation1 = new Reservation(house1, customer1, of(2023, 11, 17), of(2023, 11, 23));

        // start: 01/12/2023 - finish: 10/12/2023
        var reservation2 = new Reservation(house2, customer1, of(2023, 12, 1), of(2023, 12, 10));

        // start: 24/11/2023 - finish: 30/11/2023
        var reservation3 = new Reservation(house3, customer2, of(2023, 11, 24), of(2023, 11, 30));

        m_travelServiceHelper.saveAllReservations(List.of(reservation1, reservation2, reservation3));
    }

}

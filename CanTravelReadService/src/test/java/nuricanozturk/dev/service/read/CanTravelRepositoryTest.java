package nuricanozturk.dev.service.read;

import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.data.entity.ViewType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static java.time.LocalDate.of;
import static java.util.stream.StreamSupport.stream;
import static nuricanozturk.dev.data.util.BeanName.READ_REPO_ENTITIY_PACKAGE_NAME;
import static nuricanozturk.dev.data.util.BeanName.READ_REPO_PACKAGE_NAME;
import static nuricanozturk.dev.service.read.util.Constants.READ_SERVICE_PACKAGE_NAME;
import static nuricanozturk.dev.service.read.util.Constants.TEST_PROPERTIES_FILE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EntityScan(basePackages = READ_REPO_ENTITIY_PACKAGE_NAME)
@ComponentScan(basePackages = {READ_SERVICE_PACKAGE_NAME, READ_REPO_PACKAGE_NAME})
@TestPropertySource(locations = TEST_PROPERTIES_FILE)
public class CanTravelRepositoryTest
{
    @Autowired
    private CanTravelServiceHelper m_travelServiceHelper;

    private <T> List<T> toList(Iterable<T> iterable)
    {
        return stream(iterable.spliterator(), false).toList();
    }

    @Test
    public void shouldRetrieveAllEntitiesSuccessfully()
    {
        assertEquals(3, m_travelServiceHelper.getHouseCount());
        assertEquals(3, m_travelServiceHelper.getReservationCount());
        assertEquals(2, m_travelServiceHelper.getLocationCount());
        assertEquals(2, m_travelServiceHelper.getCustomerCount());
    }

    @Test
    public void checkReservations_withGivenDate_shouldReturnsEqualsWithHouse1()
    {
        // house1: 17/11/2023 - 23/11/2023
        var startDate = of(2023, 11, 25); // 25/11/2023
        var finishDate = of(2023, 12, 5); // 05/12/2023

        // expected available houses: house1
        var availableHouses = toList(m_travelServiceHelper.findAvailableHousesBetweenDates(startDate, finishDate, 1, 0));

        assertNotNull(availableHouses);
        assertEquals(1, availableHouses.size());

        var house1 = toList(m_travelServiceHelper.findHouseByHouseName("House-1", 1));

        assertNotNull(house1);
        assertEquals(1, house1.size());
        assertEquals(availableHouses.get(0), house1.get(0));
    }


    @Test
    public void checkReservations_withGivenDate_shouldReturnsTrue()
    {
        // expected available houses: house1, house2, house3
        var availableHouses = toList(m_travelServiceHelper
                .findAvailableHousesBetweenDates(of(2025, 11, 25), of(2029, 12, 5), 1, 1));

        assertNotNull(availableHouses);

        assertEquals(3, availableHouses.size());

        var houseNames = availableHouses.stream().map(House::getHouseName).toList();

        Assertions.assertTrue(houseNames.contains("House-1"));
        Assertions.assertTrue(houseNames.contains("House-2"));
        Assertions.assertTrue(houseNames.contains("House-3"));
    }


    @Test
    public void checkReservations_withGivenDates_shouldReturnsEmptyList()
    {
        // expected available houses: EMPTY LIST 18/11/2023 - 05/12/203
        var availableHouses = toList(m_travelServiceHelper
                .findAvailableHousesBetweenDates(of(2023, 11, 18), of(2023, 12, 5), 1, 1));

        // expected available houses: house1 - house3
        var availableHouses2 = toList(m_travelServiceHelper
                .findAvailableHousesBetweenDates(of(2023, 12, 1), of(2023, 12, 10), 1, 1));

        assertNotNull(availableHouses);
        assertNotNull(availableHouses2);
        assertTrue(availableHouses.isEmpty());
        assertEquals(2, availableHouses2.size());
    }

    @Test
    public void testFindHousesInSpecificCountry_withGivenCountryName_shouldReturnEquals()
    {
        var housesInTurkey = m_travelServiceHelper.findAllHouseInCountry("Turkey", 1);
        var housesInUSA = m_travelServiceHelper.findAllHouseInCountry("USA", 1);

        assertNotNull(housesInTurkey);
        assertNotNull(housesInUSA);

        var houseListInTurkey = toList(housesInTurkey);
        var houseListInUSA = toList(housesInUSA);

        assertEquals(2, houseListInTurkey.size());
        assertEquals(1, houseListInUSA.size());
    }

    @Test
    public void testFindHousesInSpecificCountry_withGivenCountryName_shouldReturnEmptyList()
    {
        var houses = m_travelServiceHelper.findAllHouseInCountry("GERMANY", 1);
        assertNotNull(houses);

        var houseList = toList(houses);

        assertEquals(0, houseList.size());
    }

    @Test
    public void testFindHousesByViewType_withGivenViewType_shouldReturnEqual()
    {
        var forestHouses = m_travelServiceHelper.findHouseByViewType(ViewType.FOREST, 1);
        var mountainHouses = m_travelServiceHelper.findHouseByViewType(ViewType.MOUNTAIN, 1);
        assertNotNull(forestHouses);
        assertNotNull(mountainHouses);

        var forestHouseList = toList(forestHouses);
        var mountainHouseList = toList(mountainHouses);

        assertEquals(2, forestHouseList.size());
        assertEquals(0, mountainHouseList.size());
    }


    @Test
    public void testFindHousesByHouseType_withGivenHouseType_shouldReturnEqual()
    {
        var apartments = m_travelServiceHelper.findAllHouseByHouseType(HouseType.APARTMENT, 1);
        var cottages = m_travelServiceHelper.findAllHouseByHouseType(HouseType.COTTAGE, 1);

        assertNotNull(apartments);
        assertNotNull(cottages);

        var apartmentList = toList(apartments);
        var cottageList = toList(cottages);

        assertEquals(1, apartmentList.size());
        assertEquals(0, cottageList.size());
    }

    @Test
    public void testFindHousesByPriceBetween_withGivenAmount_shouldReturnEqual()
    {
        var result = toList(m_travelServiceHelper.findAllHouseByPriceBetween(100.0, 300.0, 1));

        assertNotNull(result);
        assertEquals(2, result.size());
    }


    @Test
    public void testFindHousesByPriceLessThanEqual_withGivenAmount_shouldReturnEqual()
    {
        var result = toList(m_travelServiceHelper.findAllHouseByPriceLessThanEqual(200.0, 1));
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindHousesByPriceGreaterThanEqual_withGivenAmount_shouldReturnEqual()
    {
        var result = toList(m_travelServiceHelper.findAllHouseByPriceGreaterThanEqual(200.0, 1));
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindHousesByCity_withGivenCityName_shouldReturnEqual()
    {
        var result = toList(m_travelServiceHelper.findAllHouseInCity("Istanbul", 1));
        assertNotNull(result);
        assertEquals(2, result.size());
    }


    @Test
    public void testFindHousesByCountryAndCity_withGivenCityAndCountryName_shouldReturnEmpty()
    {
        var result = toList(m_travelServiceHelper.findAllHouseByCountryAndCity("USA", "New York", 1));
        assertNotNull(result);
        assertEquals(1, result.size());
    }


    @Test
    public void testFindHousesByCountryAndCity_withGivenCityAndCountryName_shouldReturnEqual()
    {
        var result = toList(m_travelServiceHelper.findAllHouseByCountryAndCity("Turkey", "Istanbul", 1));
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
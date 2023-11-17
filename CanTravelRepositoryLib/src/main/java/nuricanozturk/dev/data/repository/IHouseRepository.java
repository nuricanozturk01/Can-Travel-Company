package nuricanozturk.dev.data.repository;

import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.data.entity.ViewType;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
@Lazy
public interface IHouseRepository extends JpaRepository<House, UUID>
{
    @Query("from House where viewType = :viewType")
    Page<House> findByViewType(ViewType viewType, Pageable pageable);

    Page<House> findAllByHouseNameContainsIgnoreCase(String word, Pageable pageable);

    Page<House> findAllByHouseType(HouseType m_houseType, Pageable pageable);

    Page<House> findAllByPriceBetween(double min, double max, Pageable pageable);

    Page<House> findAllByPriceLessThanEqual(double price, Pageable pageable);

    Page<House> findAllByPriceGreaterThanEqual(double price, Pageable pageable);

    Page<House> findHouseByHouseName(String homeName, Pageable pageable);

    @Query("from House where location.city = :city")
    Page<House> findAllByCity(@Param("city") String city, Pageable pageable);

    @Query("from House where location.country = :country")
    Page<House> findAllByCountry(@Param("country") String country, Pageable pageable);

    @Query("from House where location.country = :country and location.city = :city")
    Page<House> findAllByCountryAndCity(@Param("country") String country,
                                        @Param("city") String city, Pageable pageable);


    @Query("""
    SELECT r.house
    FROM Reservation r
    WHERE (r.startDate >= :finishDate OR r.finishDate <= :startDate)
        AND r.house.maxParticipantCount >= :participantCount
""")
    Page<House> findAvailableHousesBetweenDates(
            @Param("startDate") LocalDate startDate,
            @Param("finishDate") LocalDate finishDate,
            @Param("participantCount") int participantCount,
            Pageable pageable
    );



    @Query("""
                SELECT CASE WHEN COUNT(res) > 0 THEN true ELSE false END
                FROM Reservation res
                WHERE res.house.houseId = :houseId
                AND NOT res.startDate BETWEEN :startDate AND :finishDate
                AND NOT res.finishDate BETWEEN :startDate AND :finishDate
                AND NOT :startDate BETWEEN res.startDate AND res.finishDate
                AND NOT :finishDate BETWEEN res.startDate AND res.finishDate
            """)
    boolean isHouseAvailableBetweenDates(@Param("houseId") UUID houseId, @Param("startDate") LocalDate startDate,
                                         @Param("finishDate") LocalDate finishDate);

}
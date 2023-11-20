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
import java.util.List;
import java.util.UUID;

@Repository
@Lazy
public interface IHouseRepository extends JpaRepository<House, UUID>
{
    @Override
    Page<House> findAll(Pageable pageable);


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
                select h
                from House h
                where h not in (
                    select distinct r.house
                    from Reservation r
                    where (r.startDate <= :finishDate and r.finishDate >= :startDate)
                ) and h.maxParticipantCount >= :participantCount
            """)
    Page<House> findAvailableHousesBetweenDates(
            @Param("startDate") LocalDate startDate,
            @Param("finishDate") LocalDate finishDate,
            @Param("participantCount") int participantCount,
            Pageable pageable
    );
    

    @Query("""
                select case when count (res) = 0 then true else false end
                from Reservation res where res.house.houseId = :houseId
                and not (:startDate >= res.finishDate or :finishDate <= res.startDate)
            """)
    boolean isHouseAvailableBetweenDates(@Param("houseId") UUID houseId,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("finishDate") LocalDate finishDate);


}
package nuricanozturk.dev.data.repository;

import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.data.entity.Reservation;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
@Lazy
public interface IReservationRepository extends CrudRepository<Reservation, UUID>
{
    boolean existsReservationByStartDateAfterAndFinishDateBeforeAndHouse(LocalDate start, LocalDate finish, House house);
}

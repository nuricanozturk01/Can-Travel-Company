package nuricanozturk.dev.data.repository;

import nuricanozturk.dev.data.entity.Location;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public interface ILocationRepository extends CrudRepository<Location, Long>
{
    @Query("from Location where city = :city")
    Iterable<Location> findAllByCity(String city);
    @Query("from Location where country = :country")
    Iterable<Location> findAllByCountry(String country);
}

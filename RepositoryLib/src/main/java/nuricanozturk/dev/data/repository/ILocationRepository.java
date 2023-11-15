package nuricanozturk.dev.data.repository;

import nuricanozturk.dev.data.entity.Location;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public interface ILocationRepository extends CrudRepository<Location, Long>
{
}

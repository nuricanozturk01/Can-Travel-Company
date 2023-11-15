package nuricanozturk.dev.data.repository;

import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.data.entity.ViewType;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Lazy
public interface IHouseRepository extends CrudRepository<House, UUID>
{
    @Query("from House where m_viewType = :viewType")
    Iterable<House> findByViewType(ViewType viewType);
}

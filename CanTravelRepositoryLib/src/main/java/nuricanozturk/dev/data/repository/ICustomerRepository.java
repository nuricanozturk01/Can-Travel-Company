package nuricanozturk.dev.data.repository;

import nuricanozturk.dev.data.entity.Customer;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Lazy
public interface ICustomerRepository extends CrudRepository<Customer, UUID>
{
    Optional<Customer> findByUsername(String username);
    @Query("from Customer where email = :email")
    Optional<Customer> findByEmail(String email);
}

package nuricanozturk.dev.service.booking.mapper;


import nuricanozturk.dev.data.entity.Customer;
import nuricanozturk.dev.service.booking.dto.RegisterDTO;
import org.mapstruct.Mapper;

@Mapper(implementationName = "UserRegisterMapperImpl", componentModel = "spring")
public interface IUserRegisterMapper
{
    /**
     * @param registerDTO is registerDTO
     * @return Customer
     */
    Customer toCustomer(RegisterDTO registerDTO);

    /**
     * @param customer is customer
     * @return RegisterDTO
     */
    RegisterDTO toRegisterDTO(Customer customer);
}

package nuricanozturk.dev.service.booking.mapper;


import nuricanozturk.dev.data.entity.Customer;
import nuricanozturk.dev.service.booking.dto.RegisterDTO;
import org.mapstruct.Mapper;

@Mapper(implementationName = "UserRegisterMapperImpl", componentModel = "spring")
public interface IUserRegisterMapper
{
    Customer toCustomer(RegisterDTO registerDTO);

    RegisterDTO toRegisterDTO(Customer customer);
}

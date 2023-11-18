package nuricanozturk.dev.service.booking.service;

import nuricanozturk.dev.service.booking.dto.LoginDTO;
import nuricanozturk.dev.service.booking.dto.LoginResponseDTO;
import nuricanozturk.dev.service.booking.dto.RegisterDTO;
import nuricanozturk.dev.service.booking.dto.RegisterResponseDTO;

public interface IAuthenticationService
{
    RegisterResponseDTO register(RegisterDTO registerDTO);

    LoginResponseDTO login(LoginDTO loginDTO);
}

package nuricanozturk.dev.service.booking.service;

import nuricanozturk.dev.service.booking.dto.LoginDTO;
import nuricanozturk.dev.service.booking.dto.LoginResponseDTO;
import nuricanozturk.dev.service.booking.dto.RegisterDTO;
import nuricanozturk.dev.service.booking.dto.RegisterResponseDTO;

public interface IAuthenticationService
{
    /**
     * Register.
     *
     * @param registerDTO is registerDTO
     * @return RegisterResponseDTO
     */
    RegisterResponseDTO register(RegisterDTO registerDTO);

    /**
     * Login.
     *
     * @param loginDTO is loginDTO
     * @return LoginResponseDTO
     */
    LoginResponseDTO login(LoginDTO loginDTO);
}

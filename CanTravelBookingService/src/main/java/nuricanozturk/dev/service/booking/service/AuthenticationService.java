package nuricanozturk.dev.service.booking.service;

import callofproject.dev.library.exception.service.DataServiceException;
import callofproject.dev.service.jwt.JwtUtil;
import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.service.booking.config.CanTravelAuthenticationProvider;
import nuricanozturk.dev.service.booking.dto.LoginDTO;
import nuricanozturk.dev.service.booking.dto.LoginResponseDTO;
import nuricanozturk.dev.service.booking.dto.RegisterDTO;
import nuricanozturk.dev.service.booking.dto.RegisterResponseDTO;
import nuricanozturk.dev.service.booking.mapper.IUserRegisterMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;
import static java.lang.String.format;

@Service
@Lazy
public class AuthenticationService implements IAuthenticationService
{
    private final CanTravelServiceHelper m_travelServiceHelper;
    private final CanTravelAuthenticationProvider m_authenticationProvider;
    private final PasswordEncoder m_passwordEncoder;
    private final IUserRegisterMapper m_registerMapper;

    public AuthenticationService(CanTravelServiceHelper travelServiceHelper, CanTravelAuthenticationProvider authenticationProvider,
                                 PasswordEncoder passwordEncoder, IUserRegisterMapper registerMapper)
    {
        m_travelServiceHelper = travelServiceHelper;
        m_authenticationProvider = authenticationProvider;
        m_passwordEncoder = passwordEncoder;
        m_registerMapper = registerMapper;
    }

    @Override
    public RegisterResponseDTO register(RegisterDTO registerDTO)
    {
        return doForDataService(() -> registerCallback(registerDTO), "AuthenticationService::register");
    }

    @Override
    public LoginResponseDTO login(LoginDTO loginDTO)
    {
        return doForDataService(() -> loginCallback(loginDTO), "AuthenticationService::login");
    }

    //-----------------------------------------_CALLBACKS_---------------------------------------

    private LoginResponseDTO loginCallback(LoginDTO loginDTO)
    {
        var auth = m_authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));

        if (!auth.isAuthenticated())
            throw new DataServiceException("Invalid username or password!");

        return new LoginResponseDTO(true, "User login operation is successful", JwtUtil.generateToken(loginDTO.username()));
    }

    private RegisterResponseDTO registerCallback(RegisterDTO registerDTO)
    {
        registerDTO.setPassword(m_passwordEncoder.encode(registerDTO.getPassword()));

        var savedUser = m_travelServiceHelper.saveCustomer(m_registerMapper.toCustomer(registerDTO));

        var token = JwtUtil.generateToken(savedUser.getUsername());

        return new RegisterResponseDTO(true, format("Welcome %s!", savedUser.getUsername()), token);
    }
}
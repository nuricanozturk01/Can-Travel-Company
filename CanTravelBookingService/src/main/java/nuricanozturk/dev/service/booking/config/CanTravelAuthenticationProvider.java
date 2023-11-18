package nuricanozturk.dev.service.booking.config;

import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CanTravelAuthenticationProvider implements AuthenticationProvider
{
    private final CanTravelServiceHelper m_travelServiceHelper;
    private final PasswordEncoder passwordEncoder;

    public CanTravelAuthenticationProvider(CanTravelServiceHelper travelServiceHelper, PasswordEncoder passwordEncoder)
    {
        m_travelServiceHelper = travelServiceHelper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        var username = authentication.getName();
        var pwd = authentication.getCredentials().toString();
        var user = m_travelServiceHelper.findCustomerByUsername(username);

        if (user.isPresent())
        {
            if (passwordEncoder.matches(pwd, user.get().getPassword()))
                return new UsernamePasswordAuthenticationToken(username, pwd, null);

            else throw new BadCredentialsException("Invalid password!");
        }
        else throw new BadCredentialsException("No user registered with this details!");
    }


    @Override
    public boolean supports(Class<?> authentication)
    {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
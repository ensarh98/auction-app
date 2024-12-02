package com.auctionapp.auth.spring;

import com.auctionapp.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Ensar Horozović
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final String BAD_CREDENTIALS_MSG = "Neispravno korisničko ime ili lozinka.";

	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		var user = userRepository.findOneByEmail(authentication.getName());
		var bCryptPasswordEncoder = new BCryptPasswordEncoder();
		var credentials = authentication.getCredentials();

		if (user != null
				&& ((credentials != null && (bCryptPasswordEncoder.matches(credentials.toString(), user.getPassword())
						|| credentials.toString().equals(user.getPassword()))) || credentials == null)) {

			return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

		} else {
			throw new BadCredentialsException(BAD_CREDENTIALS_MSG);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}

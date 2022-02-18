package com.my.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private SecurityUserService userDeSer;

	@SuppressWarnings("unchecked")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		if (authentication == null) {
			throw new InternalAuthenticationServiceException("Authentication is null");
		}
		String username = authentication.getName();
		if (authentication.getCredentials() == null) {
			throw new AuthenticationCredentialsNotFoundException("Credentials is null");
		}
		String password = authentication.getCredentials().toString();
		UserDetails loadedUser = userDeSer.loadUserByUsername(username);
		if (loadedUser == null) {
			throw new InternalAuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}
		/* checker */ if (!loadedUser.isAccountNonLocked()) {
			throw new LockedException("User account is locked");
		}
		if (!loadedUser.isEnabled()) {
			throw new DisabledException("User is disabled");
		}
		if (!loadedUser.isAccountNonExpired()) {
			throw new AccountExpiredException("User account has expired");
		}
		/* 실질적인 인증 */
//		if (!passwordEncoder.matches(password, loadedUser.getPassword())) {
//			throw new BadCredentialsException("Password does not match stored value");
//		}
		if (!password.equals(loadedUser.getPassword())) {
			throw new BadCredentialsException("Password does not match stored value");
		}
		/* checker */
		if (!loadedUser.isCredentialsNonExpired()) {
			throw new CredentialsExpiredException("User credentials have expired");
		}
		/* 인증 완료 */
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loadedUser, null,
				loadedUser.getAuthorities());
		result.setDetails(authentication.getDetails());
		return result;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}

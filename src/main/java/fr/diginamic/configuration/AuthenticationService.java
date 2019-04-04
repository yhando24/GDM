package fr.diginamic.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import fr.diginamic.user.model.User;
import fr.diginamic.user.repository.UserRepository;

@Component("userDetailsService")
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		String userLogin = email.toLowerCase();
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User " + userLogin + " not found");

		} else {
			return new org.springframework.security.core.userdetails.User(userLogin, user.getPassword(), true, true,
					true, true, findAuthorityForUser(user));
		}
	}

	private List<GrantedAuthority> findAuthorityForUser(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
		return authorities;
	}

}

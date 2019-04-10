package fr.diginamic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import fr.diginamic.user.model.User;
import fr.diginamic.user.repository.UserRepository;

@Service
public class SecurityUtils {

	@Autowired
	private  UserRepository userRepository;

	public  User getConnectedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails detail = (UserDetails) authentication.getPrincipal();
		System.out.println(detail.getUsername());

		return userRepository.findByEmail(detail.getUsername());
	}
}

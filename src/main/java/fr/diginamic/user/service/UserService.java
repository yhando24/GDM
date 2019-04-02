package fr.diginamic.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.user.model.RoleEnum;
import fr.diginamic.user.model.User;
import fr.diginamic.user.repository.UserRepository;

@Service
public class UserService {


	@Autowired
	private UserRepository userRepository;

	// create

	public User save(User user) {
		return userRepository.save(user);
	}

	// read

	public List<User> findByLastName(String lastName) {
		return userRepository.findByLastName(lastName);
	}

	public List<User> findByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}

	public List<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<User> findByRole(RoleEnum role) {
		return userRepository.findByRole(role);
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public List<User> findAllById(List<Long> ids) {
		return userRepository.findAllById(ids);
	}

	public Optional<User> findByPwdAndEmail(String password, String email) {
		return userRepository.findByPasswordAndEmail(password, email);
	}

	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	// delete

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public void deleteAll(List<? extends User> users) {
		userRepository.deleteAll(users);
	}

	public void deleteAll() {
		userRepository.deleteAll();
	}
}
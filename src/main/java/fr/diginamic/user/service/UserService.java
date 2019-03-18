package fr.diginamic.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.user.exception.UserNotFoundException;
import fr.diginamic.user.model.RoleEnum;
import fr.diginamic.user.model.User;
import fr.diginamic.user.repository.UserRepository;

@Service
public class UserService {

	/**
	 * Methods with convertion from User entity to User dto and reverse.
	 */

	@Autowired
	private UserRepository userRepository;

	// create

	public User save(User user) throws UserNotFoundException {
		Optional<User> newUser = userRepository.findById(user.getId());
		if (newUser == null) {
			throw new UserNotFoundException("user n'existe pas");
		}
		return user;
	}

	public User update(User user) {
		User updateUser = userRepository.save(user);
		if (updateUser != null) {
			throw new UserNotFoundException("");
		}

		return user;
	}

//
//	public List<User> saveAll(List<User> users) {
//		return userRepository.saveAll(users);
//	}

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

//	public User save(User user) throws UserNotFoundException {
//		User u = userRepository.findById(id);
//		if (u == null) {
//			throw new UserNotFoundException("user n'existe pas");
//		}
//		return user;
//	}

//	public User update(User user) {
//		User updateUser = userRepository.findById(id);
//		if (updateUser != null) {
//			throw new UserNotFoundException("");
//		}
//
//		return user;
//	}

//	public void deleteUser(Long id) {
//		userRepository.deleteById(id);
//	}
//
//	public Optional<User> findById(Long id) {
//		Optional<User> user = userRepository.findById(id);
//		return user;
//	}

//	public List<UserDTO> findAll() {
//		return toDto(userRepository.findAll());
//	}
//
//	public UserDTO findById(Long id) throws UserNotFoundException {
//		User user = userRepository.findOne(id);
//		if (user == null) {
//			throw new UserNotFoundException("user n'existe pas");
//		}
//		return toDto(user);
//	}
//
//	public UserDTO create(UserDTO dto) {
//		return toDto(userRepository.save(toEntity(dto)));
//	}
//
//	public UserDTO update(UserDTO dto) {
//		User updateUser = userRepository.findOne(dto.getId());
//		if (updateUser != null) {
//			throw new UserNotFoundException("");
//		}
//		return toDto(userRepository.save(toEntity(dto)));
//	}

//	public void List<UserDTO> deleteAll(){
//		
//	}

//	public UserDTO delete(UserDTO dto) throws UserNotFoundException {
//		User resource = userRepository.findOne(dto.getId());
//		if (resource == null) {
//			throw new UserNotFoundException("user n'existe pas, suppresion impossible");
//		}
//		return dto;
//	}

//	private static List<User> toEntity(List<UserDTO> dto) {
//		return dto.stream().map(d -> toEntity(d)).collect(Collectors.toList());
//	}
//
//	private static List<UserDTO> toDto(List<User> user) {
//		return user.stream().map(u -> toDto(u)).collect(Collectors.toList());
//	}

//	private static User toEntity(UserDTO dto) {
//		User user = new User();
//		user.setId(dto.getId());
//		user.setFirstName(dto.getFirstName());
//		user.setLastName(dto.getLastName());
//		user.setEmail(dto.getEmail());
//		user.setPassword(dto.getPassword());
//		user.setRole(dto.getRole());
//		return user;
//	}
//
//	private static UserDTO toDto(User user) {
//		UserDTO dto = new UserDTO();
//		dto.setId(user.getId());
//		dto.setFirstName(user.getFirstName());
//		dto.setLastName(user.getLastName());
//		dto.setEmail(user.getEmail());
//		dto.setPassword(user.getPassword());
//		dto.setRole(user.getRole());
//		return dto;
//	}

}

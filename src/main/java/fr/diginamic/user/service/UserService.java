package fr.diginamic.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.user.exception.ControllerUserException;
import fr.diginamic.user.model.RoleEnum;
import fr.diginamic.user.model.User;
import fr.diginamic.user.model.UserDTO;
import fr.diginamic.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MapperUserService mpu;

	// create

	public UserDTO save(UserDTO userDTO) {
		userDTO.setRole(RoleEnum.USER);
		return mpu.toDTO(userRepository.save(mpu.toEntity(userDTO)));
	}
	// read

	public List<UserDTO> findByLastName(String lastName) {
		return mpu.toDTOs(userRepository.findByLastName(lastName));
	}

	public List<UserDTO> findByFirstName(String firstName) {
		return mpu.toDTOs(userRepository.findByFirstName(firstName));
	}

	public UserDTO findByEmail(String email) {
		return mpu.toDTO(userRepository.findByEmail(email));
	}

	public List<UserDTO> findByRole(RoleEnum role) {
		return mpu.toDTOs(userRepository.findByRole(role));
	}

	public UserDTO findById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			return mpu.toDTO(userOptional.get());
		} else {
			throw new ControllerUserException("L'id correpond à aucun user");
		}
	}

	public List<UserDTO> findAllById(List<Long> ids) {
		return mpu.toDTOs(userRepository.findAllById(ids));
	}

	public UserDTO findByPwdAndEmail(String password, String email) {
		Optional<User> userOptional = userRepository.findByPasswordAndEmail(password, email);
		if (userOptional.isPresent()) {
			return mpu.toDTO(userOptional.get());
		} else {
			throw new ControllerUserException("Le user n'existe pas");
		}
	}

	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	public List<UserDTO> findAll() {
		return mpu.toDTOs(userRepository.findAll());
	}

	// delete

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public void delete(UserDTO userDTO) {
		userRepository.delete(mpu.toEntity(userDTO));
	}

}
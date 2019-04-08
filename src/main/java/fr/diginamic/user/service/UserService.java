package fr.diginamic.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.mission.repository.MissionRepository;
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
	private MissionRepository missionRepository;

	@Autowired
	private MapperUserService mpu;

	// create

	public UserDTO save(UserDTO userDTO) throws ControllerUserException {
		try {
			return mpu.toDTO(userRepository.save(mpu.toEntity(userDTO)));

		} catch (Exception e) {
			throw new ControllerUserException("L'email " + userDTO.getEmail() + " existe déjà.");

		}
	}
	// read

	public List<UserDTO> findByLastName(String lastName) throws ControllerUserException {
		try {
			return mpu.toDTOs(userRepository.findByLastName(lastName));

		} catch (Exception e) {
			throw new ControllerUserException("Aucun utilisateur avec le nom : " + lastName + "n'a été trouvé.");

		}
	}

	public List<UserDTO> findByFirstName(String firstName) throws ControllerUserException {
		try {
			return mpu.toDTOs(userRepository.findByFirstName(firstName));
		} catch (Exception e) {
			throw new ControllerUserException("Aucun utilisateur avec le prénom : " + firstName + "n'a été trouvé.");

		}
	}

	public UserDTO findByEmail(String email) throws ControllerUserException {
		try {
			return mpu.toDTO(userRepository.findByEmail(email));
		} catch (Exception e) {
			throw new ControllerUserException("L'utilisateurs avec le mail : " + email + "n'a pas été trouvé.");

		}
	}

	public List<UserDTO> findByRole(RoleEnum role) throws ControllerUserException {
		try {
			return mpu.toDTOs(userRepository.findByRole(role));
		} catch (Exception e) {
			throw new ControllerUserException("Aucun utilisateur avec le role : " + role + '.');

		}
	}

	public UserDTO findById(Long id) throws ControllerUserException {
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
		User u = userRepository.findById(id).orElseThrow(() ->  new ControllerUserException("Le user n'existe pas"));
		if(missionRepository.findByUser(u).size()>0) {
			throw new ControllerUserException("Cet utilisateur ne peut pas etre supprimé car il a déjà affecté à une mission");
		}else {
			userRepository.deleteById(id);
		}
		
	}

	public void delete(UserDTO userDTO) {
		userRepository.delete(mpu.toEntity(userDTO));
	}

}
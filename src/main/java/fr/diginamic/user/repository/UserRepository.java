package fr.diginamic.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.user.model.RoleEnum;
import fr.diginamic.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

//create

	public User save(User user);

//read

	public List<User> findByLastName(String lastName);

	public List<User> findByFirstName(String firstName);

	public List<User> findByEmail(String email);

	public List<User> findByRole(RoleEnum role);

	public Optional<User> findById(Long id);

	public List<User> findAllById(List<Long> ids);

	public Optional<User> findByPasswordAndEmail(String password, String email);

	public boolean existsById(Long id);

	public List<User> findAll();

//delete

	public void deleteById(Long id);

	public void delete(User user);

	public void deleteAll();

}

package fr.diginamic.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUserLastName(String lastName);

	List<User> findByLastName(String lastName);

	@Query("select u from User u where u.firstname = :firstname")
	List<User> findByFirstName(String firstName);
}

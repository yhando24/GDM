package fr.diginamic.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByLastName(String lastName);

	@Query("select u from User u where u.firstname = :firstname")
	User findByFirstName(String firstName);
	
}

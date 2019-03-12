package fr.diginamic.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


	List<User> findByLastName(String lastName);


	List<User> findByFirstName(String firstName);
}

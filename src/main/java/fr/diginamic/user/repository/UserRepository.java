package fr.diginamic.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByLastName(String lastName);

}

package fr.diginamic.user.repository;

import java.util.Optional;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.user.model.User;

@Repository
public class UserRepository extends AbstractJpaRepository<User> {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Transactional(readOnly = true)

	public Optional<User> findOneByLastName(String lastName) {
		User user = (User) getSession().createCriteria(entityClass).add(Restrictions.eq("lastName", lastName))
				.uniqueResult();
		return Optional.of(user);
	}

	public Optional<User> findOneByFirstName(String firstName) {
		User user = (User) getSession().createCriteria(entityClass).add(Restrictions.eq("firstName", firstName))
				.uniqueResult();
		return Optional.of(user);
	}

	public void deleteAllUsers() {
		getSession().createQuery("delete from User").executeUpdate();

	}
}

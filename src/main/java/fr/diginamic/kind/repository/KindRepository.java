package fr.diginamic.kind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.kind.model.Kind;


@Repository
public interface KindRepository extends JpaRepository<Kind, Long>{

	


}

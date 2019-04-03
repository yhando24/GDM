package fr.diginamic.kindversion.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.kindversion.model.KindVersion;

@Repository
public interface KindVersionRepository extends JpaRepository<KindVersion, Long>  {
	

	KindVersion findTopByNameOrderByVersionDesc(String name);
	
	
}

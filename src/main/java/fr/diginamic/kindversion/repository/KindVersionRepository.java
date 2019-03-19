package fr.diginamic.kindversion.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.kindversion.model.KindVersion;

public interface KindVersionRepository extends CrudRepository<KindVersion, Long>  {
	
	public  Optional<KindVersion>findById(Long id);

}

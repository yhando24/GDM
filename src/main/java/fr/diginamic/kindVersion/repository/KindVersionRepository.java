package fr.diginamic.kindVersion.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.kindVersion.model.KindVersion;

public interface KindVersionRepository extends CrudRepository<KindVersion, Long>  {
	
	public  Optional<KindVersion>findById(Long id);

}

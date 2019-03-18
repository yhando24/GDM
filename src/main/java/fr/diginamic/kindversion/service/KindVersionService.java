package fr.diginamic.kindVersion.service;

import java.util.Optional;

import fr.diginamic.kindVersion.model.KindVersion;
import fr.diginamic.kindVersion.repository.KindVersionRepository;

public class KindVersionService {

	
	private KindVersionRepository kindVersionRepository;
	
	public  Optional<KindVersion>findById(Long id){
	return	kindVersionRepository.findById(id);
	}
}

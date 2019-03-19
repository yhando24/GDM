package fr.diginamic.kindversion.service;

import java.util.Optional;

import fr.diginamic.kindversion.model.KindVersion;
import fr.diginamic.kindversion.repository.KindVersionRepository;

public class KindVersionService {

	
	private KindVersionRepository kindVersionRepository;
	
	public  Optional<KindVersion>findById(Long id){
	return	kindVersionRepository.findById(id);
	}
}

package fr.diginamic.kind.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.kind.model.Kind;
import fr.diginamic.kind.model.KindDTO;
import fr.diginamic.kind.repository.KindRepository;
import fr.diginamic.kindversion.model.KindVersionDTO;
import fr.diginamic.kindversion.model.MapperKindVersionService;
import fr.diginamic.kindversion.repository.KindVersionRepository;

@Service
public class KindService {

	@Autowired
	private KindRepository kindRepository;

	@Autowired
	private KindVersionRepository kindVersionRepository;

	@Autowired
	private MapperKindService mapperKindService;

	@Autowired
	private MapperKindVersionService mapperKindVersionService;

	public List<KindDTO> findAll() {
		// TODO Auto-generated method stub
		return mapperKindService.toDTOs(kindRepository.findAll());
	}

	@Transactional
	public KindDTO save(KindDTO k) {
		
		Optional<Kind> kind = kindRepository.findByName(k.getName());
		if (kind.isPresent()) {
			
			KindVersionDTO Lastversion = mapperKindVersionService
					.toDTO(kindVersionRepository.findTopByNameOrderByVersionDesc(k.getName()));
			KindVersionDTO newVersion = KindService.toKindVersionDTO(k);
			newVersion.setVersion(Lastversion.getVersion() + 1);

			kindVersionRepository.save(mapperKindVersionService.toEntity(newVersion));
		} else {
			
			KindVersionDTO newVersion = KindService.toKindVersionDTO(k);
			newVersion.setVersion(1L);
			kindVersionRepository.save(mapperKindVersionService.toEntity(newVersion));
		}

		return k;

	}

	public static KindVersionDTO toKindVersionDTO(KindDTO d) {

		KindVersionDTO kv = new KindVersionDTO();
		kv.setAdr(d.getAdr());
		kv.setAuthorizationToExceed(d.getAuthorizationToExceed());
		kv.setBonus(d.getBonus());
		kv.setBonusPercentage(d.getBonusPercentage());
		kv.setDailyCharges(d.getDailyCharges());
		kv.setInvoiced(d.getInvoiced());
		kv.setKind(d);
		kv.setName(d.getName());
		kv.setUpdatedAt(LocalDateTime.now());

		return kv;

	}

}

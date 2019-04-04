package fr.diginamic.kind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.kind.model.KindDTO;
import fr.diginamic.kind.repository.KindRepository;
import fr.diginamic.mission.service.MissionService;

@Service
public class KindService {

	@Autowired
	private KindRepository kindRepository;

//	@Autowired
//	private KindVersionRepository kindVersionRepository;

	@Autowired
	private MapperKindService mapperKindService;

//	@Autowired
//	private MapperKindVersionService mapperKindVersionService;

	@Autowired
	private MissionService missionService;

	public List<KindDTO> findAll() {
		// TODO Auto-generated method stub
		return mapperKindService.toDTOs(kindRepository.findAll());
	}

	public KindDTO save(KindDTO k) {

		/*
		 * Optional<Kind> kind = kindRepository.findByName(k.getName()); if
		 * (kind.isPresent()) {
		 * 
		 * KindVersionDTO Lastversion = mapperKindVersionService
		 * .toDTO(kindVersionRepository.findTopByNameOrderByVersionDesc(k.getName()));
		 * KindVersionDTO newVersion = KindService.toKindVersionDTO(k);
		 * newVersion.setVersion(Lastversion.getVersion() + 1);
		 * 
		 * kindVersionRepository.save(mapperKindVersionService.toEntity(newVersion)); }
		 * else {
		 * 
		 * KindVersionDTO newVersion = KindService.toKindVersionDTO(k);
		 * newVersion.setVersion(1L);
		 * kindVersionRepository.save(mapperKindVersionService.toEntity(newVersion)); }
		 */

		return mapperKindService.toDTO(kindRepository.save(mapperKindService.toEntity(k)));

	}

	/*
	 * public static KindVersionDTO toKindVersionDTO(KindDTO d) {
	 * 
	 * KindVersionDTO kv = new KindVersionDTO(); kv.setAdr(d.getAdr());
	 * kv.setAuthorizationToExceed(d.getAuthorizationToExceed());
	 * kv.setBonus(d.getBonus()); kv.setBonusPercentage(d.getBonusPercentage());
	 * kv.setDailyCharges(d.getDailyCharges()); kv.setInvoiced(d.getInvoiced());
	 * kv.setKind(d); kv.setName(d.getName());
	 * 
	 * kv.setUpdatedAt(LocalDateTime.now());
	 * 
	 * 
	 * return kv;
	 * 
	 * }
	 */

	public void deleteKind(Long id) {

		kindRepository.deleteById(id);
	}

//	AuditReader reader = AuditReaderFactory.get(em);
//	LocalDate dateToConvert = LocalDate.ofYearDay(year + 1, 1);
//
//	Number num = reader.getRevisionNumberForDate(
//			Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
//	AuditQuery query = reader.createQuery().forEntitiesAtRevision(Salarie.class, num);
//	query.add(AuditEntity.id().eq(id));
//	salarie = (Salarie) query.getSingleResult();

}

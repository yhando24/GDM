package fr.diginamic.kind.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.kind.model.Kind;
import fr.diginamic.kind.model.KindDTO;
import fr.diginamic.kind.repository.KindRepository;
import fr.diginamic.mission.service.MissionService;

@Service
public class KindService {

	@Autowired
	private KindRepository kindRepository;

	@Autowired
	private MapperKindService mapperKindService;

	@Autowired
	private MissionService missionService;

	@PersistenceContext
	EntityManager em;

	public List<KindDTO> findAll() {
		return mapperKindService.toDTOs(kindRepository.findAll());
	}

	public KindDTO save(KindDTO k) {
		return mapperKindService.toDTO(kindRepository.save(mapperKindService.toEntity(k)));
	}

	public void deleteKind(Long id) {
		kindRepository.deleteById(id);
	}

	public KindDTO getHistoric(long id, long millis) {
		Kind kind = null;
		AuditReader reader = AuditReaderFactory.get(em);
		Number num = reader.getRevisionNumberForDate(Date.from(Instant.ofEpochMilli(millis)));
		AuditQuery query = reader.createQuery().forEntitiesAtRevision(Kind.class, num);
		query.add(AuditEntity.id().eq(id));
		kind = (Kind) query.getSingleResult();
		return mapperKindService.toDTO(kind);
	}

	public List<KindDTO> getHistoric(long id) {
		List<Kind> kinds = new ArrayList<>();
		AuditReader reader = AuditReaderFactory.get(em);
		List<Number> revs = reader.getRevisions(Kind.class, id);
		revs.stream().forEach(rev -> {
			AuditQuery query = reader.createQuery().forEntitiesAtRevision(Kind.class, rev);
			query.add(AuditEntity.id().eq(id));
			Kind kind = (Kind) query.getSingleResult();
			kinds.add(kind);
		});
		return mapperKindService.toDTOs(kinds);
	}

}

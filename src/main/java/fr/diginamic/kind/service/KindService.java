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

import fr.diginamic.kind.exception.KindException;
import fr.diginamic.kind.model.HistoricDTO;
import fr.diginamic.kind.model.Kind;
import fr.diginamic.kind.model.KindDTO;
import fr.diginamic.kind.repository.KindRepository;

@Service
public class KindService {

	@Autowired
	private KindRepository kindRepository;

	@Autowired
	private MapperKindService mapperKindService;


	@PersistenceContext
	EntityManager em;

	public List<KindDTO> findAll() {
		return mapperKindService.toDTOs(kindRepository.findAll());
	}

	public List<KindDTO> findActive() {
		return mapperKindService.toDTOs(kindRepository.findByActiveTrue());
	}

	public KindDTO save(KindDTO k) {
		return mapperKindService.toDTO(kindRepository.save(mapperKindService.toEntity(k)));
	}

	public void deleteKind(Long id) {

		this.kindRepository.findById(id).orElseThrow(() -> new KindException("Cette nature n'existe pas"));

		kindRepository.deleteById(id);
	}

	
	
	public KindDTO findKindDTOVersionByIdAndTimestamp(long id, long millis) {

		Kind kind = null;
		AuditReader reader = AuditReaderFactory.get(em);
		Number num = reader.getRevisionNumberForDate(Date.from(Instant.ofEpochMilli(millis)));
		AuditQuery query = reader.createQuery().forEntitiesAtRevision(Kind.class, num);
		query.add(AuditEntity.id().eq(id));
		kind = (Kind) query.getSingleResult();

		if (kind == null) {
			throw new KindException("Cette version de nature n'existe pas");
		}

		return mapperKindService.toDTO(kind);
	}
	
	
	
	public Kind findKindVersionByIdAndTimestamp(long id, long millis) {

		Kind kind = null;
		AuditReader reader = AuditReaderFactory.get(em);
		Number num = reader.getRevisionNumberForDate(Date.from(Instant.ofEpochMilli(millis)));
		AuditQuery query = reader.createQuery().forEntitiesAtRevision(Kind.class, num);
		query.add(AuditEntity.id().eq(id));
		kind = (Kind) query.getSingleResult();

		if (kind == null) {
			throw new KindException("Cette version de nature n'existe pas");
		}

		return kind;
	}

	
	
	public List<HistoricDTO> findKindHistoricById(long id) {
		List<HistoricDTO> historique = new ArrayList<>();
		AuditReader reader = AuditReaderFactory.get(em);
		List<Number> revs = reader.getRevisions(Kind.class, id);

		if (revs == null) {
			throw new KindException("Il n'y a pas d'historique de cette nature");
		}

		revs.stream().forEach(rev -> {
			AuditQuery query = reader.createQuery().forEntitiesAtRevision(Kind.class, rev);
			query.add(AuditEntity.id().eq(id));
			Kind kind = (Kind) query.getSingleResult();
			Date date = reader.getRevisionDate(rev);
			historique.add(new HistoricDTO(date, mapperKindService.toDTO(kind)));
		});

		return historique;
	}

}

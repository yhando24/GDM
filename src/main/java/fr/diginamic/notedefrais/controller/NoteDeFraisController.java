package fr.diginamic.notedefrais.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.notedefrais.model.LigneDeFrais;
import fr.diginamic.notedefrais.model.NoteDeFrais;
import fr.diginamic.notedefrais.model.LigneDeFraisModel;
import fr.diginamic.notedefrais.exception.NoteDeFraisException;
import fr.diginamic.notedefrais.model.NoteDeFraisModel;
import fr.diginamic.mission.repository.MissionRepository;
import fr.diginamic.notedefrais.repository.LigneDeFraisRepository;
import fr.diginamic.notedefrais.repository.NoteDeFraisRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/notes")
public class NoteDeFraisController {

	private static final String KEY_MESSAGE_HEADER = "response_message";
	@Autowired
	private NoteDeFraisRepository noteDeFraisRepo;

	@Autowired
	private LigneDeFraisRepository ligneDeFraisRepo;

	@Autowired
	private MissionRepository missionRepo;

	/**
	 * Retourne toutes les notes de frais
	 */
	@GetMapping()
	public List<NoteDeFrais> searchAll() {
		return this.noteDeFraisRepo.findAll();
	}

	/**
	 * Retourne la liste des natures (type) de frais != nature de mission
	 */
	@GetMapping("/frais/natures")
	public String[] listerNatures() {
		return new String[] { "Hôtel", "Petit-Déjeuner", "Restaurant" };
	}

	/**
	 * Retourne la ligne de frais en fonction de l'id du frais
	 */
	@GetMapping("/frais/{id}")
	public ResponseEntity<LigneDeFraisModel> lireUnFrais(@PathVariable Integer id) throws NoteDeFraisException {
		if (id > 0) {
			LigneDeFrais frais = this.ligneDeFraisRepo.findOne(id);
			if (frais != null) {
				LigneDeFraisModel fraisModel = new LigneDeFraisModel(Integer.toString(frais.getId()),
						frais.getDate().format(DateTimeFormatter.ISO_DATE), frais.getNature(),
						frais.getMontant().toString());
				return ResponseEntity.ok(fraisModel);
			}
		}
		final String VALUE_MESSAGE_HEADER = "Aucun frais trouvé pour l'id: " + id;
		throw new NoteDeFraisException(VALUE_MESSAGE_HEADER);
	}

	/**
	 * Retourne une note de frais en fonction de l'id de la mission
	 */
	@GetMapping("/missions/{id}")
	public ResponseEntity<NoteDeFraisModel> findNoteFraisById(@PathVariable Integer id) throws NoteDeFraisException {
		// récupérer la note de frais via l'id de mission
		Optional<NoteDeFrais> noteOpt = noteDeFraisRepo.findByMissionId(id);
		if (noteOpt.isPresent()) {
			List<LigneDeFrais> noteItemsEntities = ligneDeFraisRepo.findByNoteDeFrais(noteOpt.get());

			// generer l'objet Model retouné
			List<LigneDeFraisModel> items = noteItemsEntities.stream()
					.map(n -> new LigneDeFraisModel(Integer.toString(n.getId()),
							n.getDate().format(DateTimeFormatter.ISO_DATE), n.getNature(), n.getMontant().toString()))
					.collect(Collectors.toList());
			return ResponseEntity.ok(new NoteDeFraisModel(id, items));
		} else {
			final String VALUE_MESSAGE_HEADER = "Pas de note de frais trouvé pour la mission : " + id;
			throw new NoteDeFraisException(VALUE_MESSAGE_HEADER);
		}
	}

	/**
	 * Ajouter la ligne de frais à une note de frais
	 */
	@PostMapping("/{id}/frais")
	public ResponseEntity<LigneDeFraisModel> creerLigneDeFrais(@PathVariable Integer id,
			@RequestBody LigneDeFraisModel fraisModel) throws NoteDeFraisException {
		// récupérer la note de frais
		if (id > 0) {
			NoteDeFrais note = noteDeFraisRepo.findOne(id);
			if (note != null) {
				// vérifier que le frais est unique
				if (false == estUniqueDateAndNatureAndMission(id, fraisModel)) {
					final String VALUE_MESSAGE_HEADER = "Impossible d'ajout le frais : Un frais de même nature existe dèja pour cette date !";
					throw new NoteDeFraisException(VALUE_MESSAGE_HEADER);
				}
				// parser le model en entité
				LigneDeFrais frais = new LigneDeFrais(fraisModel.getNature(),
						LocalDate.parse(fraisModel.getDate(), DateTimeFormatter.ISO_DATE),
						new BigDecimal(fraisModel.getMontant()), note);
				// parser l'entité en model
				frais = this.ligneDeFraisRepo.save(frais);
				LigneDeFraisModel newFrais = new LigneDeFraisModel(Integer.toString(frais.getId()),
						frais.getDate().format(DateTimeFormatter.ISO_DATE), frais.getNature(),
						frais.getMontant().toString());
				return ResponseEntity.ok(newFrais);
			}
		}
		final String VALUE_MESSAGE_HEADER = "Pas de note de frais trouvé pour la mission : " + id;
		throw new NoteDeFraisException(VALUE_MESSAGE_HEADER);
	}

	/**
	 * Vérifier que le frais est unique (couple nature + date)
	 */
	private Boolean estUniqueDateAndNatureAndMission(Integer id, LigneDeFraisModel fraisModel) {

		LocalDate date = LocalDate.parse(fraisModel.getDate(), DateTimeFormatter.ISO_DATE);
		List<LigneDeFrais> items = ligneDeFraisRepo.findByDateAndNatureAndNoteDeFraisMissionId(date,
				fraisModel.getNature(), id);
		if (items.isEmpty()) {
			// le frais est unique
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Modifier la ligne de frais
	 */
	@PutMapping("/frais")
	public ResponseEntity<LigneDeFraisModel> creerLigneDeFrais(@RequestBody LigneDeFraisModel fraisModel)
			throws NoteDeFraisException {
		// récupérer la note de frais

		LigneDeFrais frais = ligneDeFraisRepo.findOne(Integer.parseInt(fraisModel.getId()));
		if (frais != null) {

			// parser le model en entité
			frais.setNature(fraisModel.getNature());
			frais.setMontant(new BigDecimal(fraisModel.getMontant()));
			frais.setDate(LocalDate.parse(fraisModel.getDate(), DateTimeFormatter.ISO_DATE));

			// parser l'entité en model
			frais = this.ligneDeFraisRepo.save(frais);

			return ResponseEntity.ok(fraisModel);
		}

		final String VALUE_MESSAGE_HEADER = "Pas de frais trouvé pour l'id : " + fraisModel.getId();
		throw new NoteDeFraisException(VALUE_MESSAGE_HEADER);
	}

	@DeleteMapping("frais/{id}")
	public String supprimerFrais(@PathVariable Integer id) throws NoteDeFraisException {
		// récupérer la ligne de frais
		LigneDeFrais frais = ligneDeFraisRepo.findOne(id);
		if (null == frais)
			throw new NoteDeFraisException("La ligne de frais avec l'id " + id + " n'existe pas !");
		ligneDeFraisRepo.delete(id);
		return "Ligne de frais supprimées avec succès !";
	}
}

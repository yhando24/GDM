package fr.diginamic.notedefrais.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.diginamic.notedefrais.model.LigneDeFrais;
import fr.diginamic.notedefrais.model.NoteDeFrais;

public interface LigneDeFraisRepository extends JpaRepository<LigneDeFrais, Integer> {
	List<LigneDeFrais> findByNoteDeFrais(NoteDeFrais note);

	List<LigneDeFrais> findByDateAndNatureAndNoteDeFraisMissionId(LocalDate date, String nature, Integer idMission);

	Optional<NoteDeFrais> save(Optional<NoteDeFrais> frais);

	public LigneDeFrais findOne(Integer id);

	public void delete(Integer id);

}

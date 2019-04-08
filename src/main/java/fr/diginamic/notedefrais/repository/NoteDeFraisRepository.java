package fr.diginamic.notedefrais.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.diginamic.notedefrais.model.NoteDeFrais;

public interface NoteDeFraisRepository extends JpaRepository<NoteDeFrais, Integer> {
	Optional<NoteDeFrais> findByMissionId(Integer id);

	public NoteDeFrais findOne(Integer id);
}

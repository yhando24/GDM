package fr.diginamic.kindversion.model;

import java.util.List;

import fr.diginamic.kind.model.Kind;
import fr.diginamic.kind.model.KindDTO;

public interface MapperKindVersionService {

	KindVersionDTO toDTO(KindVersion kind);
	

	List<KindVersionDTO> toDTOs(List<KindVersion> kind);

	KindVersion toEntity(KindVersionDTO kindVersionDTO);

	List<KindVersion> toEntitys(List<KindVersionDTO> kindVersionDTO);
}

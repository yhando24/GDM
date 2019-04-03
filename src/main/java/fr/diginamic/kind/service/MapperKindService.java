package fr.diginamic.kind.service;

import java.util.List;

import org.mapstruct.Mapper;

import fr.diginamic.kind.model.Kind;
import fr.diginamic.kind.model.KindDTO;

@Mapper(componentModel = "spring", uses = {})
public interface MapperKindService {

	KindDTO toDTO(Kind kind);

	List<KindDTO> toDTOs(List<Kind> kind);

	Kind toEntity(KindDTO kindDTO);

	List<Kind> toEntitys(List<KindDTO> kindDTO);

}

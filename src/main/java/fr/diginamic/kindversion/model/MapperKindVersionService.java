package fr.diginamic.kindversion.model;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface MapperKindVersionService {

	KindVersionDTO toDTO(KindVersion kind);

	List<KindVersionDTO> toDTOs(List<KindVersion> kind);

	KindVersion toEntity(KindVersionDTO kindVersionDTO);

	List<KindVersion> toEntitys(List<KindVersionDTO> kindVersionDTO);
}

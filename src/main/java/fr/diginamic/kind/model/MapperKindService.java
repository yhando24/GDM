package fr.diginamic.kind.model;

import java.util.List;

import org.mapstruct.Mapper;

import fr.diginamic.user.model.User;
import fr.diginamic.user.model.UserDTO;

@Mapper(componentModel = "spring", uses = {})
public interface MapperKindService {
	
	KindDTO toDTO(Kind kind);
	

	List<KindDTO> toDTOs(List<Kind> kind);

	Kind toEntity(KindDTO kindDTO);

	List<Kind> toEntitys(List<KindDTO> kindDTO);

}

package fr.diginamic.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.diginamic.kind.service.MapperKindService;
import fr.diginamic.user.model.User;
import fr.diginamic.user.model.UserDTO;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDTO userToUserDTO(User user);
	User userDTOToUser(UserDTO userDTO);

}

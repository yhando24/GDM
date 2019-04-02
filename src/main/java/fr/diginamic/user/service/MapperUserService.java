package fr.diginamic.user.service;

import java.util.List;

import org.mapstruct.Mapper;

import fr.diginamic.user.model.User;
import fr.diginamic.user.model.UserDTO;

@Mapper(componentModel = "spring", uses = {})
public interface MapperUserService {

	UserDTO toDTO(User user);

	List<UserDTO> toDTOs(List<User> user);

	User toEntity(UserDTO userDTO);

	List<User> toEntitys(List<UserDTO> userDTO);
}
package fr.diginamic.mission.service;

import java.util.List;

import org.mapstruct.Mapper;

import fr.diginamic.kind.service.MapperKindService;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionDTO;


@Mapper(componentModel = "spring", uses = {MapperKindService.class})
public interface MapperMissionService {

	MissionDTO toDTO(Mission mission);

	List<MissionDTO> toDTOs(List<Mission> mission);

	Mission toEntity(MissionDTO missionDTO);

	List<Mission> toEntitys(List<MissionDTO> missionDTO);
	
}

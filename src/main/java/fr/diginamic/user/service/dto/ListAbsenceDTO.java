package fr.diginamic.user.service.dto;

import java.io.Serializable;
import java.util.List;

public class ListAbsenceDTO implements Serializable {

	private static final long serialVersionUID = 7914425519604519179L;
	List<AbsenceDTO> abs;

	public List<AbsenceDTO> getAbs() {
		return abs;
	}

	public void setAbs(List<AbsenceDTO> abs) {
		this.abs = abs;
	}

	@Override
	public String toString() {
		return "ListAbsenceDTO [abs=" + abs + "]";
	}

	
	
}

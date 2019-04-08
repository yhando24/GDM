package fr.diginamic.kind.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoricDTO implements Serializable {

	private static final long serialVersionUID = 3467351442722605915L;

	@NotBlank
	private Date timestamp;

	private KindDTO kind;

	public HistoricDTO() {
		super();
	}

	public HistoricDTO(@NotBlank Date timestamp, KindDTO kind) {
		super();
		this.timestamp = timestamp;
		this.kind = kind;
	}

}

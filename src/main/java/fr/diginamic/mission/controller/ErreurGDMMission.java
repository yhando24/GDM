package fr.diginamic.mission.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.diginamic.mission.exception.ControllerMissionException;

@ControllerAdvice
public class ErreurGDMMission {

	@ExceptionHandler(ControllerMissionException.class)
	public ResponseEntity<?> erreur(Throwable ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}

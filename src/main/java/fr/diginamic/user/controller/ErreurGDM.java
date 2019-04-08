package fr.diginamic.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.diginamic.user.exception.ControllerUserException;

@ControllerAdvice
public class ErreurGDM {

	@ExceptionHandler(ControllerUserException.class)
	public ResponseEntity<?> erreur(Throwable ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

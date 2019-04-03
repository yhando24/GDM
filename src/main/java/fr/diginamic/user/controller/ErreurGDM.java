package fr.diginamic.user.controller;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErreurGDM extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> erreur(ConstraintViolationException ex, WebRequest request) {
		String bodyOfResponse = "L'email est déjà utilisé.";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR, request);

	}
}

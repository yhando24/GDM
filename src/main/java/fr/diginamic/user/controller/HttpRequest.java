package fr.diginamic.user.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.diginamic.configuration.JWTFilter;
import fr.diginamic.configuration.TokenProvider;
import fr.diginamic.user.dto.RecupLogin;
import fr.diginamic.user.model.User;
import fr.diginamic.user.model.UserDTO;
import fr.diginamic.user.service.UserService;



public class HttpRequest {
	
	HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://www.oxiane.com/")).GET().build();
	
	HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://www.oxiane.com/api/formations"))
	.setHeader("Content-Type", "application/json").POST(BodyPublishers.ofString("{"cle1":"valeur1", "cle2":"valeur2"}"))
	        .build();

}

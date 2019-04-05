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
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;


public class HttpClient {
	
	HttpClient client = HttpClient.newHttpClient();

	HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
	
	HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).proxy(ProxySelector.of(new InetSocketAddress("proxy.oxiane.com", 80))).build();

	HttpClient client = HttpClientBuilder.create().build();
    HttpUriRequest httpUriRequest = new HttpGet("URL");

    HttpResponse response = client.execute(httpUriRequest);
    System.out.println(response);
}

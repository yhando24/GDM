package fr.diginamic.user.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import fr.diginamic.user.service.dto.AbsenceDTO;
import fr.diginamic.user.service.dto.ListAbsenceDTO;


public class GetService {
	

	static final String URL_ABSENCES = "http://localhost:8081/api/absences";
	
	public static void main(String[] args) {

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");
			HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		  RestTemplate restTemplate = new RestTemplate();
		  
		  ResponseEntity<ListAbsenceDTO> response = restTemplate.exchange(URL_ABSENCES, 
	                HttpMethod.GET, entity, ListAbsenceDTO.class);
		  System.out.println(response.getBody().toString());
	}
}

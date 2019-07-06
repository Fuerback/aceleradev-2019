package com.codenation.aceleradev.desafio.client;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class CodeNationClient {

	private final String token;

	public CodeNationClient(String token) {
		this.token = token;
	}

	public String submitAnswer(String filePath) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType( MediaType.MULTIPART_FORM_DATA );

		MultiValueMap<String, Object> body
				= new LinkedMultiValueMap<>();
		body.add( "answer", new FileSystemResource( filePath ) );

		HttpEntity<MultiValueMap<String, Object>> requestEntity
				= new HttpEntity<>( body, headers );

		String serverUrl = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=" + token;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate
				.postForEntity( serverUrl, requestEntity, String.class );

		return response.getBody();
	}

	public String requestData() {
		String serverUrl = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=" + token;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity( serverUrl, String.class );
		return response.getBody();
	}

}

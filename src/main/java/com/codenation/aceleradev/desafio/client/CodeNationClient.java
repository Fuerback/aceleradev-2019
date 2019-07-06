package com.codenation.aceleradev.desafio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

	public String requestData() throws IOException {
		URL url_status = new URL(
				"https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=" + token );
		HttpURLConnection connection = (HttpURLConnection) url_status.openConnection();
		connection.setRequestMethod( "GET" );
		BufferedReader statusResponse = new BufferedReader(
				new InputStreamReader( connection.getInputStream() ) );

		String response = "";
		while (statusResponse.ready()) {
			response += statusResponse.readLine();
		}
		statusResponse.close();
		return response;
	}

}

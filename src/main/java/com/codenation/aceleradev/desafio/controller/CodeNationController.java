package com.codenation.aceleradev.desafio.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codenation.aceleradev.desafio.service.ChallengeData;

@RestController
@RequestMapping(value = "codenation", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodeNationController {

	@Autowired
	private ChallengeData challengeData;

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String getChallengeData(@RequestParam String token) throws IOException {
		return challengeData.request( token );
	}

	@RequestMapping(value = "/data", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String submitAnswer(@RequestParam String token, @RequestParam("answer") MultipartFile answer)
			throws IOException {
		return challengeData.submit( token, answer );
	}
}

package com.codenation.aceleradev.desafio.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codenation.aceleradev.desafio.dtos.TransformDTO;
import com.codenation.aceleradev.desafio.service.CesarTransform;
import com.codenation.aceleradev.desafio.service.ChallengeData;
import com.codenation.aceleradev.desafio.service.Sha1;

@RestController
@RequestMapping(value = "codenation", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodeNationController {

	@Autowired
	private ChallengeData challengeData;

	@Autowired
	private CesarTransform cesarTransform;

	@Autowired
	private Sha1 sha1;

	@RequestMapping(value = "{token}/data", method = RequestMethod.GET)
	public String getChallengeData(@PathVariable String token) throws IOException {
		return challengeData.request( token );
	}

	@RequestMapping(value = "encrypt", method = RequestMethod.GET)
	public String encrypt(@RequestBody TransformDTO transformDTO) {
		return cesarTransform.encrypt( transformDTO.getShift(), transformDTO.getText() );
	}

	@RequestMapping(value = "decrypt", method = RequestMethod.GET)
	public String decrypt(@RequestBody TransformDTO transformDTO) {
		return cesarTransform.decrypt( transformDTO.getShift(), transformDTO.getText() );
	}

	@RequestMapping(value = "sha1", method = RequestMethod.GET)
	public String getSha1Code(@RequestBody TransformDTO transformDTO) {
		return sha1.transform( transformDTO.getText() );
	}

	@RequestMapping(value = "{token}/data", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String submitAnswer(@PathVariable String token) {
		return challengeData.submit( token );
	}
}

package com.codenation.aceleradev.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codenation.aceleradev.desafio.dtos.TransformDTO;
import com.codenation.aceleradev.desafio.service.CesarCypher;
import com.codenation.aceleradev.desafio.service.Sha1;

@RestController
@RequestMapping(value = "transform")
public class TransformController {

	@Autowired
	private CesarCypher cesarCypher;

	@Autowired
	private Sha1 sha1;

	@RequestMapping(value = "encrypt", method = RequestMethod.GET)
	public String encrypt(@RequestBody TransformDTO transformDTO) {
		return cesarCypher.encrypt( transformDTO.getShift(), transformDTO.getText() );
	}

	@RequestMapping(value = "decrypt", method = RequestMethod.GET)
	public String decrypt(@RequestBody TransformDTO transformDTO) {
		return cesarCypher.decrypt( transformDTO.getShift(), transformDTO.getText() );
	}

	@RequestMapping(value = "sha1", method = RequestMethod.GET)
	public String getSha1Code(@RequestBody TransformDTO transformDTO) {
		return sha1.transform( transformDTO.getText() );
	}
}

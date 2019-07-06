package com.codenation.aceleradev.desafio.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codenation.aceleradev.desafio.service.Challenge;

@RestController
@RequestMapping(value = "challenge")
public class ChallengeController {

	@Autowired
	private Challenge challenge;

	@RequestMapping(method = RequestMethod.POST)
	public String challenge(@RequestParam String token) throws IOException {
		return challenge.execute( token );
	}
}

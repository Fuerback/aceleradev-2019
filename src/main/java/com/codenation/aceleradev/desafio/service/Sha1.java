package com.codenation.aceleradev.desafio.service;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.stereotype.Service;

@Service
public class Sha1 {

	public String transform(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance( "SHA-1" );

			byte[] messageDigest = md.digest( text.getBytes() );

			BigInteger no = new BigInteger( 1, messageDigest );

			String hashtext = no.toString( 16 );

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}

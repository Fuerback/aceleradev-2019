package com.codenation.aceleradev.desafio.service;

import org.springframework.stereotype.Service;

@Service
public class CesarCypher {

	public String encrypt(int shift, String text) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < text.length(); i++) {
			if (Character.isLetter( text.charAt( i ) )) {
				if (Character.isUpperCase( text.charAt( i ) )) {
					char ch = (char) (((int) text.charAt( i ) +
							shift - 65) % 26 + 65);
					result.append( ch );
				} else {
					char ch = (char) (((int) text.charAt( i ) +
							shift - 97) % 26 + 97);
					result.append( ch );
				}
			} else {
				result.append( text.charAt( i ) );
			}
		}

		System.out.println( result );
		return result.toString();
	}

	public String decrypt(int shift, String text) {
		shift = 26 - shift;
		return encrypt( shift, text );
	}
}

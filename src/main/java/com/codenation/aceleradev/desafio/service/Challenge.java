package com.codenation.aceleradev.desafio.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codenation.aceleradev.desafio.client.CodeNationClient;

@Service
public class Challenge {

	public static final String ANSWER_FILE_PATH = "/home/felipe/Code/desafio/src/main/java/com/codenation/aceleradev/desafio/files/answer/answer.json";
	@Autowired
	private CodeNation codeNation;

	@Autowired
	private CesarCypher cesarCypher;

	@Autowired
	private Sha1 sha1;

	public String execute(String token) throws IOException {
		String requestText = codeNation.request( token );

		JSONObject jsonObject = new JSONObject( requestText );

		String decryptText = cesarCypher
				.decrypt( jsonObject.getInt( "numero_casas" ), jsonObject.getString( "cifrado" ) );
		String sha1Code = sha1.transform( decryptText );

		jsonObject.put( "decifrado", decryptText );
		jsonObject.put( "resumo_criptografico", sha1Code );

		File newFile = new File(
				ANSWER_FILE_PATH );
		newFile.createNewFile();
		FileOutputStream fos = new FileOutputStream( newFile );
		fos.write( jsonObject.toString().getBytes() );
		fos.close();

		CodeNationClient client = new CodeNationClient( token );
		return client.submitAnswer( ANSWER_FILE_PATH );
	}
}

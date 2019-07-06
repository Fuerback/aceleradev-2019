package com.codenation.aceleradev.desafio.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.springframework.stereotype.Service;

import com.codenation.aceleradev.desafio.client.CodeNationClient;

@Service
public class ChallengeData {

	public String submit(String token) {
		CodeNationClient client = new CodeNationClient( token );
		return client.submitAnswer(
				"/home/felipe/Code/desafio/src/main/java/com/codenation/aceleradev/desafio/files/answer.json" );
	}

	public String request(String token) throws IOException {
		CodeNationClient client = new CodeNationClient( token );

		String response;

		File fout = new File(
				"/home/felipe/Code/desafio/src/main/java/com/codenation/aceleradev/desafio/files/answer.json" );

		response = client.requestData();

		if (!fout.exists()) {
			writeInFile( response, fout );
		}

		return response;
	}

	private void writeInFile(String response, File fout) throws IOException {
		FileOutputStream fos = new FileOutputStream( fout );

		BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( fos ) );

		bufferedWriter.write( response );
		bufferedWriter.close();
	}
}

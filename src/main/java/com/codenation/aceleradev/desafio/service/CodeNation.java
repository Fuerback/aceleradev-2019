package com.codenation.aceleradev.desafio.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codenation.aceleradev.desafio.client.CodeNationClient;

@Service
public class CodeNation {

	private static final String REQUEST_FILE_PATH = "./src/main/java/com/codenation/aceleradev/desafio/files/request/answer.json";
	private static final String ANSWER_FILE_PATH = "./src/main/java/com/codenation/aceleradev/desafio/files/answer/answer.json";

	public String submit(String token, MultipartFile answer) throws IOException {
		CodeNationClient client = new CodeNationClient( token );

		createNewFile( answer );

		return client.submitAnswer(
				ANSWER_FILE_PATH );
	}

	private void createNewFile(MultipartFile answer) throws IOException {
		File newFile = new File(
				ANSWER_FILE_PATH );
		newFile.createNewFile();
		FileOutputStream fos = new FileOutputStream( newFile );
		fos.write( answer.getBytes() );
		fos.close();
	}

	public String request(String token) throws IOException {
		CodeNationClient client = new CodeNationClient( token );

		String response;

		File fout = new File(
				REQUEST_FILE_PATH );

		response = client.requestData();

		if (!fout.exists()) {
			bufferWriteInFile( response, fout );
		}

		return response;
	}

	private void bufferWriteInFile(String response, File fout) throws IOException {
		FileOutputStream fos = new FileOutputStream( fout );

		BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( fos ) );

		bufferedWriter.write( response );
		bufferedWriter.close();
	}
}

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

	public static final String TEMP_PATH = "/home/felipe/Code/desafio/src/main/java/com/codenation/aceleradev/desafio/files/";
	public static final String ANSWER_PATH = TEMP_PATH + "answer/";
	public static final String NAME_FILE = "answer.json";

	public String submit(String token, MultipartFile answer) throws IOException {
		CodeNationClient client = new CodeNationClient( token );

		createNewFile( answer );

		return client.submitAnswer(
				ANSWER_PATH + answer.getOriginalFilename() );
	}

	private void createNewFile(MultipartFile answer) throws IOException {
		File newFile = new File(
				ANSWER_PATH + answer.getOriginalFilename() );
		newFile.createNewFile();
		FileOutputStream fos = new FileOutputStream( newFile );
		fos.write( answer.getBytes() );
		fos.close();
	}

	public String request(String token) throws IOException {
		CodeNationClient client = new CodeNationClient( token );

		String response;

		File fout = new File(
				TEMP_PATH + NAME_FILE );

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

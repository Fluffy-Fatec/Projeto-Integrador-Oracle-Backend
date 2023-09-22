package com.fluffy.backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.fluffy.backend.service.CsvService;
import com.fluffy.backend.util.CsvUtils;
import com.opencsv.exceptions.CsvValidationException;

public class CsvControllerTest {

	@InjectMocks
	private CsvController csvController;

	@Mock
	private CsvService csvService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUploadCsvSuccess() throws IOException, CsvValidationException {
		// Crie um MockMultipartFile com nome de arquivo, nome do campo e conteúdo
		String csvContent = "Product Name,Product Value,Product Type,PC Quantity,PC Measurement,PC Turn,Command Number,PC Datetime Order\n"
				+ "Product 1,10.0,Type A,5.0,cm,1,123,2023-09-18 10:00:00\n";
		MultipartFile mockFile = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());

		ResponseEntity<String> response = csvController.uploadCsv(mockFile);

		// Verifique se a resposta é OK
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Dados CSV importados com sucesso.", response.getBody());
	}

	@Test
	public void testUploadCsvEmptyFile() throws IOException {
		MultipartFile emptyFile = new MockMultipartFile("test.csv", new byte[0]);

		ResponseEntity<String> response = csvController.uploadCsv(emptyFile);

		verify(csvService, never()).processCsvData(any());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("O arquivo CSV está vazio.", response.getBody());
	}

	@Test
	public void testUploadCsvError() throws IOException, CsvValidationException {
		// Crie um MockMultipartFile com um nome de arquivo válido e conteúdo de CSV
		String csvContent = "Product Name,Product Value,Product Type\n" // Formato CSV inválido
				+ "Product 1,10.0,Type A\n";
		MultipartFile mockFile = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());

		ResponseEntity<String> response = csvController.uploadCsv(mockFile);

		// Verifique se a resposta é um erro 500 e contém a mensagem de exceção esperada
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertTrue(response.getBody().contains("Erro ao importar dados CSV"));
	}

}

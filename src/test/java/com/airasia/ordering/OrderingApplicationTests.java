package com.airasia.ordering;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.airasia.ordering.mapper.utils.Constants;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class OrderingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testSuccessScenario() {
		File file = new File(this.getClass().getClassLoader().getResource(Constants.SUCCESS_JSON_FILE).getFile());
		String fileContent = getFileContents(file.getAbsolutePath());
		try {
			mockMvc.perform(post(Constants.ORDER_CREATE_URL).contentType(MediaType.APPLICATION_JSON)
					.content(fileContent).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testEmptyRequestError() {
		String fileContent = "[]";
		try {
			mockMvc.perform(post(Constants.ORDER_CREATE_URL).contentType(MediaType.APPLICATION_JSON)
					.content(fileContent).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	void testMissingNameError() {
		File file = new File(this.getClass().getClassLoader().getResource(Constants.MISSING_NAME).getFile());
		String fileContent = getFileContents(file.getAbsolutePath());
		try {
			mockMvc.perform(post(Constants.ORDER_CREATE_URL).contentType(MediaType.APPLICATION_JSON)
					.content(fileContent).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
			.andExpect(content().string(containsString(HttpStatus.BAD_REQUEST.name())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testMissingEmilIdError() {
		File file = new File(this.getClass().getClassLoader().getResource(Constants.MISSING_EMAIL).getFile());
		String fileContent = getFileContents(file.getAbsolutePath());
		try {
			mockMvc.perform(post(Constants.ORDER_CREATE_URL).contentType(MediaType.APPLICATION_JSON)
					.content(fileContent).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
			.andExpect(content().string(containsString(HttpStatus.BAD_REQUEST.name())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	void testMissingHotelIDError() {
		File file = new File(this.getClass().getClassLoader().getResource(Constants.MISSING_HOTEL_ID).getFile());
		String fileContent = getFileContents(file.getAbsolutePath());
		try {
			mockMvc.perform(post(Constants.ORDER_CREATE_URL).contentType(MediaType.APPLICATION_JSON)
					.content(fileContent).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
			.andExpect(content().string(containsString(HttpStatus.BAD_REQUEST.name())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testInvalidDateFormatError() {
		File file = new File(this.getClass().getClassLoader().getResource(Constants.INVALID_DATE).getFile());
		String fileContent = getFileContents(file.getAbsolutePath());
		try {
			mockMvc.perform(post(Constants.ORDER_CREATE_URL).contentType(MediaType.APPLICATION_JSON)
					.content(fileContent).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
			.andExpect(content().string(containsString(HttpStatus.BAD_REQUEST.name())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testMissingRoomDtlsError() {
		File file = new File(this.getClass().getClassLoader().getResource(Constants.MISSING_ROOM_DTLS).getFile());
		String fileContent = getFileContents(file.getAbsolutePath());
		try {
			mockMvc.perform(post(Constants.ORDER_CREATE_URL).contentType(MediaType.APPLICATION_JSON)
					.content(fileContent).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
			.andExpect(content().string(containsString(HttpStatus.BAD_REQUEST.name())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testMissingRoomIDError() {
		File file = new File(this.getClass().getClassLoader().getResource(Constants.MISSING_ROOM_ID).getFile());
		String fileContent = getFileContents(file.getAbsolutePath());
		try {
			mockMvc.perform(post(Constants.ORDER_CREATE_URL).contentType(MediaType.APPLICATION_JSON)
					.content(fileContent).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
			.andExpect(content().string(containsString(HttpStatus.BAD_REQUEST.name())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testInvalidRoomIDError() {
		File file = new File(this.getClass().getClassLoader().getResource(Constants.INVALID_ROOM_ID).getFile());
		String fileContent = getFileContents(file.getAbsolutePath());
		try {
			mockMvc.perform(post(Constants.ORDER_CREATE_URL).contentType(MediaType.APPLICATION_JSON)
					.content(fileContent).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testInvalidHotelIDError() {
		File file = new File(this.getClass().getClassLoader().getResource(Constants.INVALID_HOTEL_ID).getFile());
		String fileContent = getFileContents(file.getAbsolutePath());
		try {
			mockMvc.perform(post(Constants.ORDER_CREATE_URL).contentType(MediaType.APPLICATION_JSON)
					.content(fileContent).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param filePath
	 * @return
	 */
	private static String getFileContents(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}
}

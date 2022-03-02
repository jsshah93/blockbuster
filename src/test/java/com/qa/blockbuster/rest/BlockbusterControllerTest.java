package com.qa.blockbuster.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.blockbuster.domain.Blockbuster;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:blockbuster-schema.sql",
		"classpath:blockbuster-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)


@ActiveProfiles("test") 
public class BlockbusterControllerTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper map;
	
	@Test
	void createTest() throws Exception {
		 Blockbuster newM = new Blockbuster ("Spider-man", "2021-12-25", 10);
		 String newMJSON = this.map.writeValueAsString(newM);
		 RequestBuilder mockRequest = post("/createMovie").contentType(MediaType.APPLICATION_JSON).content(newMJSON);
		 
		 Blockbuster savedM = new Blockbuster(2L, "Spider-man", "2021-12-25", 10);
		 
		 String savedMJSON = this.map.writeValueAsString(savedM);
		 
		 ResultMatcher matchStatus = status().isCreated();
		 ResultMatcher matchBody = content().json(savedMJSON);
		 
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);	
	}
	
	@Test
	void readTest() throws Exception {
		Blockbuster readM = new Blockbuster(1L, "Spider-man", "2021-12-25", 10);
		List<Blockbuster> allMovie = List.of(readM);
		String readMovieJSON = this.map.writeValueAsString(allMovie);
		
		RequestBuilder readReq = get("/getMovie");
		
		ResultMatcher status = status().isOk();
		ResultMatcher body = content().json(readMovieJSON);
		
		this.mock.perform(readReq).andExpect(status).andExpect(body);
		}
	
	@Test
	void updateTest() throws Exception {
		Blockbuster updateMovie = new Blockbuster("hit man", "2020-01-25", 10);
		String updateMovieJSON = this.map.writeValueAsString(updateMovie);
		Long IDupdate = 1L;
		
		RequestBuilder updateReq = put("/updateMovie/" + IDupdate).contentType(MediaType.APPLICATION_JSON).content(updateMovieJSON);
		
		Blockbuster updatedMovie = new Blockbuster(1L, "hit man", "2020-01-25", 10);
		String updatedMovieJSON = this.map.writeValueAsString(updatedMovie);
		
		ResultMatcher updatedStatus = status().isOk();
		ResultMatcher updatedBody = content().json(updatedMovieJSON);
		
		this.mock.perform(updateReq).andExpect(updatedStatus).andExpect(updatedBody);		
	}
	
	@Test
	void deleteTest() throws Exception {
		Blockbuster deleteMovie = new Blockbuster(1L, "Spider-man", "2021-12-25", 10);
		String deleteMovieJSON = this.map.writeValueAsString(deleteMovie);
		
		Long remID = 1L;
		RequestBuilder delMovie = delete("/deleteMovie/" + remID);
		ResultMatcher Status = status().isOk();
		ResultMatcher body = content().json(deleteMovieJSON);
		
		this.mock.perform(delMovie).andExpect(Status).andExpect(body);
		
	}
	
}

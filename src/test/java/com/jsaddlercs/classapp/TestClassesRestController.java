package com.jsaddlercs.classapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jsaddlercs.classapp.model.ClassesModel;
import com.jsaddlercs.classapp.response.ClassesResponse;
import com.jsaddlercs.classapp.response.PingResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TestClassesRestController {

	public static final Logger LOG = LoggerFactory.getLogger(TestClassesRestController.class);
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Configuration
	@ComponentScan(basePackages={"com.jsaddlercs.classapp"})
	public static class TestInfrastructureConfig
	{
		
	}
	
	@BeforeAll
	public static void beforeAll() { 
		LOG.warn("**TESTS**");
	}
	
	@AfterAll
	public static void afterAll() { 
		LOG.warn("**END TESTS**");
	}
	
	@Test
	public void test_pingResponseReturnsSuccessTrue() { 
		ResponseEntity<PingResponse> pingResponse = testRestTemplate.getForEntity("/api/ping", PingResponse.class);
		assertThat(pingResponse.getBody()).isExactlyInstanceOf(PingResponse.class);
		assertThat(pingResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(pingResponse.getBody().getSuccess()).isEqualTo(true);
	}
	
	@Test
	public void testClasses_containsOutput() { 
		ResponseEntity<?> postResponse = testRestTemplate.getForEntity(
				"/api/classes", 
				ClassesResponse.class);
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(postResponse.getBody()).isExactlyInstanceOf(ClassesResponse.class);
		ClassesResponse pr = (ClassesResponse)postResponse.getBody();
		assertThat(pr.getClasses().size()).isGreaterThan(0);
	}
	
	/*
	 * "entryId": "1",
	 * "classesName": "SENG 2000",
	 */
	@Test
	public void testClasses_containsSENG1000ClassInfo() { 
		ResponseEntity<?> postResponse = testRestTemplate.getForEntity(
				"/api/classes", 
				ClassesResponse.class);
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(postResponse.getBody()).isExactlyInstanceOf(ClassesResponse.class);
		ClassesResponse pr = (ClassesResponse)postResponse.getBody();
		ClassesModel cm1 = pr.getClasses().get(0);
		assertThat(cm1.getEntryId()).isEqualTo("1");
		assertThat(cm1.getClassesName()).contains("SENG 2000");
	}
	
	@ParameterizedTest
	@CsvSource({"2020"})
	public void testClasses_goodYear_containsOutput(String year) { 
		ResponseEntity<?> postResponse = testRestTemplate.getForEntity(
				"/api/classes", 
				ClassesResponse.class,
				year);
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(postResponse.getBody()).isExactlyInstanceOf(ClassesResponse.class);
		ClassesResponse pr = (ClassesResponse)postResponse.getBody();
		assertThat(pr.getClasses().size()).isGreaterThan(0);
	}
	

}

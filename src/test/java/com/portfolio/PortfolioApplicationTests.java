package com.portfolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PortfolioApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String getBaseUrl() {
		return "http://localhost:" + port;
	}

	@Test
	void contextLoads() {
		// Verifies Spring Boot application context loads successfully
		assertNotNull(restTemplate);
	}

	@Test
	void testGetSkillsEndpoint() {
		String url = getBaseUrl() + "/api/skills";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody().contains("\"name\""));
	}

	@Test
	void testGetProjectsEndpoint() {
		String url = getBaseUrl() + "/api/projects";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void testGetAboutEndpoint() {
		String url = getBaseUrl() + "/api/about/primary";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void testGetExperienceEndpoint() {
		String url = getBaseUrl() + "/api/experience";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void testGetFeaturedProjects() {
		String url = getBaseUrl() + "/api/projects/featured";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testGetSkillsByCategory() {
		String url = getBaseUrl() + "/api/skills/category/Backend";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}

package com.w2m.zeraus.supher;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.w2m.zeraus.supher.model.Superhero;

@SpringBootTest(classes = SuperherosApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class SuperherosApplicationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void findAllTest() {
		ResponseEntity<Superhero[]> response = restTemplate.getForEntity("http://localhost:" + port + "/superheros",
				Superhero[].class);

		assertEquals(4, Arrays.asList(response.getBody()).size());
	}

	@Test
	void findByIdTest() {
		Long id = 3L;

		ResponseEntity<Superhero> response = restTemplate.getForEntity("http://localhost:" + port + "/superhero/" + id,
				Superhero.class);

		assertEquals(id, response.getBody().getId());
	}

	@Test
	void findByNameTest() {
		String name = "ana";

		ResponseEntity<Superhero[]> response = restTemplate
				.getForEntity("http://localhost:" + port + "/superhero?name=" + name, Superhero[].class);

		assertTrue(Arrays.asList(response.getBody()).get(0).getName().contains(name));
	}

	@Test
	void updateTest() {
		Long id = 3L;
		ResponseEntity<Superhero> supher = restTemplate.getForEntity("http://localhost:" + port + "/superhero/" + id,
				Superhero.class);

		supher.getBody().setName("Capitana");

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "3");
		restTemplate.put("http://localhost:" + port + "/superheros", supher, params);

		ResponseEntity<Superhero> response = restTemplate.getForEntity("http://localhost:" + port + "/superhero/" + id,
				Superhero.class);

		assertEquals(id, response.getBody().getId());
	}

	@Test
	void deleteByIdTest() {
		Long id = 3L;
		restTemplate.delete("http://localhost:" + port + "/superhero/" + id);

		ResponseEntity<Superhero> response = restTemplate.getForEntity("http://localhost:" + port + "/superheros/" + id,
				Superhero.class);

		assertNull(response.getBody());
	}

}

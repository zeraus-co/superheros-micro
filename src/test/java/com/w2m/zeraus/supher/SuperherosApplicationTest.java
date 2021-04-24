package com.w2m.zeraus.supher;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.w2m.zeraus.supher.service.model.SuperheroVO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = SuperherosApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class SuperherosApplicationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	void findAllTest() {
		ResponseEntity<SuperheroVO[]> response = restTemplate.getForEntity("http://localhost:" + port + "/superheros",
				SuperheroVO[].class);

		assertEquals(4, Arrays.asList(response.getBody()).size());
	}

	@Test
	@Order(2)
	void findByIdTest() {
		Long id = 3L;

		ResponseEntity<SuperheroVO> response = restTemplate.getForEntity("http://localhost:" + port + "/superhero/" + id,
				SuperheroVO.class);

		assertEquals(id, response.getBody().getId());
	}

	@Test
	@Order(3)
	void findByNameTest() {
		String name = "ana";

		ResponseEntity<SuperheroVO[]> response = restTemplate
				.getForEntity("http://localhost:" + port + "/superhero?name=" + name, SuperheroVO[].class);

		assertTrue(Arrays.asList(response.getBody()).get(0).getName().contains(name));
	}

	@Test
	@Order(4)
	void updateTest() {
		Long id = 3L;
		ResponseEntity<SuperheroVO> supher = restTemplate.getForEntity("http://localhost:" + port + "/superhero/" + id,
				SuperheroVO.class);

		supher.getBody().setName("Capitana");

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "3");
		restTemplate.put("http://localhost:" + port + "/superheros", supher, params);

		ResponseEntity<SuperheroVO> response = restTemplate.getForEntity("http://localhost:" + port + "/superhero/" + id,
				SuperheroVO.class);

		assertEquals(id, response.getBody().getId());
	}

	@Test
	@Order(5)
	void deleteByIdTest() {
		Long id = 3L;
		restTemplate.delete("http://localhost:" + port + "/superhero/" + id);

		ResponseEntity<SuperheroVO> response = restTemplate.getForEntity("http://localhost:" + port + "/superhero/" + id,
				SuperheroVO.class);

		assertNull(response.getBody());
	}

}

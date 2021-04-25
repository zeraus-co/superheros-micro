package com.w2m.zeraus.supher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.w2m.zeraus.supher.service.model.SuperheroVO;
import com.w2m.zeraus.supher.web.model.SuperheroTO;

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
		Map<String, Object> response = (Map<String, Object>) restTemplate
				.getForEntity("http://localhost:" + port + "/superheros", Map.class).getBody();

		List<SuperheroVO> supherList = (List<SuperheroVO>) response.get("supherList");
		assertNotNull(supherList);
		assertEquals(3, supherList.size());
		assertEquals(4, response.get("totalElements"));
	}

	@Test
	@Order(2)
	void findAllWithPaginationTest() {
		Map<String, Object> response = (Map<String, Object>) restTemplate
				.getForEntity("http://localhost:" + port + "/superheros?pageNumber=1&pageSize=3", Map.class).getBody();

		List<SuperheroVO> supherList = (List<SuperheroVO>) response.get("supherList");
		assertNotNull(supherList);
		assertEquals(1, supherList.size());
		assertEquals(4, response.get("totalElements"));
	}

	@Test
	@Order(3)
	void findByIdTest() {
		Long id = 3L;

		ResponseEntity<SuperheroTO> response = restTemplate
				.getForEntity("http://localhost:" + port + "/superhero/" + id, SuperheroTO.class);

		assertEquals(id, response.getBody().getId());
	}

	@Test
	@Order(4)
	void findByNameTest() {
		String nameValue = "an";

		Map<String, Object> response = (Map<String, Object>) restTemplate
				.getForEntity("http://localhost:" + port + "/superhero?name=" + nameValue, Map.class).getBody();

		List<SuperheroVO> supherList = (List<SuperheroVO>) response.get("supherList");
		assertNotNull(supherList);
		assertEquals(3, supherList.size());
		assertEquals(3, response.get("totalElements"));
	}

	@Test
	@Order(5)
	void findByNameWithPaginationTest() {
		Map<String, Object> response = (Map<String, Object>) restTemplate
				.getForEntity("http://localhost:" + port + "/superhero?name=an&pageNumber=1&pageSize=2", Map.class)
				.getBody();

		List<SuperheroVO> supherList = (List<SuperheroVO>) response.get("supherList");
		assertNotNull(supherList);
		assertEquals(1, supherList.size());
		assertEquals(3, response.get("totalElements"));
	}

	@Test
	@Order(6)
	void updateTest() {
		Long id = 3L;
		ResponseEntity<SuperheroTO> supher = restTemplate.getForEntity("http://localhost:" + port + "/superhero/" + id,
				SuperheroTO.class);

		supher.getBody().setName("Capitana");

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "3");
		restTemplate.put("http://localhost:" + port + "/superheros", supher, params);

		ResponseEntity<SuperheroTO> response = restTemplate
				.getForEntity("http://localhost:" + port + "/superhero/" + id, SuperheroTO.class);

		assertEquals(id, response.getBody().getId());
	}

	@Test
	@Order(7)
	void deleteByIdTest() {
		Long id = 3L;
		restTemplate.delete("http://localhost:" + port + "/superhero/" + id);

		ResponseEntity<SuperheroTO> response = restTemplate
				.getForEntity("http://localhost:" + port + "/superhero/" + id, SuperheroTO.class);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

}

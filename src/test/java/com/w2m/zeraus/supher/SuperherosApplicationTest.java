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

		// Call to endpoint to find all
		Map<String, Object> response = (Map<String, Object>) restTemplate
				.getForEntity("http://localhost:" + port + "/superheros", Map.class).getBody();

		// Check the result
		List<SuperheroVO> supherList = (List<SuperheroVO>) response.get("supherList");
		assertNotNull(supherList);
		assertEquals(3, supherList.size());
		assertEquals(4, response.get("totalElements"));

	}

	@Test
	@Order(2)
	void findAllWithPaginationTest() {

		// Call method to find all with pagination
		Map<String, Object> response = (Map<String, Object>) restTemplate
				.getForEntity("http://localhost:" + port + "/superheros?pageNumber=1&pageSize=3", Map.class).getBody();

		// Check the result
		List<SuperheroVO> supherList = (List<SuperheroVO>) response.get("supherList");
		assertNotNull(supherList);
		assertEquals(1, supherList.size());
		assertEquals(4, response.get("totalElements"));

	}

	@Test
	@Order(3)
	void findByIdTest() {

		// Request data
		Long id = 3L;

		// Call method to find by id
		ResponseEntity<SuperheroTO> response = restTemplate
				.getForEntity("http://localhost:" + port + "/superhero/" + id, SuperheroTO.class);

		// Check the result
		assertEquals(id, response.getBody().getId());

	}

	@Test
	@Order(4)
	void findByNameTest() {

		// Request data
		String nameValue = "an";

		// Call method to find by name
		Map<String, Object> response = (Map<String, Object>) restTemplate
				.getForEntity("http://localhost:" + port + "/superhero?name=" + nameValue, Map.class).getBody();

		// Check the result
		List<SuperheroVO> supherList = (List<SuperheroVO>) response.get("supherList");
		assertNotNull(supherList);
		assertEquals(3, supherList.size());
		assertEquals(3, response.get("totalElements"));

	}

	@Test
	@Order(5)
	void findByNameWithPaginationTest() {

		// Call method to find by name with pagination
		Map<String, Object> response = (Map<String, Object>) restTemplate
				.getForEntity("http://localhost:" + port + "/superhero?name=an&pageNumber=1&pageSize=2", Map.class)
				.getBody();

		// Check the result
		List<SuperheroVO> supherList = (List<SuperheroVO>) response.get("supherList");
		assertNotNull(supherList);
		assertEquals(1, supherList.size());
		assertEquals(3, response.get("totalElements"));
	}

	@Test
	@Order(6)
	void updateTest() {

		// Request data to find
		Long id = 3L;

		// Call method to find by id
		ResponseEntity<SuperheroTO> supher = restTemplate.getForEntity("http://localhost:" + port + "/superhero/" + id,
				SuperheroTO.class);

		// Modify the name of super hero
		supher.getBody().setName("Capitana");

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "3");
		restTemplate.put("http://localhost:" + port + "/superheros", supher, params);

		// Call method to find by id
		ResponseEntity<SuperheroTO> response = restTemplate
				.getForEntity("http://localhost:" + port + "/superhero/" + id, SuperheroTO.class);

		// Check the result
		assertEquals(id, response.getBody().getId());

	}

	@Test
	@Order(7)
	void deleteByIdTest() {

		// Request data
		Long id = 3L;
		restTemplate.delete("http://localhost:" + port + "/superhero/" + id);

		// Call method to find by id
		ResponseEntity<SuperheroTO> response = restTemplate
				.getForEntity("http://localhost:" + port + "/superhero/" + id, SuperheroTO.class);

		// Check the result
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

	}

}

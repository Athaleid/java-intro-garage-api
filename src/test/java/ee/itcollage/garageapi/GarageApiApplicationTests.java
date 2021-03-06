package ee.itcollage.garageapi;

import ee.itcollage.garageapi.controller.Greeting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GarageApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void application_greets_with_greeting_world() {
		ResponseEntity<Greeting> entity = restTemplate.getForEntity("/greeting", Greeting.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		Greeting greeting = entity.getBody();
		assertNotNull(greeting);
		assertEquals("greeting World", greeting.getName());
	}

	@Test
	public void application_greets_with_custom_greeting_if_provided() {
		ResponseEntity<Greeting> entity = restTemplate.getForEntity("/greeting?name=Joseph", Greeting.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		Greeting greeting = entity.getBody();
		assertNotNull(greeting);
		assertEquals("greeting Joseph", greeting.getName());
	}

}


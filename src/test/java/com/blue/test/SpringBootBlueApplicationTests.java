package com.blue.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.blue.data.Basket;
import com.blue.data.Fruit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootBlueApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(SpringBootBlueApplicationTests.class);

	@Autowired
	private TestRestTemplate restTemplate;

	/** Same json string as Object<b>Writer</b> writes with @type in BasketController.getFruitList() */
	@Test
	public void testGetFruitList() {

		log.info("/getFruitList JSON: {}", restTemplate.getForObject("/getFruitList", String.class));
		// log: /getFruitList JSON: [{"@type":"Apple","wgt":1},{"@type":"Banana","wgt":2}]
	}

	/** Same json string as Object<b>Mapper</b> writes with @type in BasketController.getFruitBasket() */
	@Test
	public void testGetFruitBasket() {

		log.info("/getFruitBasket JSON: {}", restTemplate.getForObject("/getFruitBasket", String.class));
		// log: /getFruitBasket JSON: {"items":[{"@type":"Apple","wgt":3},{"@type":"Banana","wgt":4}]}
	}

	/** Same json string as Object<b>Mapper</b> writes without @type in BasketController.getBasketOfFruit().
	 * What I want is the string as Object<b>Writer</b> writes with @type */
	@Test
	public void testGetBasketOfFruit() {

		log.info("/getBasketOfFruit JSON: {}", restTemplate.getForObject("/getBasketOfFruit", String.class));
		// log: /getBasketOfFruit JSON: {"items":[{"wgt":5},{"wgt":6}]}
		ResponseEntity<Basket<Fruit>> response =
				restTemplate.exchange("/getBasketOfFruit",
						HttpMethod.GET, null, new ParameterizedTypeReference<Basket<Fruit>>() {});
		Basket<Fruit> basketOfFruit = response.getBody();
		log.info("basketOfFruit: {}", basketOfFruit);

	}

}

package com.blue.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootBlueApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(SpringBootBlueApplicationTests.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetFruitList() {

		log.info("/getFruitList JSON: {}", restTemplate.getForObject("/getFruitList", String.class));
	}

	@Test
	public void testGetBasketOfFruit() {

		log.info("/getBasketOfFruit JSON: {}", restTemplate.getForObject("/getBasketOfFruit", String.class));
	}

	@Test
	public void testGetFruitBasket() {

		log.info("/getFruitBasket JSON: {}", restTemplate.getForObject("/getFruitBasket", String.class));
	}

}

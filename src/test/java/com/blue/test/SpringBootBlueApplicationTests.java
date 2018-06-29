package com.blue.test;

import java.io.IOException;
import java.util.List;

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

import com.blue.data.Apple;
import com.blue.data.Basket;
import com.blue.data.Fruit;
import com.blue.data.FruitBasket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootBlueApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(SpringBootBlueApplicationTests.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testGetFruitBasket() {

		log.info("/getFruitBasket JSON: {}", restTemplate.getForObject("/getFruitBasket", String.class));

		ResponseEntity<FruitBasket> response = restTemplate.exchange("/getFruitBasket", HttpMethod.GET, null,
				new ParameterizedTypeReference<FruitBasket>() {
				});
		assertEquals(200, response.getStatusCodeValue());
	}

	@SuppressWarnings("unused")
	@Test// (expected = HttpMessageNotReadableException.class)
	public void testGetBasketOfFruit() {

		String getBasketOfFruitJson = restTemplate.getForObject("/getBasketOfFruit", String.class);
		log.info("/getBasketOfFruit JSON: {}", getBasketOfFruitJson);

		Basket<Fruit> basket;
		try {
			basket = mapper.readerFor(Basket.class).readValue(getBasketOfFruitJson);
			Basket<Fruit> basketOfFruit = basket;

//			JavaType inner = mapper.getTypeFactory().constructParametricType(ArrayList.class, Fruit.class);
//			JavaType outer = mapper.getTypeFactory().constructParametricType(Basket.class, Apple.class);
//			Object object = mapper.readValue(getBasketOfFruitJson, outer);
			List<Apple> fruitList = mapper.readValue("[{\"wgt\":6},{\"wgt\":7}]", TypeFactory.defaultInstance().constructCollectionType(List.class, Apple.class));

		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
//		Basket<Fruit> basketOfFruit = mapper.readValue(getBasketOfFruitJson, new TypeReference<Basket<Fruit>>() {});

		ResponseEntity<Basket<Fruit>> response = restTemplate.exchange("/getBasketOfFruit", HttpMethod.GET, null,
				new ParameterizedTypeReference<Basket<Fruit>>() {
				});
		assertEquals(200, response.getStatusCodeValue());
	}
}

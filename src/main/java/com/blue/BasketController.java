package com.blue;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blue.data.Apple;
import com.blue.data.Banana;
import com.blue.data.Basket;
import com.blue.data.Fruit;
import com.blue.data.FruitBasket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
@RequestMapping(path = "/")
public class BasketController {

	private static final Logger log = LoggerFactory.getLogger(BasketController.class);

	@Autowired
	private ObjectMapper mapper;

	/** <li>Object<b>Mapper</b>.writeValueAsString, @type is lost due to java type erasure.
	 *  <li>Object<b>Writer</b>.writeValueAsString, @type is kept */
	@GetMapping(path = "getFruitList")
	public List<Fruit> getFruitList() throws JsonProcessingException {

		List<Fruit> fruitList = Arrays.asList(new Apple(1), new Banana(2));
		log.info("List<Fruit> mapper.writeValueAsString: {}", mapper.writeValueAsString(fruitList));
		// log: List<Fruit> mapper.writeValueAsString: [{"wgt":1},{"wgt":2}]
		ObjectWriter writer = mapper.writerFor(new TypeReference<List<Fruit>>() {});
		log.info("List<Fruit> writer.writeValueAsString: {}", writer.writeValueAsString(fruitList));
		// log: List<Fruit> writer.writeValueAsString: [{"@type":"Apple","wgt":1},{"@type":"Banana","wgt":2}]
		return fruitList;
	}

	/** Object<b>Mapper</b>.writeValueAsString, with a wrapper of {@code List<Fruit>}, @type is kept */
	@GetMapping(path = "getFruitBasket")
	public FruitBasket getFruitBasket() throws JsonProcessingException {

		FruitBasket fruitBasket = new FruitBasket();
		fruitBasket.getItems().add(new Apple(3));
		fruitBasket.getItems().add(new Banana(4));
		log.info("FruitBasket mapper.writeValueAsString: {}", mapper.writeValueAsString(fruitBasket));
		// log: FruitBasket mapper.writeValueAsString: {"items":[{"type":"Apple","wgt":3},{"type":"Banana","wgt":4}]}
		return fruitBasket;
	}

	/** <li>Object<b>Mapper</b>.writeValueAsString, @type is lost due to java type erasure.
	 *  <li>Object<b>Writer</b>.writeValueAsString, @type is kept */
	@GetMapping(path = "getBasketOfFruit")
	public Basket<Fruit> getBasketOfFruit() throws JsonProcessingException {

		Basket<Fruit> basketOfFruit = new Basket<Fruit>();
		basketOfFruit.getItems().add(new Apple(5));
		basketOfFruit.getItems().add(new Banana(6));
		log.info("Basket<Fruit> mapper.writeValueAsString: {}", mapper.writeValueAsString(basketOfFruit));
		// log: Basket<Fruit> mapper.writeValueAsString: {"items":[{"wgt":5},{"wgt":6}]}
		ObjectWriter writer = mapper.writerFor(new TypeReference<Basket<Fruit>>() {});
		log.info("Basket<Fruit> writer.writeValueAsString: {}", writer.writeValueAsString(basketOfFruit));
		// log: Basket<Fruit> writer.writeValueAsString: {"items":[{"@type":"Apple","wgt":5},{"@type":"Banana","wgt":6}]}
		return basketOfFruit;
	}

}

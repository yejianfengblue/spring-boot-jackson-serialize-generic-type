package com.blue;

import java.util.ArrayList;
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

	@GetMapping(path = "getFruitList")
	public List<Fruit> getFruitList() throws JsonProcessingException {

		List<Fruit> fruitList = new ArrayList<Fruit>();
		fruitList.add(new Apple(500));
		fruitList.add(new Banana(600));

		log.info("fruitList mapper.writeValueAsString: {}", mapper.writeValueAsString(fruitList));

		return fruitList;
	}

	@GetMapping(path = "getFruitBasket")
	public FruitBasket getFruitBasket() throws JsonProcessingException {

		FruitBasket fruitBasket = new FruitBasket();
		fruitBasket.getItems().add(new Apple(1));
		fruitBasket.getItems().add(new Banana(2));

		log.info("FruitBasket mapper.writeValueAsString: {}", mapper.writeValueAsString(fruitBasket));

		return fruitBasket;
	}

	@GetMapping(path = "getBasketOfFruit")
	public Basket<Fruit> getBasketOfFruit() throws JsonProcessingException {

		Basket<Fruit> basketOfFruit = new Basket<Fruit>();
		basketOfFruit.getItems().add(new Apple(30));
		basketOfFruit.getItems().add(new Banana(40));

		log.info("Basket<Fruit> mapper.writeValueAsString: {}", mapper.writeValueAsString(basketOfFruit));
		ObjectWriter writer = mapper.writerFor(new TypeReference<Basket<Fruit>>() {
		});
		log.info("Basket<Fruit> writer.writeValueAsString: {}", writer.writeValueAsString(basketOfFruit));

		return basketOfFruit;
	}

}

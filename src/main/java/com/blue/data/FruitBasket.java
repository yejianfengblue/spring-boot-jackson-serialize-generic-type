package com.blue.data;

import java.util.ArrayList;
import java.util.List;

/**
 * A Basket to store Fruit
 *
 * @param <Fruit>
 */
public class FruitBasket {

	List<Fruit> items = new ArrayList<Fruit>();

	public List<Fruit> getItems() {

		return items;
	}

	public void setItems(List<Fruit> items) {

		this.items = items;
	}

	@Override
	public String toString() {

		return "FruitBasket [items=" + items + "]";
	}

}

package com.blue.data;

import java.util.ArrayList;
import java.util.List;

/**
 * A Basket to store a specific kind of item
 *
 * @param <T>
 */
public class Basket<T> {

	List<T> items = new ArrayList<T>();

	public List<T> getItems() {

		return items;
	}

	public void setItems(List<T> items) {

		this.items = items;
	}

	@Override
	public String toString() {

		return "Basket [items=" + items + "]";
	}

}

package com.blue.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.PROPERTY)
public class Apple implements Fruit {

	private int wgt;

	public Apple() {

		super();
		this.wgt = 0;
	};

	public Apple(int wgt) {

		super();
		this.wgt = wgt;
	}

	public int getWgt() {

		return wgt;
	}

	public void setWgt(int wgt) {

		this.wgt = wgt;
	}

	@Override
	public String toString() {

		return "Apple [wgt=" + wgt + "]";
	}

}

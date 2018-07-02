package com.blue.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.PROPERTY)
public class Banana implements Fruit {

	private int wgt;

	public Banana() {

		super();
		this.wgt = 0;
	}

	public Banana(int wgt) {

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

		return "Banana [wgt=" + wgt + "]";
	}

}

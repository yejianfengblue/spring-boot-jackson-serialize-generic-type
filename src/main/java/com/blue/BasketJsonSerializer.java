package com.blue;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.blue.data.Basket;
import com.blue.data.Fruit;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class BasketJsonSerializer {

	public static class Serializer extends JsonSerializer<Basket<Fruit>> {

		@Override
		public void serialize(Basket<Fruit> value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {

			gen.writeStartObject();
			gen.writeObjectField("items", value.getItems());
			gen.writeEndObject();
		}
	}
}
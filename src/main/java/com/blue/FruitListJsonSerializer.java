package com.blue;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.jackson.JsonComponent;

import com.blue.data.Fruit;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class FruitListJsonSerializer {

	public static class Serializer extends JsonSerializer<List<Fruit>> {

		@Override
		public void serialize(List<Fruit> list, JsonGenerator gen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {

			gen.writeStartArray();
			for (Fruit fruit : list)
				gen.writeObject(fruit);
			gen.writeEndArray();
		}
	}
}
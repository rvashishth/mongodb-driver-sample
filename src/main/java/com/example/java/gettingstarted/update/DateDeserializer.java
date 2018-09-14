package com.example.java.gettingstarted.update;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;


public class DateDeserializer extends JsonDeserializer<Date>{

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode jn = (JsonNode) p.readValueAsTree();
		Date date = new Date();
		// if value is from db, node type should be object
		if (jn.getNodeType().equals(JsonNodeType.OBJECT)) {
			JsonNode dob = jn.get("$date");
			if (null != dob) {
				String asText = dob.asText();
				long l = Long.parseLong(asText);
				date = new Date(l);
			}
		}
		return date;
	}
	
}

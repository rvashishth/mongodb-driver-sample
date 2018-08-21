package com.example.java.gettingstarted.mongo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class LongDeserializer extends JsonDeserializer<Long>{

	@Override
	public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		TreeNode readValueAsTree = p.readValueAsTree();
		JsonNode jn = (JsonNode)readValueAsTree;
		JsonNode oid = jn.get("$numberLong");
	    return new Long(oid.asText()).longValue();
	}

}

package com.devidea.model.jsonAdapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class PhoneJsonAdapter implements JsonDeserializer<String>,JsonSerializer<String> {

	@Override
	public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		String text = json.getAsString();
		if (text == null) {
			return null;
		} else {
			text = text.replaceAll("\\.", "");
			text = text.replaceAll("-", "");
			text = text.replaceAll("\\(", "");
			text = text.replaceAll("\\)", "");
			text = text.replaceAll(" ", "");
			
			if (text.length() > 11) {
				return text.substring(0, 11);
			}
			return text;
		}
	}

	@Override
	public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
		if (src != null) {
			src = src.replaceAll("\\.", "");
			src = src.replaceAll("-", "");
			src = src.replaceAll("\\(", "");
			src = src.replaceAll("\\)", "");
			src = src.replaceAll(" ", "");
			JsonPrimitive result = new JsonPrimitive(src);
			return result;
		}
		return null;
	}    

}

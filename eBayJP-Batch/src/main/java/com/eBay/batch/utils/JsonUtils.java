package com.eBay.batch.utils;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * com.eBay.batch.utils_ JsonUtils.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class JsonUtils {
	
	public static String toJson(Map<String, Object> map) {

		ObjectMapper mapper = new ObjectMapper();
		String json = "";

		try {
			/* pretty print */
			json = mapper.writeValueAsString(map);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}

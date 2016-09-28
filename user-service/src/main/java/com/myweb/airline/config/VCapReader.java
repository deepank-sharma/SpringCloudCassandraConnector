package com.myweb.airline.config;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VCapReader {
	@SuppressWarnings("unchecked")
	@Bean
	public List<Map<String, Object>> vCapList() {
		Map<String, Object> vCapMap;
		if (hasCloudProperties()) {
			JsonParser jsonParser = JsonParserFactory.getJsonParser();
			vCapMap = jsonParser.parseMap(getVCap());
			return (List<Map<String, Object>>) vCapMap.get("user-provided");
		}
		return Collections.EMPTY_LIST;
	}

	public boolean hasCloudProperties() {
		return getVCap() != null;
	}

	public String getVCap() {
		return System.getenv("VCAP_SERVICES");
	}
}

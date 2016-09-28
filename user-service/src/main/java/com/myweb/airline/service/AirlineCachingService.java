package com.myweb.airline.service;

import java.util.Map;

import com.myweb.airline.vo.JToken;

public interface AirlineCachingService {
	Object getCachedElement(String id);
	Object saveToCache(JToken token);
	Map<String,JToken> getCompleteCache();
	Object deleteCacheElement(String id);
	Object deleteCompleteCache();
}

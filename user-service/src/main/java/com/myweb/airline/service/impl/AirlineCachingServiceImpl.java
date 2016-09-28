package com.myweb.airline.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.myweb.airline.service.AirlineCachingService;
import com.myweb.airline.vo.JToken;

@Component
public class AirlineCachingServiceImpl implements AirlineCachingService {
	@Autowired
	private RedisTemplate<String, JToken> cacheTemplate;
	private final static String cacheName = "AIRLINE";

	@Override
	public Object getCachedElement(String id) {
		JToken token = (JToken) cacheTemplate.opsForHash().get(cacheName, id);
		if(token.getExpiry().compareTo(new Date())>0){
			return token;
		} else {
			return null;
		}
	}

	@Override
	public Object saveToCache(JToken token) {
		cacheTemplate.opsForHash().put(cacheName, token.getUUID(), token);
		return token;
	}

	@Override
	public Map<String, JToken> getCompleteCache() {
		Set<Object> keys = cacheTemplate.opsForHash().keys(cacheName);
		Map<String, JToken> returnMap = new HashMap<>();
		for (Object o : keys) {
			returnMap.put((String) o, (JToken) cacheTemplate.opsForHash().get(cacheName, (String) o));
		}
		return returnMap;
	}

	@Override
	public Object deleteCacheElement(String id) {
		cacheTemplate.opsForHash().delete(cacheName, id);
		return "Cache Deleted";
	}

	@Override
	public Object deleteCompleteCache() {
		Set<Object> keys = cacheTemplate.opsForHash().keys(cacheName);
		for (Object o : keys) {
			cacheTemplate.opsForHash().delete(cacheName, (String) o);
		}
		return "Deleted All";
	}

}

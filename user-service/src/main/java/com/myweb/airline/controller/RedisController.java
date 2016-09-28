package com.myweb.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.airline.service.AirlineCachingService;
import com.myweb.airline.vo.JToken;

@RestController
@RequestMapping("/cache")
public class RedisController {
	@Autowired
	private AirlineCachingService cacheService;

	@RequestMapping(value = "/tokens", method = RequestMethod.POST)
	public ResponseEntity<Object> add(@RequestBody JToken token) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(cacheService.saveToCache(token));
	}

	@RequestMapping(value = "/tokens/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> get(@PathVariable String id) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(cacheService.getCachedElement(id));
	}

	@RequestMapping(value = "/tokens/all", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll() {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(cacheService.getCompleteCache());
	}
	
	@RequestMapping(value = "/tokens/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable String id) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(cacheService.deleteCacheElement(id));
	}

	@RequestMapping(value = "/all", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAll() {
		return ResponseEntity.ok().body(cacheService.deleteCompleteCache());
	}
}

package com.rest.api.config;

import java.util.*;

public class SimpleCacheManager {
	private Map<String, Object> cache = Collections.synchronizedMap(new HashMap<String, Object>());

	SimpleCacheManager() {
	}

	public void put(String cacheKey, Object value) {
		cache.put(cacheKey, value);
	}

	public Object get(String cacheKey) {
		return cache.get(cacheKey);
	}

	public void clear(String cacheKey) {
		cache.put(cacheKey, null);
	}

	public int size() {
		return cache.size();
	}

	public void clear() {
		cache.clear();
	}

	public boolean isEmpty() {
		return cache.isEmpty();
	}

	public Map<String, Object> getCache() {
		return cache;
	}

	public Boolean containsKey(String key) {
		boolean isKeyPresent = cache.containsKey(key);
		return isKeyPresent;
	}

	public void setCache(Map<String, Object> cache) {
		this.cache = cache;
	}
	
	public Set<Map.Entry<String, Object>> entrySet(){
		return cache.entrySet();
	}

	@Override
	public String toString() {
		return "SimpleCacheManager [cache=" + cache + "]";
	}

}
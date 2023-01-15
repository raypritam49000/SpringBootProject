package com.cache.api.config;

import java.util.*;

public class SimpleCacheManager {

//	private static SimpleCacheManager instance;
//	private static Object monitor = new Object();
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

//	public static SimpleCacheManager getInstance() {
//		if (instance == null) {
//			synchronized (monitor) {
//				if (instance == null) {
//					instance = new SimpleCacheManager();
//				}
//			}
//		}
//		return instance;
//	}

	@Override
	public String toString() {
		return "SimpleCacheManager [cache=" + cache + "]";
	}
	

}
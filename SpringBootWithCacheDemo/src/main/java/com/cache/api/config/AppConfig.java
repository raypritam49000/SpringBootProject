package com.cache.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean(name = "cache1")
	public SimpleCacheManager cacheManager() {
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		return simpleCacheManager;
	}

	@Bean(name = "cache2")
	public SimpleCacheManager cacheManager2() {
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		return simpleCacheManager;
	}
	
	@Bean(name = "cache3")
	public SimpleCacheManager cacheManager3() {
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		return simpleCacheManager;
	}
}

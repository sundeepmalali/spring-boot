package com.spring.boot.cache;

import java.util.Random;

import javax.cache.annotation.CacheResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

@Component
public class StockTracker {

	@Autowired
	CacheManager manager;
	
	@CacheResult(cacheName="price")
	public double getPriceBySymbol(String symbol) {
		System.out.println("######## Generating price from tracker....");
		return 20 + (200 -20) * new Random().nextDouble();
	}
	
	public double getPriceFromCacheManager(String symbol) {
		System.out.println("######## Generating price from cache manager tracker....");
		Cache cache = manager.getCache("price");
		return Double.valueOf(cache.get(symbol).getObjectValue().toString());
	}
}

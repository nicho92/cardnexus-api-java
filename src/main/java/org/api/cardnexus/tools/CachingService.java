package org.api.cardnexus.tools;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class CachingService {

    
    public static <K, V> Cache<K, V> createCache()
    {
	return Caffeine.newBuilder().build();
    }
    
}

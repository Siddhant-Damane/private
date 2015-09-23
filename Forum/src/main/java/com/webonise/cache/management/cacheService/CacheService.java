package com.webonise.cache.management.cacheService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.webonise.cache.management.cache.Cachable;

public interface CacheService extends Serializable {

	public void save(Cachable cachable);

	public void delete(String OBJECT_KEY, long key);

	public void update(Cachable cachable);

	public Cachable read(String hashKey, long key);

	public Map<Object, Object> readAllHash(String hashKey);

	public void deleteAll(List<Cachable> cachable);

	public void deleteAll(String hashKey);
}

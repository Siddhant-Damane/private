package com.webonise.cache.management.cacheServiceImpl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.webonise.cache.management.cache.Cachable;
import com.webonise.cache.management.cacheService.CacheService;

@Repository("cacheServiceImpl")
public class CacheServiceImpl implements CacheService {

	private static final Logger LOG = Logger.getLogger(CacheServiceImpl.class);

	@Autowired
	private RedisTemplate<String, Cachable> redisTemplate;

	public RedisTemplate<String, Cachable> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Cachable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void save(Cachable cachable) {
		// TODO Auto-generated method stub
		LOG.info("Saving object for key: {}" +  cachable.getKey());
		redisTemplate.opsForHash().put(cachable.getObjectKey(), cachable.getKey(), cachable);

	}

	@Override
	public void delete(String OBJECT_KEY,long key) {
		// TODO Auto-generated method stub
		LOG.info("Deleting object for key: {}" +key);
		redisTemplate.opsForHash().delete(OBJECT_KEY, key);
	}

	@Override
	public void update(Cachable cachable) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cachable read(String hashKey, long key) {
		// TODO Auto-generated method stub
		LOG.info("Reading key: {} from hashKey: {}"+ key +"  "+ hashKey);
		return (Cachable) redisTemplate.opsForHash().get(hashKey, key);

	}

	@Override
	public Map<Object, Object> readAllHash(String hashKey) {
		// TODO Auto-generated method stub
		LOG.info("Reading all object for hashKey: {}"+ hashKey);
		return (Map<Object, Object>) redisTemplate.opsForHash().entries(hashKey);

	}

	@Override
	public void deleteAll(List<Cachable> cachable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(String hashKey) {
		// TODO Auto-generated method stub

	}

}
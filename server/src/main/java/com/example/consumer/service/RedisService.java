package com.example.consumer.service;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    public static final String HASH_KEY = "CodeResponse";
    private final HashOperations<String, String, Object> hashOps;

    public RedisService(final RedisTemplate<String, Object> redisTemplate) {
        this.hashOps = redisTemplate.opsForHash();
    }

    public void save(final String key, final Object code){
        this.hashOps.put(HASH_KEY, key, code);
    }

    public Object getById(final String key) {
        return this.hashOps.get(HASH_KEY, key);
    }
}

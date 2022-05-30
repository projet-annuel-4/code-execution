package com.projet_annuel.rec.service;

import com.projet_annuel.rec.dto.CodeResponse;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final HashOperations<String, String, Object> valueOps;

    public RedisService(final RedisTemplate<String, Object> redisTemplate) {
        this.valueOps = redisTemplate.opsForHash();
    }

//    public void save(final String key, final Object code){
//        this.valueOps.set(key, code);
//    }

    public Object getById(final String hashKey, final String key){
        return this.valueOps.get(hashKey, key);
    }
}

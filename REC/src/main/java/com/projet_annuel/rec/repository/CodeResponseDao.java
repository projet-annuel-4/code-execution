package com.projet_annuel.rec.repository;

import com.projet_annuel.rec.dto.CodeResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CodeResponseDao {

    public static final String HASH_KEY = "CodeResponse";
    private RedisTemplate template;

    public CodeResponse getById(String id){
        return (CodeResponse) template.opsForHash().get(HASH_KEY, id);
    }
}

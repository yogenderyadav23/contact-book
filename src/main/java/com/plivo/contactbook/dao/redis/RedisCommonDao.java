package com.plivo.contactbook.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/*
  this is common redis dao, All different type of redis repo, caching different type of object can extend this particaular dao.


  //todo : make this dao generic dao, implement more methods using different data structures suppported by redis.

 */


@Repository
public class RedisCommonDao{

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisCommonDao() {
    }

    public void putValueWithExpireTime(String key,Object value,long timeout,TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

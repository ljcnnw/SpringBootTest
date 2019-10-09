package com.testspringboot.demo.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisTestController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("add")
    public Object add() {
        redisTemplate.opsForValue().set("name", "tom");
        return "success";
    }

    @GetMapping("get")
    public Object get() {
        String s = redisTemplate.opsForValue().get("name");
        return s;
    }

}

package com.assignment.Social_Guard_Api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    public void incrementScore(Long postId , int value){
        String key = "post:"+postId+":virality_score";
        redisTemplate.opsForValue().increment(key,value);
    }
    public Long CheckingBotReply(long postId){
        String x = "post:"+ postId+":bot_count";
    return  redisTemplate.opsForValue().increment(x,1);
    }
    public boolean coolDownActive (Long botId , Long userId){
        String key = "cooldown:bot_"+botId+":human_"+userId;
        return redisTemplate.hasKey(key);
    }
    public void setTimer(Long botid , Long userId){
        String key = "cooldown:bot_"+botid+":human_"+userId;
        redisTemplate.opsForValue().set(key,"1",10 ,TimeUnit.MINUTES);
    }
    public boolean isNotificationTimer(Long userId) {
        String key = "notification:cooldown:user_" + userId;
        return redisTemplate.hasKey(key);
    }
    public void setNotificationTimer(Long userId){
        String key = "notification:cooldown:user_" + userId ;
        redisTemplate.opsForValue().set(key, "1", 15, TimeUnit.MINUTES);
    }
    public void storeNotificationTimer(Long userId , String message){
        String key = "user:" + userId + ":pending_notifs";
        redisTemplate.opsForList().rightPush(key ,message);
    }

}

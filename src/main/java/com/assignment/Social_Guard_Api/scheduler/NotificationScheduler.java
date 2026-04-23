package com.assignment.Social_Guard_Api.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class NotificationScheduler {
    @Autowired
    private RedisTemplate<String , Object> redisTemplate;
    @Scheduled(fixedRate = 30000)
    public void scheduledNotification() {
        Set<String> keys = redisTemplate.keys("user:*:pending_notifs");
        if (keys == null || keys.isEmpty()){
            return;
        }
            for (String key : keys) {
                Long counter = redisTemplate.opsForList().size(key);
                if(counter== null|| counter == 0){
                    continue;
                }
                String FirstMsg = (String) redisTemplate.opsForList().leftPop(key);
                long remain = counter-1;
                redisTemplate.delete(key);
                if(remain>0){
                    System.out.println("Summarized Push Notification: "+FirstMsg+" and "+"["+remain+"]"+ " others interacted with your posts.");
                }else{
                    System.out.println("Summarized Push Notification: "+FirstMsg+" interacted with your post.");
                }
            }
    }
}
package com.example.redistest.config;

import com.example.redistest.model.NewMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisMessagePublisher{

    private final RedisTemplate<String,Object> redisTemplate;
//    private final ChannelTopic topic;

    public void publish(NewMessage message) {
        System.out.println("publis : "+ message.getData());
        System.out.println("publish topic: "+message.getRoomId());
        redisTemplate.convertAndSend(message.getRoomId(), message);
    }
}

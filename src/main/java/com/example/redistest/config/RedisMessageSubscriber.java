package com.example.redistest.config;

import com.example.redistest.model.NewMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisMessageSubscriber implements MessageListener {
    private final RedisTemplate<String,Object> redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;
    private final ObjectMapper objectMapper;


    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
        String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
        System.out.println(publishMessage+"111111");
        NewMessage newMessage = objectMapper.readValue(publishMessage,NewMessage.class);
        messagingTemplate.convertAndSend("/sub/"+newMessage.getRoomId(), newMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

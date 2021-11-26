package com.hexacta.microservice.customerservice.publish;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hexacta.microservice.customerservice.model.MessageUser;
import com.hexacta.microservice.customerservice.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class UserPublisher {

    RedisTemplate<?, ?> redisTemplate;
    ChannelTopic topic;

    public UserPublisher(RedisTemplate<?,?> redisTemplate, ChannelTopic topic){
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(User.class));
        this.topic = topic;
    }

    public void publish(MessageUser messageUser) throws JsonProcessingException {
        redisTemplate.convertAndSend(topic.getTopic(), messageUser);
    }

}

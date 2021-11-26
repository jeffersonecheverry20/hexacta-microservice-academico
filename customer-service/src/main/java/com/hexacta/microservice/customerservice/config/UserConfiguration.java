package com.hexacta.microservice.customerservice.config;

import com.hexacta.microservice.customerservice.publish.UserPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class UserConfiguration {

    @Autowired
    RedisTemplate<?, ?> redisTemplate;

    @Bean
    UserPublisher redisPublisher(){
        return new UserPublisher(redisTemplate, topic());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("user");
    }

}

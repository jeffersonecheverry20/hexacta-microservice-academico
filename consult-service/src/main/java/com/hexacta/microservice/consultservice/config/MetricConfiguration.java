package com.hexacta.microservice.consultservice.config;

import com.hexacta.microservice.consultservice.suscribe.MetricSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class MetricConfiguration {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    MetricSubscribe metricSubscribe;

    @Bean
    RedisMessageListenerContainer container(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.addMessageListener(messageListenerAdapter(), topic());
        container.setConnectionFactory(redisConnectionFactory);
        return container;
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter(){
        return new MessageListenerAdapter(metricSubscribe);
    }

    @Bean
    ChannelTopic topic(){
        return new ChannelTopic("user");
    }

}

package com.dailycodebuffer.springrabbitmqconsumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String MESSAGE_EXCHANGE = "message_exchange";
    public static final String MESSAGE_QUEUE = "message_queue";
    public static final String MESSAGE_ROUTING_KEY = "message_routingKey";

    @Bean
    public Queue queue()
    {
        return new Queue(MESSAGE_QUEUE);
    }

    @Bean
    public Exchange exchange()
    {
        return new TopicExchange(MESSAGE_EXCHANGE);
    }

    @Bean
    public Binding binding( Queue queue, TopicExchange topicExchange)
    {
        return BindingBuilder.bind(queue).to(topicExchange).with(MESSAGE_ROUTING_KEY);
    }

    @Bean
    public org.springframework.amqp.support.converter.MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;

    }
}

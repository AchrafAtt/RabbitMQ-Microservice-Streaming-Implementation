package com.a2t.rabbitmq_consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import org.springframework.amqp.support.converter.MessageConverter;

import org.springframework.amqp.core.Binding;

import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQ {


  @Value("${spring.rabbitmq.host}")
  String host;

  @Value("${spring.rabbitmq.username}")
  String username;

  @Value("${spring.rabbitmq.password}")
  String password;

  @Value("${spring.rabbitmq.template.exchange}")
  String exchange;

  @Value("${spring.rabbitmq.template.routingKey}")
  String routingKey;


  @Bean
  CachingConnectionFactory connectionFactory() {
      CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
      cachingConnectionFactory.setUsername(username);
      cachingConnectionFactory.setPassword(password);
      return cachingConnectionFactory;
  }

  @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

  @Bean RabbitTemplate rabbitTemplate( CachingConnectionFactory connectionFactory) {
      RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
      rabbitTemplate.setMessageConverter(jsonMessageConverter());
      return rabbitTemplate;
     
  }

   @Bean
    DirectExchange userExchange() {
        return new DirectExchange(exchange, true, false); // Durable, non-auto-delete
    }

    @Bean
    Queue queue() {
        return new Queue("user.queue", true); // Durable queue
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

  



  
}

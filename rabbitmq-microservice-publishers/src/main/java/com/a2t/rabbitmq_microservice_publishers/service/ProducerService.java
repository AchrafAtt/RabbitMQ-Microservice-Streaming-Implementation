package com.a2t.rabbitmq_microservice_publishers.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.a2t.rabbitmq_microservice_publishers.domain.User;
@Service
public class ProducerService {



  private final RabbitTemplate rabbitTemplate;

  public ProducerService(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  private final String exchange  = "user.exchange";

  private final String routingkey = "user.routingKey";

  public void sendMessage (User user)
  {
    rabbitTemplate.convertAndSend(exchange, routingkey, user);
  } 

  
}

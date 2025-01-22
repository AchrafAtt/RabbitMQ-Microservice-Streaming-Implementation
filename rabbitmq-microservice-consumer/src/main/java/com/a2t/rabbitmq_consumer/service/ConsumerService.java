package com.a2t.rabbitmq_consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.a2t.rabbitmq_consumer.domain.User;
import com.a2t.rabbitmq_consumer.repository.UserRepository;

@Service
public class ConsumerService {

  private final UserRepository userRepository;
  

  private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

  public ConsumerService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @RabbitListener(queues = "${spring.rabbitmq.queue}")
  public void receivedMessage(User user) {
    User persistedUser = userRepository.save(user);
    userRepository.save(user);
    logger.info("persisted user : " + persistedUser );
    logger.info("Message received from queue : " + user);
    logger.info("User persisted successfully :)");
  }

}

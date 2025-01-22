package com.a2t.rabbitmq_microservice_publishers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a2t.rabbitmq_microservice_publishers.domain.User;
import com.a2t.rabbitmq_microservice_publishers.service.ProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1")

public class ProducerController {
  
  private  ProducerService producerService;

  private static  final Logger logger = LoggerFactory.getLogger(ProducerController.class);

  public ProducerController(ProducerService producerService) {
    this.producerService = producerService;
  }


  @PostMapping("/produce")
  public ResponseEntity<String> sendMessge (@RequestBody User user){
    logger.info("User sent to the queue: " + user); 
    producerService.sendMessage(user);
    return ResponseEntity.ok( "user sent: " + user);
  }
  




}

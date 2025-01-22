package com.a2t.rabbitmq_microservice_publishers.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import  lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
public class User implements Serializable{

  private String userID;
  private String userName;
  
}

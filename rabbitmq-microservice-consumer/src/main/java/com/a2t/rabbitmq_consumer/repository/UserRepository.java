package com.a2t.rabbitmq_consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a2t.rabbitmq_consumer.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}

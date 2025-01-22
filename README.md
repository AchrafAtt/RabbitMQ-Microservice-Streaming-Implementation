# RabbitMQ Microservice Streaming Implementation

This project demonstrates the implementation of a microservice-based streaming system using RabbitMQ as the message broker. It consists of two main components:

- **Producer Service**: Publishes messages to RabbitMQ.
- **Consumer Service**: Listens to messages from RabbitMQ and processes them.

The project also integrates a MySQL database for storing processed data and uses Spring Boot for building the microservices.

---

## Features

### Producer Service
- Sends messages (e.g., user data) to RabbitMQ.
- Uses a direct exchange (`user.exchange`) and routing key (`user.routingKey`).

### Consumer Service
- Listens to messages from the `user.queue`.
- Processes messages and stores them in a MySQL database.

### Automatic Database Creation
- The MySQL database (`rabbitmq`) is automatically created if it doesn't exist.

### Automatic Schema Updates
- Hibernate automatically updates the database schema based on JPA entities.

### RabbitMQ Integration
- Exchange, queue, and binding are automatically created when the application starts.

---

## Technologies Used

- **Spring Boot**: Framework for building microservices.
- **RabbitMQ**: Message broker for streaming messages between services.
- **MySQL**: Database for storing processed data.
- **Spring AMQP**: Library for RabbitMQ integration.
- **Spring Data JPA**: For database operations.
- **Maven**: Build tool for managing dependencies.
- **Docker (Optional)**: For running RabbitMQ and MySQL in containers.

---

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL Server
- RabbitMQ Server
- Docker (Optional)

---

## Setup and Installation

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/rabbitmq-microservice-streaming.git
cd rabbitmq-microservice-streaming
```

### 2. Set Up RabbitMQ
Install RabbitMQ locally or use Docker:
```bash
docker run -d --hostname my-rabbit --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```
Access the RabbitMQ Management UI at [http://localhost:15672](http://localhost:15672) (username: `guest`, password: `guest`).

### 3. Set Up MySQL
Install MySQL locally or use Docker:
```bash
docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql:latest
```
Create a database named `rabbitmq` (if not using auto-creation).

### 4. Configure the Application
Update the `application.yml` files for both the producer and consumer services with the correct RabbitMQ and MySQL configurations.

---

## Project Structure

```
rabbitmq-microservice-streaming/
├── producer-service/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/producer/
│   │   │   │       ├── config/
│   │   │   │       ├── domain/
│   │   │   │       ├── service/
│   │   │   │       └── ProducerApplication.java
│   │   │   └── resources/
│   │   │       └── application.yml
│   │   └── test/
│   └── pom.xml
├── consumer-service/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/consumer/
│   │   │   │       ├── config/
│   │   │   │       ├── domain/
│   │   │   │       ├── service/
│   │   │   │       └── ConsumerApplication.java
│   │   │   └── resources/
│   │   │       └── application.yml
│   │   └── test/
│   └── pom.xml
└── README.md
```

---

## Configuration

### Producer Service (`application.yml`)
```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
    template:
      exchange: user.exchange
      routingKey: user.routingKey
server:
  port: 8081
```

### Consumer Service (`application.yml`)
```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
    listener:
      simple:
        default-requeue-rejected: false
  datasource:
    url: jdbc:mysql://localhost:3306/rabbitmq?useSSL=false&createDatabaseIfNotExist=true
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
server:
  port: 8082
```

---

## Running the Application

### 1. Start RabbitMQ and MySQL
Ensure RabbitMQ and MySQL are running.

### 2. Run the Producer Service
```bash
cd producer-service
mvn spring-boot:run
```

### 3. Run the Consumer Service
```bash
cd consumer-service
mvn spring-boot:run
```

---

## Testing the Implementation

### 1. Send a Message
Use the producer service to send a message:
```bash
curl -X POST http://localhost:8081/send -H "Content-Type: application/json" -d '{"name": "John Doe", "email": "john.doe@example.com"}'
```

### 2. Verify the Message
Check the consumer service logs to confirm the message was received and processed.

Verify the data in the MySQL database:
```sql
USE rabbitmq;
SELECT * FROM user;
```

---

## API Endpoints

### Producer Service
- **POST /send**: Send a message to RabbitMQ.

Request Body:
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

---

## Troubleshooting

### RabbitMQ Connection Issues
- Ensure RabbitMQ is running and accessible at `localhost:5672`.
- Check the RabbitMQ logs for errors.

### MySQL Connection Issues
- Ensure MySQL is running and the database `rabbitmq` exists.
- Verify the username and password in `application.yml`.

### Message Not Processed
- Ensure the consumer service is running and listening to the correct queue (`user.queue`).

---

## Contributing
Contributions are welcome! Please open an issue or submit a pull request.

---


Let me know if you need further assistance! 🌪️
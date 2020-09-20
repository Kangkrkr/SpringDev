package com.dev.kangkrkr;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.dev.kangkrkr.config.kafka.MessageListener;
import com.dev.kangkrkr.config.kafka.MessageProducer;
import com.dev.kangkrkr.vo.kafka.Greeting;

@SpringBootApplication
public class SpringDevApplication {

	@Autowired
	private MessageProducer producer;
	
	@Autowired
	private MessageListener listener;
	
	
	@PostConstruct
	public void init() throws Exception {
		producer.sendMessage("Hello, World!");
		listener.latch.await(10, TimeUnit.SECONDS);
		for (int i = 0; i < 5; i++) {
			producer.sendMessageToPartion("Hello To Partioned Topic!", i);
		}
		listener.partitionLatch.await(10, TimeUnit.SECONDS);
		producer.sendMessageToFiltered("Hello Baeldung!");
		producer.sendMessageToFiltered("Hello World!");
		listener.filterLatch.await(10, TimeUnit.SECONDS);
		producer.sendGreetingMessage(new Greeting("Greetings", "World!"));
		listener.greetingLatch.await(10, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringDevApplication.class, args);
	}
}

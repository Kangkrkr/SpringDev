//package com.dev.kangkrkr.config.kafka;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.admin.AdminClientConfig;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.kafka.core.KafkaAdmin;
//
//@Profile("local")
//@Configuration
//public class KafkaTopicConfig {
//
//	@Value("${kafka.bootstrapAddress}")
//	private String bootstrapAddress;
//	
//	@Value("${message.topic.name}")
//	private String messageTopicName;
//	
//	@Value("${partitioned.topic.name}")
//	private String partitionedTopicName;
//	
//	@Value("${filtered.topic.name}")
//	private String filteredTopicName;
//	
//	@Value("${greeting.topic.name}")
//	private String greetingTopicName;
//	
//	@Bean
//	public KafkaAdmin kafkaAdmin() {
//		Map<String, Object> configs = new HashMap<>();
//		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//		return new KafkaAdmin(configs);
//	}
//	
//	/**
//	 * 메시지큐의 Topic 생성
//	 * topicName, numPartitions, replicationFactor
//	 */
//	@Bean
//	public NewTopic topic1() {
//		return new NewTopic(messageTopicName, 1, (short)1);
//	}
//	
//	@Bean
//	public NewTopic topic2() {
//		return new NewTopic(partitionedTopicName, 6, (short)1);
//	}
//	
//	@Bean
//	public NewTopic topic3() {
//		return new NewTopic(filteredTopicName, 1, (short)1);
//	}
//	
//	@Bean
//	public NewTopic topic4() {
//		return new NewTopic(greetingTopicName, 1, (short)1);
//	}
//}

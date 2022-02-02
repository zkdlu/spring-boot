package com.zkdlu.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class Consumer implements ConsumerSeekAware {
    @Value("${kafka.topics.test}")
    private String topic;

    @KafkaListener(topics = "${kafka.topics.test}", groupId = "${spring.kafka.consumer.group-id}")
    void listen(String message) {
        log.info("1. {}", message);
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
        assignments.keySet().stream()
                .filter(partition -> topic.equals(partition.topic()))
                .forEach(partition -> callback.seekToBeginning(topic, partition.partition()));
    }
}

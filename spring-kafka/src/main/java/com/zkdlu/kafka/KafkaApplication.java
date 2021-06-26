package com.zkdlu.kafka;

import com.zkdlu.kafka.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Autowired
	Producer producer;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 0; i < 100; i++) {
			producer.sendMessage("test", i + "");
		}
	}
}

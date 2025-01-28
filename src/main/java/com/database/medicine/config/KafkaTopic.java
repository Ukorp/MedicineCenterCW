package com.database.medicine.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic medicineTopic() {
        return new NewTopic("mail", 1, (short) 1);
    }
}

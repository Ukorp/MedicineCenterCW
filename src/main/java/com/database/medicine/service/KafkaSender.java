package com.database.medicine.service;

import com.database.medicine.dto.mail.MailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaSender {
    private final KafkaTemplate<String, MailResponse> kafkaTemplate;
    public void send(String topic, MailResponse message) {
        kafkaTemplate.send(topic, message);
    }
}

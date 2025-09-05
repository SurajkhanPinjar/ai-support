package com.aidev.ai.support.rag.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueryConsumer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public QueryConsumer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "queries.in", groupId = "rag-service")
    public void consumeMessage(String message) {
        System.out.println("📥 Service B (RAG) received: " + message);
        // TODO: Add RAG logic here

        // Simulate enrichment
        String enriched = message.replace("}", ", \"ragData\":\"Sample RAG context\"}");
        kafkaTemplate.send("queries.out", enriched);
    }
}
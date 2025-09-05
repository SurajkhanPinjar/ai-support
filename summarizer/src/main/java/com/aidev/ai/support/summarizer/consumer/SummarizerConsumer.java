package com.aidev.ai.support.summarizer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SummarizerConsumer {

    @KafkaListener(topics = "queries.out", groupId = "summarizer-service")
    public void consumeMessage(String message) {
        System.out.println("📝 Summarizer received enriched data: " + message);
        // TODO: Add summarization logic
        System.out.println("✅ Summary ready for final output");
    }
}
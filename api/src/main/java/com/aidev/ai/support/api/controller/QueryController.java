package com.aidev.ai.support.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "AI Query API", description = "Endpoints for sending user queries to Kafka")
public class QueryController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public QueryController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Operation(
            summary = "Submit a query",
            description = "Accepts a user query payload and pushes it to the Kafka topic `queries.in`.",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Query accepted and queued for processing",
                            content = @Content(schema = @Schema(example = "{ \"status\": \"QUEUED\", \"topic\": \"queries.in\" }"))
                    )
            }
    )
    @PostMapping("/query")
    public ResponseEntity<?> postQuery(@RequestBody Map<String, Object> req) {
        String payload = new JSONObject(req).toString();
        kafkaTemplate.send("queries.in", payload);
        return ResponseEntity.accepted().body(Map.of(
                "status", "QUEUED",
                "topic", "queries.in"
        ));
    }
}
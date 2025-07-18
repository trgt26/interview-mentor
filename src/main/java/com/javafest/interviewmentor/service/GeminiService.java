package com.javafest.interviewmentor.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.create("https://generativelanguage.googleapis.com");

    public String getGeminiResponse(String prompt) {
        Map<String, Object> requestBody = Map.of(
            "contents", new Object[] {
                Map.of("parts", new Object[] { Map.of("text", prompt) })
            }
        );

        JsonNode response = webClient.post()
            .uri(uriBuilder -> uriBuilder
                .path("/v1beta/models/gemini-2.5-flash:generateContent")
                .queryParam("key", apiKey)
                .build()
            )
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(JsonNode.class)
            .block();

        try {
            return response
                    .get("candidates")
                    .get(0)
                    .get("content")
                    .get("parts")
                    .get(0)
                    .get("text")
                    .asText();
        } catch (Exception e) {
            return "Sorry, I couldn't understand the response.";
        }
    }
}

package com.javafest.interviewmentor.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for Gemini chat API")
public class GeminiRequestDTO {
    @Schema(description = "The prompt/message to send to Gemini AI", example = "Tell me about Spring Boot")
    private String prompt;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}

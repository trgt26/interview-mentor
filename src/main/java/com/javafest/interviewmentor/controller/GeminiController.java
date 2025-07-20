package com.javafest.interviewmentor.controller;

import com.javafest.interviewmentor.service.GeminiService;
import com.javafest.interviewmentor.dto.GeminiRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/chat")
@Tag(name = "Chat API", description = "Endpoints for interacting with Gemini AI")
public class GeminiController {

    @Autowired
    private GeminiService geminiService;

    @Operation(summary = "Send a message to Gemini AI", description = "Takes a prompt and returns Gemini's response")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully received response from Gemini",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public String chat(@RequestBody GeminiRequestDTO request) {
        return geminiService.getGeminiResponse(request.getPrompt());
    }
}

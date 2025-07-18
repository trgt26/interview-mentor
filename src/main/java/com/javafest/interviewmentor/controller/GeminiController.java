package com.javafest.interviewmentor.controller;

import com.javafest.interviewmentor.service.GeminiService;
import com.javafest.interviewmentor.dto.GeminiRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class GeminiController {

    @Autowired
    private GeminiService geminiService;

    @PostMapping
    public String chat(@RequestBody GeminiRequestDTO request) {
        return geminiService.getGeminiResponse(request.getPrompt());
    }
}

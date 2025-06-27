package com.example.habitgoservice.service;

import com.example.habitgoservice.dto.ChatResponse;
 
public interface DeepSeekService {
    ChatResponse chat(String message);
} 
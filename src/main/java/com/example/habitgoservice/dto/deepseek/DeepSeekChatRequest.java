package com.example.habitgoservice.dto.deepseek;

import lombok.Data;
import java.util.List;

@Data
public class DeepSeekChatRequest {
    private String model;
    private List<DeepSeekMessage> messages;
    private boolean stream;
    
    public DeepSeekChatRequest() {
        this.model = "deepseek-chat";
        this.stream = false;
    }
} 
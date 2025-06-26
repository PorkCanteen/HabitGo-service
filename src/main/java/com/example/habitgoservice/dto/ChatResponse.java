package com.example.habitgoservice.dto;

import lombok.Data;

@Data
public class ChatResponse {
    private String reply;
    private String model;
    private Integer totalTokens;
} 
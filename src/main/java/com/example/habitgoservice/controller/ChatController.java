package com.example.habitgoservice.controller;

import com.example.habitgoservice.common.Result;
import com.example.habitgoservice.dto.ChatRequest;
import com.example.habitgoservice.dto.ChatResponse;
import com.example.habitgoservice.service.DeepSeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private DeepSeekService deepSeekService;

    @PostMapping("/deepseek")
    public Result chat(@RequestBody ChatRequest request) {
        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            return Result.error("消息内容不能为空");
        }
        
        ChatResponse response = deepSeekService.chat(request.getMessage());
        return Result.success(response);
    }
    
    @RequestMapping(value = "/deepseek", method = RequestMethod.OPTIONS)
    public Result handleOptions() {
        return Result.success("OK");
    }
    
    @GetMapping("/test")
    public Result test() {
        return Result.success("Chat API is working!");
    }
} 
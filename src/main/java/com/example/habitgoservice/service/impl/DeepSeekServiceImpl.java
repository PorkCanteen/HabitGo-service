package com.example.habitgoservice.service.impl;

import com.example.habitgoservice.dto.ChatResponse;
import com.example.habitgoservice.dto.deepseek.DeepSeekChatRequest;
import com.example.habitgoservice.dto.deepseek.DeepSeekChatResponse;
import com.example.habitgoservice.dto.deepseek.DeepSeekMessage;
import com.example.habitgoservice.service.DeepSeekService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
@Service
public class DeepSeekServiceImpl implements DeepSeekService {
    
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    
    // DeepSeek API 配置
    private static final String API_BASE_URL = "https://api.deepseek.com";
    private static final String API_KEY = "sk-177d3c8e5387495d90e921ffa503a8a0";
    
    public DeepSeekServiceImpl() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers.setBearerAuth(API_KEY);
    }
    
    @Override
    public ChatResponse chat(String message) {
        try {
            // 构建请求
            DeepSeekChatRequest request = new DeepSeekChatRequest();
            request.setMessages(Arrays.asList(
                new DeepSeekMessage("system", "You are a helpful assistant."),
                new DeepSeekMessage("user", message)
            ));
            
            log.info("发送DeepSeek API请求: {}", message);
            
            // 创建HTTP实体
            HttpEntity<DeepSeekChatRequest> entity = new HttpEntity<>(request, headers);
            
            // 调用API
            ResponseEntity<DeepSeekChatResponse> responseEntity = restTemplate.exchange(
                API_BASE_URL + "/chat/completions",
                HttpMethod.POST,
                entity,
                DeepSeekChatResponse.class
            );
            
            DeepSeekChatResponse apiResponse = responseEntity.getBody();
            
            // 构建响应
            ChatResponse response = new ChatResponse();
            if (apiResponse != null && apiResponse.getChoices() != null && !apiResponse.getChoices().isEmpty()) {
                response.setReply(apiResponse.getChoices().get(0).getMessage().getContent());
                response.setModel(apiResponse.getModel());
                if (apiResponse.getUsage() != null) {
                    response.setTotalTokens(apiResponse.getUsage().getTotalTokens());
                }
            } else {
                response.setReply("抱歉，我无法生成回复。");
                response.setModel("deepseek-chat");
                response.setTotalTokens(0);
            }
            
            log.info("DeepSeek API响应成功，Token使用: {}", response.getTotalTokens());
            
            return response;
            
        } catch (Exception e) {
            log.error("调用DeepSeek API失败", e);
            
            ChatResponse errorResponse = new ChatResponse();
            errorResponse.setReply("抱歉，服务暂时不可用，请稍后再试。");
            errorResponse.setModel("deepseek-chat");
            errorResponse.setTotalTokens(0);
            
            return errorResponse;
        }
    }
} 
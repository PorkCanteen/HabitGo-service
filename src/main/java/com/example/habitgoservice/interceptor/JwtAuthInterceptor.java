package com.example.habitgoservice.interceptor;

import com.example.habitgoservice.exception.ServiceException;
import com.example.habitgoservice.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 对于预检请求，直接放行
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        
        // 对于登录请求，直接放行
        if (request.getRequestURI().contains("/api/user/login")) {
            return true;
        }

        // 从请求头中获取token
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            throw new ServiceException("未提供认证Token");
        }

        // 去掉Bearer前缀（如果有）
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            // 验证token有效性（仅检查是否可以解析出userId）
            Integer userId = jwtUtils.extractUserId(token);
            // 将userId存入request中，以便后续使用
            request.setAttribute("userId", userId);
            return true;
        } catch (Exception e) {
            throw new ServiceException("无效的认证Token");
        }
    }
} 
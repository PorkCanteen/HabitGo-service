package com.example.habitgoservice.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 请求工具类，用于获取当前请求中的属性
 */
public class RequestUtil {
    
    /**
     * 获取当前请求中的userId
     * @return 当前登录用户的ID
     */
    public static Integer getCurrentUserId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }
        
        HttpServletRequest request = attributes.getRequest();
        Object userId = request.getAttribute("userId");
        
        if (userId == null) {
            return null;
        }
        
        return (Integer) userId;
    }
} 
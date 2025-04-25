package com.example.habitgoservice.schedule;

import com.example.habitgoservice.entity.User;
import com.example.habitgoservice.service.ITaskService;
import com.example.habitgoservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskResetScheduler {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IUserService userService;

    // 每天0点执行
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetTasksAtMidnight() {
        // 获取所有用户
        List<User> users = userService.listUsers();
        
        // 为每个用户重置任务
        for (User user : users) {
            taskService.resetAllTaskStatus(user.getId());
        }
    }
}
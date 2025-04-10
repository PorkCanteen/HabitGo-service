package com.example.habitgoservice.schedule;

import com.example.habitgoservice.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskResetScheduler {

    @Autowired
    private ITaskService taskService;

    // 每天0点执行
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetTasksAtMidnight() {
        taskService.resetAllTaskStatus();
    }
}
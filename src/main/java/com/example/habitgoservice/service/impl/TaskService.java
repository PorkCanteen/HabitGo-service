package com.example.habitgoservice.service.impl;

import com.example.habitgoservice.controller.dto.TaskDetailDTO;
import com.example.habitgoservice.entity.Task;
import com.example.habitgoservice.mapper.TaskMapper;
import com.example.habitgoservice.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Locale;

@Service
public class TaskService implements ITaskService {

    @Autowired
    TaskMapper taskMapper;

    @Override
    public List<Task> listTask(int userId) {
        return taskMapper.listTask(userId);
    }

    @Override
    public void addTask(Task task) {
        LocalDateTime now = LocalDateTime.now();
        task.setCreateTime(now);
        task.setUpdateTime(now);
        taskMapper.addTask(task);
    }

    @Override
    public void updateTask(Task task) {
        // 先获取当前任务信息，保留count和isCompleted值
        List<Task> existingTasks = taskMapper.listTask(task.getUserId());
        for (Task existingTask : existingTasks) {
            if (existingTask.getId() == task.getId()) {
                task.setCount(existingTask.getCount());
                task.setIsCompleted(existingTask.getIsCompleted());
                break;
            }
        }
        
        task.setUpdateTime(LocalDateTime.now());
        taskMapper.updateTask(task);
    }

    @Override
    public void deleteTask(int id, int userId) {
        taskMapper.deleteTask(id, userId);
    }

    @Override
    public void toggleTaskStatus(int id, int userId) {
        int result = taskMapper.toggleTaskStatus(id, userId);
        if (result == 0) {
            throw new RuntimeException("任务不存在或更新失败");
        }
    }

    @Override
    public void resetAllTaskStatus(int userId) {
        // 获取所有已完成的任务
        List<Task> completedTasks = taskMapper.getCompletedTasks(userId);
        
        // 获取昨天的日期（因为定时任务在0点执行，记录的应该是昨天完成的任务）
        LocalDate yesterday = LocalDate.now().minusDays(1);
        
        // 为每个已完成的任务更新 completedDates
        for (Task task : completedTasks) {
            String currentDates = task.getCompletedDates();
            String newDate = yesterday.toString(); // 格式：YYYY-MM-DD
            
            // 如果已有完成日期，则追加；否则直接设置
            String updatedDates = (currentDates != null && !currentDates.isEmpty()) 
                ? currentDates + "," + newDate 
                : newDate;
            
            task.setCompletedDates(updatedDates);
            taskMapper.updateCompletedDates(task);
        }
        
        // 重置所有任务状态
        taskMapper.resetAllTaskStatus(userId);
    }

    @Override
    public TaskDetailDTO getTaskDetail(int id, int userId) {
        // 查询任务基本信息
        Task task = taskMapper.getTaskById(id, userId);
        if (task == null) {
            throw new RuntimeException("任务不存在");
        }

        // 创建返回对象
        TaskDetailDTO dto = new TaskDetailDTO();
        // 复制基本字段
        dto.setId(task.getId());
        dto.setUserId(task.getUserId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setCount(task.getCount());
        dto.setIsCompleted(task.getIsCompleted());
        dto.setTaskType(task.getTaskType());
        dto.setTargetType(task.getTargetType());
        dto.setTargetCount(task.getTargetCount());
        dto.setCompletedDates(task.getCompletedDates());
        dto.setCreateTime(task.getCreateTime());
        dto.setUpdateTime(task.getUpdateTime());

        // 计算统计字段
        String completedDates = task.getCompletedDates();
        if (completedDates != null && !completedDates.trim().isEmpty()) {
            List<String> dateList = Arrays.asList(completedDates.split(","));
            
            // 1. 总打卡次数
            dto.setTotalCompletedCount(dateList.size());
            
            // 2. 本周打卡次数
            dto.setWeeklyCompletedCount(calculateWeeklyCount(dateList));
            
            // 3. 本月打卡次数
            dto.setMonthlyCompletedCount(calculateMonthlyCount(dateList));
            
            // 4. 当前连续打卡次数
            dto.setContinuities(calculateContinuities(dateList));
        } else {
            dto.setTotalCompletedCount(0);
            dto.setWeeklyCompletedCount(0);
            dto.setMonthlyCompletedCount(0);
            dto.setContinuities(0);
        }

        return dto;
    }

    /**
     * 计算本周打卡次数
     */
    private int calculateWeeklyCount(List<String> dateList) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        
        int count = 0;
        for (String dateStr : dateList) {
            try {
                LocalDate date = LocalDate.parse(dateStr.trim());
                if (!date.isBefore(startOfWeek) && !date.isAfter(endOfWeek)) {
                    count++;
                }
            } catch (Exception e) {
                // 忽略无效日期
            }
        }
        return count;
    }

    /**
     * 计算本月打卡次数
     */
    private int calculateMonthlyCount(List<String> dateList) {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        
        int count = 0;
        for (String dateStr : dateList) {
            try {
                LocalDate date = LocalDate.parse(dateStr.trim());
                if (!date.isBefore(startOfMonth) && !date.isAfter(endOfMonth)) {
                    count++;
                }
            } catch (Exception e) {
                // 忽略无效日期
            }
        }
        return count;
    }

    /**
     * 计算当前连续打卡次数
     */
    private int calculateContinuities(List<String> dateList) {
        if (dateList.isEmpty()) {
            return 0;
        }

        // 将日期字符串转换为LocalDate并排序
        List<LocalDate> dates = dateList.stream()
                .map(String::trim)
                .map(dateStr -> {
                    try {
                        return LocalDate.parse(dateStr);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(date -> date != null)
                .sorted()
                .collect(java.util.stream.Collectors.toList());

        if (dates.isEmpty()) {
            return 0;
        }

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        
        // 如果最新的打卡日期不是今天或昨天，则连续次数为0
        LocalDate latestDate = dates.get(dates.size() - 1);
        if (!latestDate.equals(today) && !latestDate.equals(yesterday)) {
            return 0;
        }

        // 从最新日期开始向前计算连续次数
        int continuities = 1;
        LocalDate currentDate = latestDate;
        
        for (int i = dates.size() - 2; i >= 0; i--) {
            LocalDate prevDate = dates.get(i);
            LocalDate expectedPrevDate = currentDate.minusDays(1);
            
            if (prevDate.equals(expectedPrevDate)) {
                continuities++;
                currentDate = prevDate;
            } else {
                break;
            }
        }
        
        return continuities;
    }
}

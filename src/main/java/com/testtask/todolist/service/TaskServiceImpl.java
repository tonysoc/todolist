package com.testtask.todolist.service;

import com.testtask.todolist.dao.TaskDao;
import com.testtask.todolist.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskDao taskDao;

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    @Transactional
    public void addTask(Task task) {
        this.taskDao.addTask(task);
    }

    @Override
    @Transactional
    public void updateTask(Task task) {
        this.taskDao.updateTask(task);
    }

    @Override
    @Transactional
    public void removeTask(int id) {
        this.taskDao.removeTask(id);
    }

    @Override
    @Transactional
    public Task getTaskById(int id) {
        return this.taskDao.getTaskById(id);
    }

    @Override
    @Transactional
    public List<Task> listTasks() {
        return this.taskDao.listTasks();
    }

    @Override
    @Transactional
    public List<Task> listDoneTasks() {
        return this.taskDao.listDoneTasks();
    }

    @Override
    @Transactional
    public List<Task> listUndoneTasks() {
        return this.taskDao.listUndoneTasks();
    }
}

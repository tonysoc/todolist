package com.testtask.todolist.service;

import com.testtask.todolist.model.Task;

import java.util.List;

public interface TaskService {
    public void addTask(Task task);

    public void updateTask(Task task);

    public void removeTask(int id);

    public Task getTaskById(int id);

    public List<Task> listTasks();

    public List<Task> listDoneTasks();

    public List<Task> listUndoneTasks();
}

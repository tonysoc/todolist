package com.testtask.todolist.dao;

import com.testtask.todolist.model.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addTask(Task task) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(task);
    }

    @Override
    public void updateTask(Task task) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(task);
    }

    @Override
    public void removeTask(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Task task = (Task) session.load(Task.class, new Integer(id));

        if(task != null){
            session.delete(task);
        }
    }

    @Override
    public Task getTaskById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Task task = (Task) session.load(Task.class, new Integer(id));

        System.out.println("Task successfully loaded. Task details: " + task);

        return task;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Task> listTasks() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Task> taskList = session.createQuery("from Task").list();

        return taskList;
    }

    @Override
    public List<Task> listDoneTasks() {
        List<Task> subList = new ArrayList<Task>();

        for(Task task: listTasks()) {
            if (task.getDone())
                subList.add(task);
        }

        return subList;
    }

    @Override
    public List<Task> listUndoneTasks() {
        List<Task> subList = new ArrayList<Task>();

        for(Task task: listTasks()) {
            if (!task.getDone())
                subList.add(task);
        }

        return subList;
    }
}

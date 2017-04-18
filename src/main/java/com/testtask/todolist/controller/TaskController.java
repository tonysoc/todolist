package com.testtask.todolist.controller;

import com.testtask.todolist.model.Task;
import com.testtask.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    private TaskService taskService;

    List<Task> pageList;
    final int tasksOnPage = 10;
    final int firstPage = 1;
    int lastPage = firstPage;
    int currentPage = firstPage;
    private String showType = "all";


    @Autowired(required = true)
    @Qualifier(value = "taskService")
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "tasks", method = RequestMethod.GET)
    public String listTasks(@RequestParam(value = "page", required = false) Long page, @RequestParam(value = "type", required = false) String type, Model model){
        model.addAttribute("task", new Task());

        if(type != null) {
            showType = type;
            currentPage = firstPage;
        }
        if(page != null)
            currentPage =  page.intValue();

        List<Task> taskList;
        if(showType.equals("done"))
            taskList = this.taskService.listDoneTasks();
        else if(showType.equals("undone"))
            taskList = this.taskService.listUndoneTasks();
        else
            taskList = this.taskService.listTasks();

        lastPage = (taskList.size()/tasksOnPage);
        if(taskList.size() % tasksOnPage > 0 || taskList.size() == 0)
            lastPage++;

        currentPage = Math.min(currentPage, lastPage);

        pageList = taskList.subList(tasksOnPage*(currentPage-1), Math.min((tasksOnPage*currentPage), taskList.size()));

        model.addAttribute("listTasks", pageList);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("lastPage", lastPage);

        return "tasks";
    }

    @RequestMapping(value = "/tasks/add", method = RequestMethod.POST)
    public String addTask(@ModelAttribute("task") Task task){
        if(task.getId() == 0){
            this.taskService.addTask(task);
        } else {
            this.taskService.updateTask(task);
        }

        return "redirect:/tasks";
    }

    @RequestMapping("/remove/{id}")
    public String removeTask(@PathVariable("id") int id){
        this.taskService.removeTask(id);

        return "redirect:/tasks";
    }

    @RequestMapping("edit/{id}")
    public String editTask(@PathVariable("id") int id, Model model){
        model.addAttribute("task", this.taskService.getTaskById(id));
        model.addAttribute("listTasks", pageList);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("lastPage", lastPage);

        return "tasks";
    }

    @RequestMapping("changemark/{id}")
    public String changeTaskMark(@PathVariable("id") int id) {
        Task task = this.taskService.getTaskById(id);
        boolean status = task.getDone();
        if(status == true)
            task.setDone(false);
        else
            task.setDone(true);
        this.taskService.updateTask(task);
        return "redirect:/tasks";
    }
}

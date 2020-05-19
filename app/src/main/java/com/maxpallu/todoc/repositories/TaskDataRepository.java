package com.maxpallu.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.maxpallu.todoc.database.dao.TaskDao;
import com.maxpallu.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) { this.taskDao = taskDao; }

    public LiveData<List<Task>> getAllTasks() { return this.taskDao.getAllTasks(); }

    public LiveData<Task> getTask(long id) {return this.taskDao.getTask(id); }

    public void createTask(Task task) { this.taskDao.insertTask(task); }

    public void deleteTask(Task task) { this.taskDao.deletetask(task.getId()); }

    public void updateTask(Task task) {taskDao.updateTask(task); }
}

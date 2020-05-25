package com.maxpallu.todoc.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.maxpallu.todoc.model.Project;
import com.maxpallu.todoc.model.Task;
import com.maxpallu.todoc.repositories.ProjectDataRepository;
import com.maxpallu.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    private TaskDataRepository mRepository;
    private ProjectDataRepository mDataRepository;
    private LiveData<List<Project>> mProjects;
    private LiveData<List<Task>> mTasks;
    private final Executor executor;

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.mRepository = taskDataSource;
        this.mDataRepository = projectDataSource;
        this.executor = executor;
    }

    public void insertTask(Task task) {
        executor.execute(() -> {
            mRepository.createTask(task);
        });
    }

    public void updateTask(Task task) {
        executor.execute(() -> {
            mRepository.updateTask(task);
        });
    }

    public void deleteTask(Task task) {
        executor.execute(() -> {
            mRepository.deleteTask(task);
        });
    }

    public LiveData<List<Task>> getAllTasks() {
        mTasks = mRepository.getAllTasks();
        return mTasks;
    }

    public LiveData<List<Project>> getProjects() {
        mProjects = mDataRepository.getProjects();
        return mProjects;
    }
}
package com.maxpallu.todoc.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.maxpallu.todoc.model.Task;
import com.maxpallu.todoc.repositories.ProjectDataRepository;
import com.maxpallu.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends AndroidViewModel {

    private TaskDataRepository mRepository;
    private ProjectDataRepository mProjectDataRepository;
    private Executor mExecutor;
    private LiveData<List<Task>> mTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TaskDataRepository(application);
        mTasks = mRepository.getAllTasks();
    }

    public void insertTask(Task task) {
        mRepository.createTask(task);
    }

    public void updateTask(Task task) {
        mRepository.updateTask(task);
    }

    public void deleteTask(Task task) {
        mRepository.deleteTask(task);
    }

    public LiveData<List<Task>> getAllTasks() {
        return mTasks;
    }
}
package com.maxpallu.todoc.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.maxpallu.todoc.repositories.ProjectDataRepository;
import com.maxpallu.todoc.repositories.TaskDataRepository;
import com.maxpallu.todoc.ui.TaskViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ProjectDataRepository projectData;
    private final TaskDataRepository taskData;
    private final Executor executor;

    public ViewModelFactory(ProjectDataRepository projectData,
                            TaskDataRepository taskData, Executor executor) {
        this.projectData = projectData;
        this.taskData = taskData;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(TaskViewModel.class))
        {
            return (T) new TaskViewModel
                    (this.taskData, this.projectData, this.executor);
        }
        throw new IllegalArgumentException("Unkown ViewModel class");
    }
}
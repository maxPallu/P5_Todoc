package com.maxpallu.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.maxpallu.todoc.database.dao.ProjectDao;
import com.maxpallu.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) { this.projectDao = projectDao; }

    public LiveData<List<Project>> getProjects() { return this.projectDao.getProjects(); }
}

package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

public interface ProjectDao {
    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getProjects();

    @Insert
    long insertProject(Project project);
}

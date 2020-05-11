package com.maxpallu.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.maxpallu.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getProjects();

    @Insert
    long insertProject(Project project);
}

package com.cleanup.todocMaxPallu.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.cleanup.todocMaxPallu.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getProjects();

    @Insert
    long insertProject(Project project);
}

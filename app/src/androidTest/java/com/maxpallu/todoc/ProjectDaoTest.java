package com.maxpallu.todoc;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import com.maxpallu.todoc.database.TodocDatabase;
import com.maxpallu.todoc.model.Project;
import com.maxpallu.todoc.model.Task;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class ProjectDaoTest {

    private TodocDatabase database;

    private static long ID = 1;
    private static Task TASK_DEMO = new Task(ID, 1, "Faire la vaisselle", 25);
    private Project TEST_PROJECT = Project.getProjectById(ID);

    @Before
    public void initDb() throws Exception {

        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @Test
    public void insertAndGetProjects() throws InterruptedException {
        this.database.projectDao().insertProject(TEST_PROJECT);

        List<Project> projects = LiveDataTestUtil.getValue(this.database.projectDao().getProjects());
        assertTrue(projects.size() == 1);
    }
}

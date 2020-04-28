package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    private TodocDatabase database;

    private static long ID = 1;
    private static Task TASK_DEMO = new Task(ID, 1, "Faire la vaisselle", 25);

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGetTask() throws InterruptedException {

        this.database.taskDao().insertTask(TASK_DEMO);

        Task task = LiveDataTestUtil.getValue(this.database.taskDao().getTasks(TASK_DEMO));
        assertTrue(task.getName().equals(TASK_DEMO.getName()) && task.getId() == ID);
    }
}

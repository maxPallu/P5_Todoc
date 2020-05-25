package com.maxpallu.todoc.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.maxpallu.todoc.database.dao.ProjectDao;
import com.maxpallu.todoc.database.dao.TaskDao;
import com.maxpallu.todoc.model.Project;
import com.maxpallu.todoc.model.Task;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    private static volatile TodocDatabase INSTANCE;

    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();

    public static TodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "MyDatabase.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(prepopulateDatabase)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback prepopulateDatabase = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
            Project[] projects = Project.getAllProjects();
            for(Project project : projects) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", project.getId());
                contentValues.put("name", project.getName());
                contentValues.put("color", project.getColor());
                db.insert("Project", OnConflictStrategy.IGNORE, contentValues);
            }
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TaskDao mTaskDao;

        private PopulateDbAsyncTask(TodocDatabase db) {
            mTaskDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}

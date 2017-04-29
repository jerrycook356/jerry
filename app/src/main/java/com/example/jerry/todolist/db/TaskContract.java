package com.example.jerry.todolist.db;

/**
 * Created by jerry on 4/27/2017.
 */
import android.provider.BaseColumns;

public class TaskContract {
    public static final String DB_NAME = "com.example.jerry.todolist.db";
    public static final int DB_VERSION =1;
    public class TaskEntry implements BaseColumns{
        public static final String TABLE = "tasks";
        public static final String COL_TASK_TITLE = "title";
    }
}

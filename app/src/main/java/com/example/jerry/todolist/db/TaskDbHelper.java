package com.example.jerry.todolist.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jerry on 4/27/2017.
 */

public class TaskDbHelper extends SQLiteOpenHelper {
    private static final int  DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "timetracker.db";
    private static final String TABLE_NAME = "timerecords";
    public static final String TIMETRACKER_COLUMN_ID ="_id";
    public static final String TIMETRACKER_DATE="date";
    public static final String TIMETRACKER_COLUMN_TIME_IN="timein";
    public static final String TIMETRACKER_COLUMN_TIME_OUT="timeout";
    public static final String TIMETRACKER_COLUMN_NOTES="notes";
    private SQLiteDatabase database;
    public TaskDbHelper(Context context) {
        super(context, TaskContract.DB_NAME, null, TaskContract.DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(
                "CREATE TABLE " + TABLE_NAME + "( "
                        + TIMETRACKER_COLUMN_ID + " INTEGER PRIMARY KEY, "
                        + TIMETRACKER_DATE + " TEXT, "
                        + TIMETRACKER_COLUMN_TIME_IN + " TEXT, "
                        + TIMETRACKER_COLUMN_TIME_OUT + " TEXT, "
                        + TIMETRACKER_COLUMN_NOTES + " TEXT )"

        );}



    public void saveTimeRecord(String date,String timeIn, String timeOut,String notes){

        getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIMETRACKER_DATE,date);
        contentValues.put(TIMETRACKER_COLUMN_TIME_IN, timeIn);
        contentValues.put(TIMETRACKER_COLUMN_TIME_OUT,timeOut);
        contentValues.put(TIMETRACKER_COLUMN_NOTES,notes);
        database.insert(TABLE_NAME,null,contentValues);
    }
    public Cursor getTimeRecordsList(){
        getReadableDatabase();
        return  database.rawQuery("select * from "+TABLE_NAME,null);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+ TaskContract.TaskEntry.TABLE);
        onCreate(db);

    }

}

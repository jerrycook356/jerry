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
    private static final int  DATABASE_VERSION =2;
    private static final String DATABASE_NAME = "timetracker.db";
    private static final String TABLE_NAME = "timerecords";
    public static final String TIMETRACKER_COLUMN_ID ="_id";
    public static final String TIMETRACKER_DATE="date";
    public static final String TIMETRACKER_COLUMN_TIME_IN="timein";
    public static final String TIMETRACKER_COLUMN_TIME_OUT="timeout";
    public static final String TIMETRACKER_COLUMN_NOTES="notes";
    private SQLiteDatabase database;

    public TaskDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase database){
        String createTable =(
                "CREATE TABLE " + TABLE_NAME + "( "
                        + TIMETRACKER_COLUMN_ID + " INTEGER PRIMARY KEY, "
                        + TIMETRACKER_DATE + " TEXT, "
                        + TIMETRACKER_COLUMN_TIME_IN + " TEXT, "
                        + TIMETRACKER_COLUMN_TIME_OUT + " TEXT, "
                        + TIMETRACKER_COLUMN_NOTES + " TEXT )");

                database.execSQL(createTable);
        }



    public void saveTimeRecord(String date,String timeIn, String timeOut,String notes){

        database= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIMETRACKER_DATE,date);
        contentValues.put(TIMETRACKER_COLUMN_TIME_IN, timeIn);
        contentValues.put(TIMETRACKER_COLUMN_TIME_OUT,timeOut);
        contentValues.put(TIMETRACKER_COLUMN_NOTES,notes);
        database.insert(TABLE_NAME,null,contentValues);

    }
    public Cursor getTimeRecordsList(){
        database = this.getReadableDatabase();
        return  database.rawQuery("select * from " + TABLE_NAME, null);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}

package com.example.jerry.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jerry.todolist.db.TaskContract;
import com.example.jerry.todolist.db.TaskDbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "Main Activiy";
    private TaskDbHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;
    private static final int TIME_ENTRY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper = new TaskDbHelper(this);
        mTaskListView = (ListView) findViewById(R.id.list_todo);

        updateUI();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_add_task:
                Intent intent = new Intent(this,TimeEntryActivity.class);
                startActivityForResult(intent, TIME_ENTRY);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode,Intent data){

        if(requestCode == TIME_ENTRY){
            if(resultCode == RESULT_OK){
                String timeIn = data.getStringExtra("timein");
                String timeOut = data.getStringExtra("timeout");
                String notes = data.getStringExtra("notes");
                String date = data.getStringExtra("date");
                mHelper.saveTimeRecord(date,timeIn,timeOut,notes);
            }
        }
    }

    private void updateUI(){
        ArrayList<String> taskList = new ArrayList<>();
        int i =0;
        Log.d(TAG,"before database");
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Log.d(TAG,"after database call");
        Cursor cursor = mHelper.getTimeRecordsList();
            cursor.moveToFirst();
            while(cursor.moveToNext()) ;
            {
                taskList.add(cursor.getString(i));
                i++;
            }

            if (mAdapter == null) {
                mAdapter = new ArrayAdapter<>(this,
                        R.layout.item_todo,
                        R.id.date_view,
                        taskList);
                mTaskListView.setAdapter(mAdapter);
            } else {
                mAdapter.clear();
                mAdapter.addAll(taskList);
                mAdapter.notifyDataSetChanged();
            }


        cursor.close();
        db.close();
    }


}

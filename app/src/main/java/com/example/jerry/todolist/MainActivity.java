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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.example.jerry.todolist.db.TaskDbHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.jerry.todolist.R.id.date_view;
import static com.example.jerry.todolist.R.id.parent;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "Main Activiy";
    private TaskDbHelper mHelper;
    private ListView mTaskListView;
    private static final int TIME_ENTRY = 1;
    public Cursor cursor;
    public MyCursorAdaper adaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper = new TaskDbHelper(this);
        //retrieve();
        cursor = mHelper.getTimeRecordsList();
        adaper = new MyCursorAdaper(this,cursor);
        ListView lV = (ListView) findViewById(R.id.list_todo);
        lV.setAdapter(adaper);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Intent intent = new Intent(this, TimeEntryActivity.class);
                startActivityForResult(intent, TIME_ENTRY);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == TIME_ENTRY) {
            if (resultCode == RESULT_OK) {
                String timeIn = data.getStringExtra("timein");
                String timeOut = data.getStringExtra("timeout");
                String notes = data.getStringExtra("notes");
                String date = data.getStringExtra("date");
                adaper.notifyDataSetChanged();
                mHelper.saveTimeRecord(date, timeIn, timeOut, notes);




            }
        }
    }



}











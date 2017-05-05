package com.example.jerry.todolist;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by jerry on 4/28/2017.
 */

public class TimeEntryActivity extends Activity {


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.xml.fadein, R.xml.fadeout);
        setContentView(R.layout.entry_form);
    }

    public void onSave(View view){
        Intent intent = getIntent();
        EditText dateView = (EditText) findViewById(R.id.date_view);
        intent.putExtra("date",dateView.getText().toString());
        EditText timeInView = (EditText) findViewById(R.id.timeIn_view);
        intent.putExtra("timein",timeInView.getText().toString());
        EditText timeOutView = (EditText)findViewById(R.id.timeOut_view);
        intent.putExtra("timeout",timeOutView.getText().toString());
        EditText notesView = (EditText)findViewById(R.id.notes_view);
        intent.putExtra("notes",notesView.getText().toString());
        this.setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(R.xml.fadein, R.xml.fadeout);

    }
    public void onCancel(View view){
        finish();
        overridePendingTransition(R.xml.fadein, R.xml.fadeout);
    }
}
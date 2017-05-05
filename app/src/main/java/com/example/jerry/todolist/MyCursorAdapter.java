package com.example.jerry.todolist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.R.attr.priority;

/**
 * Created by jerry on 5/1/2017.
 */

public class MyCursorAdapter extends CursorAdapter {

        public MyCursorAdapter(Context context, Cursor cursor) {
            super(context, cursor, 2);
        }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView dates = (TextView) view.findViewById(R.id.date_view);
        TextView timein = (TextView) view.findViewById(R.id.timeIn_view);
        TextView timeout = (TextView) view.findViewById(R.id.timeOut_view);
        TextView notess = (TextView) view.findViewById(R.id.notes_view);
        // Extract properties from cursor
        String getDate = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        String getTimein = cursor.getString(cursor.getColumnIndexOrThrow("timein"));
        String getTimeout = cursor.getString(cursor.getColumnIndexOrThrow("timeout"));
        String getnotes = cursor.getString(cursor.getColumnIndexOrThrow("notes"));

        // Populate fields with extracted properties
        dates.setText(getDate);
        timein.setText(getTimein);
        timeout.setText(getTimeout);
        notess.setText(getnotes);



    }


}

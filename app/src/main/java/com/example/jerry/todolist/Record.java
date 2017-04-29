package com.example.jerry.todolist;

/**
 * Created by jerry on 4/28/2017.
 */

public class Record {
    private String timeIn;
    private String timeOut;
    private String notes;
    private String date;

    public Record(String timeIn, String timeOut, String notes,String date){
        this.timeIn= timeIn;
        this.timeOut= timeOut;
        this.notes = notes;
        this.date = date;
    }

    public String getTimeIn(){
        return timeIn;
    }
    public String getTimeOut() {
        return timeOut;

    }
    public String getNotes(){
        return notes;
    }
    public String getDate(){return date;};
    public void setTimeIn(String timeIn){
        this.timeIn = timeIn;
    }
    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;

    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    public void setDate(String date){this.date = date;}

}


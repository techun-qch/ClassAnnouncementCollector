package com.example.classannouncementcollector.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Message")
public class Message {
    //主键
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "detail")
    private String detail;

    private String Deadline;

    public Message(String detail,String Deadline){
        this.detail=detail;
        this.Deadline=Deadline;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

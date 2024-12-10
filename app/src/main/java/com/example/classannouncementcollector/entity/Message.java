package com.example.classannouncementcollector.entity;

import androidx.annotation.NonNull;
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

    private String category;

    public Message(String detail,String Deadline,String category){
        this.detail=detail;
        this.Deadline=Deadline;
        this.category=category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NonNull
    @Override
    public String toString(){
        return this.id+":"+this.detail+this.Deadline+this.category;
    }

}

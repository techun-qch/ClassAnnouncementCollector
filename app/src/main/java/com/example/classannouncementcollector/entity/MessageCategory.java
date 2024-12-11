package com.example.classannouncementcollector.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MessageCategory")
public class MessageCategory {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "CategoryValue")
    private String CategoryValue;

    public MessageCategory(String CategoryValue) {
        this.CategoryValue = CategoryValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryValue() {
        return CategoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        CategoryValue = categoryValue;
    }
}

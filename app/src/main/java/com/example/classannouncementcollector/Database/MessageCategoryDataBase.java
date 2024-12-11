package com.example.classannouncementcollector.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.classannouncementcollector.dao.MessageCategoryDao;
import com.example.classannouncementcollector.entity.MessageCategory;

@Database(entities = {MessageCategory.class},version = 1,exportSchema = false)
public abstract class MessageCategoryDataBase extends RoomDatabase {
    public abstract MessageCategoryDao messageCategoryDao();
}

package com.example.classannouncementcollector;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.classannouncementcollector.dao.MessageDao;
import com.example.classannouncementcollector.entity.Message;

@Database(entities = {Message.class},version = 1,exportSchema = false)
public abstract class MessageDataBase extends RoomDatabase {
    public abstract MessageDao mMessageDao();
}

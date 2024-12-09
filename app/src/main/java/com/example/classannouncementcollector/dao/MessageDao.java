package com.example.classannouncementcollector.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.classannouncementcollector.entity.Message;

import java.util.List;
@Dao
public interface MessageDao {
//    查询
    @Query("Select * from Message")
    List<Message> getALLMessage();
//  插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPerson(Message message);
//    修改
    @Update
    public void updateMessages(Message... messages);
//    删除
    @Delete
    public void deleteMessages(Message ... messages);
}

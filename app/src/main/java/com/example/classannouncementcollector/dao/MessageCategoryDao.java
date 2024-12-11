package com.example.classannouncementcollector.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.classannouncementcollector.entity.Message;
import com.example.classannouncementcollector.entity.MessageCategory;

import java.util.List;
@Dao
public interface MessageCategoryDao {
    //    查询
    @Query("Select * from MessageCategory")
    List<MessageCategory> getALLMessageCategory();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCategory(MessageCategory messageCategory);

    @Update
    public void updateMessagesCategory(MessageCategory... messageCategory);
    //    删除
    @Delete
    public void deleteMessagesCategory(MessageCategory ... messageCategory);
}

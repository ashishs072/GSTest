package com.example.gstest.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gstest.db.entity.PictureInfo;

import java.util.List;

@Dao
public interface PictureInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPictureInfo(PictureInfo pictureInfo);

    @Query("Select * From PictureInfo")
    List<PictureInfo> getAllPictureData();
}

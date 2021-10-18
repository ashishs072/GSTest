package com.example.gstest.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.gstest.db.dao.PictureInfoDao;
import com.example.gstest.db.entity.PictureInfo;

@Database(entities = {PictureInfo.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract PictureInfoDao pictureInfoDao();

}

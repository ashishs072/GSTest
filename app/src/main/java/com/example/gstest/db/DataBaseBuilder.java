package com.example.gstest.db;

import android.content.Context;

import androidx.room.Room;

public class DataBaseBuilder {
    private String DB_NAME = "GSTest.db";
    private AppDataBase appDataBase;
    private Context mContext;
    private static DataBaseBuilder mInstance;

    private DataBaseBuilder(Context context){
           mContext = context;
           appDataBase = Room.databaseBuilder(mContext,AppDataBase.class,DB_NAME).build();
    }


    public static synchronized DataBaseBuilder getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DataBaseBuilder(mCtx);
        }
        return mInstance;
    }

    public AppDataBase AppDataBase() {
        return appDataBase;
    }

}

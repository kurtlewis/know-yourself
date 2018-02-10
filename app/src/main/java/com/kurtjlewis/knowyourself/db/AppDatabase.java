package com.kurtjlewis.knowyourself.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.kurtjlewis.knowyourself.db.converter.CalendarConverter;
import com.kurtjlewis.knowyourself.db.converter.EmotionConverter;
import com.kurtjlewis.knowyourself.db.dao.FeelingDao;
import com.kurtjlewis.knowyourself.db.entity.FeelingEntity;

/**
 * Created by kurt on 2/10/18.
 * @author Kurt Lewis
 * @date 2018.02.10
 */

@Database(entities = {FeelingEntity.class}, version=1)
@TypeConverters({CalendarConverter.class, EmotionConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    // Daos
    public abstract FeelingDao feelingDao();

    private static AppDatabase sInstance;

    public static final String DATABASE_NAME = "know-yourself-database";
    /**
     * @author Kurt Lewis
     * @date 2018.02.10
     * @param context - current application context
     * @return instance of database
     */
    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            // Need to initialize instance of database
             sInstance = Room.databaseBuilder(context,
                    AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return sInstance;
    }

}

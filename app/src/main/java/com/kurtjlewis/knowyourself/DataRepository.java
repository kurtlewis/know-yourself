package com.kurtjlewis.knowyourself;

/**
 * Created by kurt on 2/10/18.
 * @author Kurt Lewis
 * @date
 */

import android.content.Context;

import com.kurtjlewis.knowyourself.db.AppDatabase;
import com.kurtjlewis.knowyourself.db.entity.FeelingEntity;
import com.kurtjlewis.knowyourself.model.Emotion;

import java.util.Calendar;
import java.util.List;

public class DataRepository {

    private static DataRepository sInstance;

    private AppDatabase database;

    private DataRepository(Context context) {
        database = AppDatabase.getInstance(context);
    }

    public static DataRepository getInstance(Context context) {
        if (sInstance  == null) {
            sInstance = new DataRepository(context);
        }
        return sInstance;
    }

    public List<FeelingEntity> loadFeelingEntitiesAfterTimestamp(Calendar timestamp) {
        return database.feelingDao().loadFeelingsAfterTimestamp(timestamp);
    }

    public void insertFeelingEntity(FeelingEntity entity) {
        database.feelingDao().insertFeeling(entity);
    }

    public List<FeelingEntity> loadFeelingEntitiesofEmotion(Emotion emotion) {
        return database.feelingDao().loadFeelingsOfEmotion(emotion);
    }

    public void deleteFeelingEntity(FeelingEntity entity) {
        database.feelingDao().deleteFeeling(entity);
    }

    public int getFeelingCount() {
        return database.feelingDao().getFeelingCount();
    }
}

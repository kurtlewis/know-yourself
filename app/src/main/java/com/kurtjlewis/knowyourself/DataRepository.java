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

    /**
     * Creates instance of DataRepository
     * @param context - current application context
     */
    private DataRepository(Context context) {
        database = AppDatabase.getInstance(context);
    }

    /**
     * Gets instance of DataRepository. Requires current context
     * @param context - current application context
     * @return
     */
    public static DataRepository getInstance(Context context) {
        if (sInstance  == null) {
            sInstance = new DataRepository(context);
        }
        return sInstance;
    }

    /**
     * Returns all FeelingEntities created after a given timestamp
     * @param timestamp
     * @return List of FeelingEntity
     */
    public List<FeelingEntity> loadFeelingEntitiesAfterTimestamp(Calendar timestamp) {
        return database.feelingDao().loadFeelingsAfterTimestamp(timestamp);
    }

    /**
     * Inserts the given FeelingEntity
     * @param entity entity to be submitted
     */
    public void insertFeelingEntity(FeelingEntity entity) {
        database.feelingDao().insertFeeling(entity);
    }

    /**
     * loads all feelings of a given emotion
     * @param emotion
     * @return List of FeelingEntitys all of the given emotion
     */
    public List<FeelingEntity> loadFeelingEntitiesofEmotion(Emotion emotion) {
        return database.feelingDao().loadFeelingsOfEmotion(emotion);
    }

    /**
     * Deletes the given entity
     * @param entity entity to be deleted
     */
    public void deleteFeelingEntity(FeelingEntity entity) {
        database.feelingDao().deleteFeeling(entity);
    }

    /**
     * returns the count of all feelings in the database
     * @return int count of feeling objects
     */
    public int getFeelingCount() {
        return database.feelingDao().getFeelingCount();
    }
}

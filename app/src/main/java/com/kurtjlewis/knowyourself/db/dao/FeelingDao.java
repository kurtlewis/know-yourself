package com.kurtjlewis.knowyourself.db.dao;

/**
 * Created by kurt on 2/10/18.
 * @author Kurt Lewis
 * @date 2018.02.10
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.kurtjlewis.knowyourself.db.entity.FeelingEntity;
import com.kurtjlewis.knowyourself.model.Emotion;

import java.util.Calendar;
import java.util.List;

@Dao
public interface FeelingDao {
    @Query("SELECT * FROM feelings where timestamp > :timestamp")
    List<FeelingEntity> loadFeelingsAfterTimestamp(Calendar timestamp);

    @Query("SELECT * FROM feelings where emotion = :emotion")
    List<FeelingEntity> loadFeelingsOfEmotion(Emotion emotion);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFeeling(FeelingEntity feeling);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFeelings(List<FeelingEntity> feelings);

    @Delete
    void deleteFeeling(FeelingEntity feeling);
}

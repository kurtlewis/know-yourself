package com.kurtjlewis.knowyourself.db.converter;

/**
 * Created by kurt on 2/10/18.
 * @author Kurt Lewis
 * @date 2018.02.10
 */

import android.arch.persistence.room.TypeConverter;

import com.kurtjlewis.knowyourself.model.Emotion;

public class EmotionConverter {
    @TypeConverter
    public Emotion toEmotion(String emotion) {
        return emotion == null ? null : Emotion.valueOf(emotion);
    }

    @TypeConverter
    public String toString(Emotion emotion) {
        return emotion == null ? null : emotion.name();
    }
}

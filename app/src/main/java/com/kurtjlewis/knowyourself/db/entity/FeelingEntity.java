package com.kurtjlewis.knowyourself.db.entity;

/**
 * Created by kurt on 2/10/18.
 * @author Kurt Lewis
 * @date 2018.02.10
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.kurtjlewis.knowyourself.model.Emotion;
import com.kurtjlewis.knowyourself.model.Feeling;

import java.util.Calendar;

@Entity(tableName="feelings")
public class FeelingEntity implements Feeling {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Calendar timestamp;
    private int intensity;
    private Emotion emotion;
    private String notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    @Override
    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public FeelingEntity() {
        // Do nothing
    }

    public FeelingEntity(Emotion emotion, Calendar timestamp, int intensity, String notes) {
        this.emotion = emotion;
        this.timestamp = timestamp;;
        this.intensity = intensity;
        this.notes = notes;
    }

}

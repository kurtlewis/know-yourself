package com.kurtjlewis.knowyourself.persistence.model;

import java.util.Calendar;

/**
 * Created by kurt on 2/10/18.
 * @author Kurt Lewis
 * Defines methods for Feeling class
 */

public interface Feeling {
    public Emotion getEmotion();
    public int getIntensity();
    public String getNotes();
    public Calendar getTimestamp();
}

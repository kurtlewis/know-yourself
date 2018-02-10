package com.kurtjlewis.knowyourself.db.converter;

/**
 * Created by kurt on 2/10/18.
 * @author Kurt Lewis
 * @date 2018.02.10
 */

import android.arch.persistence.room.TypeConverter;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarConverter {
    @TypeConverter
    public static Calendar toCalendar(Long timestamp) {
        Calendar cal = null;
        if (timestamp != null) {
            cal = new GregorianCalendar();
            cal.setTimeInMillis(timestamp);
        }
        return cal;
    }

    @TypeConverter
    public static Long toTimestamp(Calendar calendar) {
        return calendar == null ? null : calendar.getTimeInMillis();
    }
}

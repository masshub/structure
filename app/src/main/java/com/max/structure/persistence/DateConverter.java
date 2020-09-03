package com.max.structure.persistence;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * Created by Maker on 2020/9/3.
 * Description:
 */
public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }




}

package com.augmentun.exam.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.augmentun.exam.Constants;

public class DateUtil {

    public static Date stringToDate(String dateString, String dateFormat) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToListString(Date date) {
        String dateString = null;
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FOMAT_LONG);
        dateString = sdf.format(date);
        return dateString;
    }

    public static boolean isAfterNow(Date date) {
        boolean result = false;
        if (!(date instanceof Date)) {
            return false;
        }
        Date now = new Date();
        if (date.getTime() > now.getTime()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
}

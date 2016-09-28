package com.austinnightingale.android.drywalltally.jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * TODO: refactor methods
 */

public class DateFormat {

    public static String get(String dateStr) {
        System.out.println(dateStr);
        SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        SimpleDateFormat sdfOut = new SimpleDateFormat("MMM d", Locale.US);
        Date date = null;
        try {
            date = sdfIn.parse(dateStr);
            return sdfOut.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "failed";
    }

    public static String getLong(String dateStr) {
        System.out.println(dateStr);
        SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        SimpleDateFormat sdfOut = new SimpleDateFormat("EEE, d MMMM yyyy", Locale.US);
        Date date = null;
        try {
            date = sdfIn.parse(dateStr);
            return sdfOut.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "failed";
    }
}

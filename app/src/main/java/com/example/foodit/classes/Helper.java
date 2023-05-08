package com.example.foodit.classes;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Helper {
    private static String date = "";
    private static int count = 0;
    private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmm", Locale.US);

    @SuppressLint("DefaultLocale")
    public static String id() {
        String currDate = df.format(Calendar.getInstance().getTime());
        count++;
        if (!date.equals(currDate)) {
            date = currDate;
            count = 0;
        }

        return date + String.format("%04d", count);
    }
}

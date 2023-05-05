package com.example.foodit.classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Helper {
    private static String date;
    private static int count;
    private static DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");

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

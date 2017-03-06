package com.artes.alexbispo.kiponto.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alex on 06/03/17.
 */

public class DateUtils {

    public static String DD_MM_YYYY = "dd/MM/yyyy";

    public static String format(long timeInMillis, String f){
        SimpleDateFormat format = new SimpleDateFormat(f);
        return format.format(new Date(timeInMillis));
    }
}

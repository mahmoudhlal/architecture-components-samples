package com.ma7moud.newsapp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataBindingUtils {

    /**
     * convert time zone to format date
     * @param publishedAt
     * @return format date
     */
    public static String setFormatToDate(String publishedAt){
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("d MMMM, yy", Locale.ENGLISH);
        Date date = null ;
        try {
            date = inputFormat.parse(publishedAt.replace(String.valueOf(publishedAt.charAt(publishedAt.length()-1)) , ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(date);
    }

    public static String setAuthorName(String name){
        return String.format("By: %s", name);
    }

}

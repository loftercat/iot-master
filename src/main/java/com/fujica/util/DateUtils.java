package com.fujica.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils{
    /**
     * 格式：yyyy-MM-dd
     */
    public static final String pattern1 = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式：yyyy-MM-dd
     */
    public static final String pattern2 = "yyyy-MM-dd";

    /**
     * 把字符串类型的日期转换成Date类型
     *
     * @param
     * @param ""转换日期格式
     * @return
     */
    public static Date parseDate(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String parseTimeStamp(Long timestamp){
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern1);
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}

package com.path.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author demo
 */
public class DateUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/DD");
    public static Date stringToDate(String string){
        try {
         Date  date = simpleDateFormat.parse(string);
         return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}

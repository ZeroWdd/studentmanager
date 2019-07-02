package com.wdd.studentmanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname DateFormatUtil
 * @Description None
 * @Date 2019/7/2 13:22
 * @Created by WDD
 */
public class DateFormatUtil {
    public static String getFormatDate(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}

package com.wdd.studentmanager.util;

/**
 * @Classname SnGenerateUtil
 * @Description 随机生成学号
 * @Date 2019/6/27 20:17
 * @Created by WDD
 */
public class SnGenerateUtil {
    public static String generateSn(int clazzId){
        String sn = "";
        sn = "S" + clazzId + System.currentTimeMillis();
        return sn;
    }
    public static String generateTeacherSn(int clazzId){
        String sn = "";
        sn = "T" + clazzId + System.currentTimeMillis();
        return sn;
    }
}

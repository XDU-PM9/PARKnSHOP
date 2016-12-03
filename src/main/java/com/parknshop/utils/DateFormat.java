package com.parknshop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by song on 16-12-1.
 * 与时间处理有关的类，可以直接获取时间的格式串，或者序列化
 * @author 宋正腾
 */
public class DateFormat {

    /*时间可视化格式为：2016-12-01 10:45 32*/
    static final String sDataFormat = "yyyy-MM-dd HH:mm ss";

    /**
     * 获取时间格式串
     * @return 时间的字符格式串
     */
    public static String getDateFormat(){
        return sDataFormat;
    }
}

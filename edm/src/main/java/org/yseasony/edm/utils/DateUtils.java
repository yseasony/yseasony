package org.yseasony.edm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: yseasony Date: 13-10-1 Time: 下午11:25
 */
public class DateUtils {

    private final static Logger log                     = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 日期格式 精确到秒 ：yyyyMMddHHmmss
     */
    public static final String  DATE_FORMDATE_TO_SECOND = "yyyyMMddHHmmss";

    public static long date2Long() {
        return date2Long(new Date());
    }

    public static long date2Long(Date date) {
        if (date == null) return 0l;
        return Long.parseLong(new SimpleDateFormat(DateUtils.DATE_FORMDATE_TO_SECOND).format(date));
    }

    /**
     * 将字符串时间转换为14位数字日期yyyy-MM-dd HH:mm:ss Long时间
     */
    public static Long ymdhmsFormatL(String str) {
        if (str == null || str.length() != 19) return null;
        String s = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10) + str.substring(11, 13)
                   + str.substring(14, 16) + str.substring(17, 19);
        return Long.valueOf(s);
    }

    /**
     * 将14/8位数字时间转换为yyyy-MM-dd String时间
     */
    public static String ymdFormatS(Long l) {
        if (l == null || l.toString().length() < 8) {
            log.debug("Long date Error: " + l);
            return "";
        }
        String str = l.toString();
        return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
    }

    /**
     * 将yyyy-mm-dd 转化为16位的时间数字
     */
    public static Long ymdFormatL(String str) {
        if (str == null || str.length() != 10) return null;
        StringBuilder stime = new StringBuilder();
        stime.append(str.substring(0, 4)).append(str.substring(5, 7)).append(str.substring(8, 10)).append("000000");
        return Long.valueOf(stime.toString());
    }
}

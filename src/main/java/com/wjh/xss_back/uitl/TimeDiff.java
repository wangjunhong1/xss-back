package com.wjh.xss_back.uitl;

import java.util.Date;

/**
 * @author wangjunhong
 * @description TimeDiff
 * @since 2023/4/15 20:22
 */
public class TimeDiff {
    public static String getTimeDiff(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / 1000 + "秒";
    }
}

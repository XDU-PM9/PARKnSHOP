package com.parknshop.utils;

/**
 * Created by song on 16-12-16.
 */
public class Log {
    public static boolean sDebug = true;

    private static String sClassName, sMethodName;
    private static int sLineNumber;

    /**
     * 获取日志发生位置信息
     * @param stackTraceElements
     */
    private static void getMethodInfo(StackTraceElement[] stackTraceElements) {
        sClassName = stackTraceElements[1].getFileName();
        sMethodName = stackTraceElements[1].getMethodName();
        sLineNumber = stackTraceElements[1].getLineNumber();
    }

    /**
     * 日志封装.
     * @param message
     * @return
     */
    private static String createLog(String message) {
        StringBuilder builder = new StringBuilder();
        builder.append(sMethodName)
                .append("(")
                .append(sClassName)
                .append(":")
                .append(sLineNumber).append(") ")
                .append(message);
        return builder.toString();
    }

    /**
     * 产生日志调用
     * @param msg
     */
    public static void debug(String msg) {
        if (!sDebug) return;
        getMethodInfo(new Throwable().getStackTrace());
        System.out.println(createLog(msg));
    }

    public static void debug(Object msg) {
        if (!sDebug) return;
        getMethodInfo(new Throwable().getStackTrace());
        System.out.println(createLog(msg.toString()));
    }

    /**
     * 开启日志，默认开启
     */
    public void onDebug() {
        sDebug = true;
    }

    /**
     * 关闭日志
     */
    public void offDebug() {
        sDebug = false;
    }


}

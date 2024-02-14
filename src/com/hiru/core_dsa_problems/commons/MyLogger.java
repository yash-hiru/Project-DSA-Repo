package com.hiru.core_dsa_problems.commons;

public class MyLogger {
    private static void PRINT(String level, String msg) {
        System.out.println(level + ":" + msg);
    }

    public static void info(String msg, boolean shouldLog) {
        if (shouldLog) {
            PRINT("INFO", msg);
        }
    }

    public static void info(String msg) {
        info(msg, true);
    }

    public static void warn(String msg, boolean shouldLog) {
        if (shouldLog) {
            PRINT("WARN", msg);
        }
    }

    public static void warn(String msg) {
        warn(msg, true);
    }

    public static void error(String msg, boolean shouldLog) {
        if (shouldLog) {
            PRINT("ERROR", msg);
        }
    }

    public static void error(String msg) {
        error(msg, true);
    }

}

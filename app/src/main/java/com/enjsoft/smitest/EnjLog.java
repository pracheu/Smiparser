package com.enjsoft.smitest;

import android.util.Log;

public class EnjLog {
    public static void v(String tag, Object msg) {

        StackTraceElement[] elements = new Throwable().getStackTrace();
        StackTraceElement element = elements[1];
        Log.v(element.toString(), tag + " = " + msg);
    }
}
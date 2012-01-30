package org.yseasony.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Exceptions {

    /**
     * 将CheckedException转换为UnCheckedException.
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException(e.getMessage(), e);
    }
    
    /**
     * 将ErrorStack转化为String.
     */
    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}

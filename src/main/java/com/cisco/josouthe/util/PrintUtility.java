package com.cisco.josouthe.util;

public class PrintUtility {
    public static String arrayToString( int... array ) {
        StringBuilder sb = new StringBuilder("int[] { ");
        for( int i : array )
            sb.append(i).append(" ");
        sb.append("}");
        return sb.toString();
    }
}

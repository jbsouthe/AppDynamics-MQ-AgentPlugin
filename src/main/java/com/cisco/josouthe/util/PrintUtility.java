package com.cisco.josouthe.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintUtility {
    private static Pattern hostPortPattern = Pattern.compile("(?<hostname>.*)\\((?<port>\\d+)\\)");

    public static String arrayToString( int... array ) {
        StringBuilder sb = new StringBuilder("int["+ array.length +"] { ");
        for( int i : array )
            sb.append(i).append(" ");
        sb.append("}");
        return sb.toString();
    }

    public static String arrayToString( String... array ) {
        StringBuilder sb = new StringBuilder("String["+ array.length +"] { ");
        for( String s : array )
            sb.append(s).append(" ");
        sb.append("}");
        return sb.toString();
    }

    public static String parseHostPortString( String inputString ) {
        //host.domain.COM(4401)
        Matcher matcher = hostPortPattern.matcher(inputString);
        if( matcher.matches() ) {
            return String.format("%s:%s", matcher.group("hostname"), matcher.group("port"));
        }
        return inputString;
    }
}

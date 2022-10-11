package com.cisco.josouthe.util;

import junit.framework.TestCase;

public class PrintUtilityTest extends TestCase {

    public void testParseHostPortString() {
        String input = "QNDCMWMQ04.MY.COM(4401)";
        String output = PrintUtility.parseHostPortString(input);
        System.out.println(String.format("input '%s' output '%s'", input, output));
    }
}
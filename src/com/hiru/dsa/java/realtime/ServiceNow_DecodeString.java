package com.hiru.dsa.java.realtime;

public class ServiceNow_DecodeString {
    // Online Java Compiler
// Use this editor to write, compile and run your Java code online

    /**
     * a2[b]
     * decoded String---- [a] [abb]
     * Abb
     *
     * A2[b2[ab]]
     *
     *
     * A=decode(2,str)
     * case-- Number--> get the inner pattern,by trimming,  decode
     *
     *
     * Abababbabab
     * Nuber for repeation --Not part of data
     *
     * Stack
     */
    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
        System.out.println(decode("a2[b]"));
    }


    static String decode(String s) {
        String decodedString;
        if (s == null || s.length() == 0) {
            return "";
        }
        int i = 0;

        // CASE-----Simple characters
        while (!isNumeric(s.charAt(i)) && s.charAt(i) != '[' && s.charAt(i) != ']') {
            decodedString.append(s.charAt(i) + "");
        }

        // CASE-----Process Number
        String numStr = "";
        int i = 0;
        while (isNumber(s.charAt(i))) {
            num += s.charAt(i);
            i++;
        }

        if (numStr.length() > 0) {
            int number = strToNum(numStr);
            // Get the inner String and decode
            // Recurse
            String d = decode(s.substring(), i - 1, getIndexOfClosingBracket(s)); //
            for (j = 0; j < number; j++)
                decodedString.append(d + ""); // Append multiple occurances
        }
        return decodedString;
    }


    static int strToNum(String s) {
        return Integer.valueOf(s);
    }

    static int getIndexOfClosingBracket(String s) {
        for (int i = s.lenth() - 1; i >= 0; i--) {
            if (s.chatAt(i) == ']') {
                return i;
            }
        }
    }


}

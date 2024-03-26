package com.hiru.dsa.java.datastructures.string;

import org.apache.commons.lang3.StringUtils;

public class NAIVE_RECURSION_DecodeString {
    // Online Java Compiler
// Use this editor to write, compile and run your Java code online


    public static void main(String[] args) {
        //System.out.println(decode("a2[b]")); //Worked
        // System.out.println(decode("A2[b2[ab]]"));// Worked
        System.out.println(decode("A2[b2[ab]cc]dd"));// Worked
    }


    /**
     * // Company: ServiceNow
     * Learnings ======================
     * .....................1. Case1 and Case2 Combinations within recursion
     * .....................2. LIBRARY METHODS:
     * ...........................StringUtils.isNumeric() and Index Handling required revision (EXTREMELY IMPORTANT)
     * ...........................
     * .....................3. Edge case misses for combination of inputs
     * .....................4.
     *
     * @param s
     *
     * @return
     */
    static String decode(String s) {
        String decodedString = "";
        if (s.isEmpty() || s.length() == 1)
            return s;
        // CASE1-----Process Number and remaining string
        if (StringUtils.isNumeric(s.substring(0, 1))) {

            String numStr = "";
            int i = 0;
            while (StringUtils.isNumeric("" + s.charAt(i))) {
                numStr += s.charAt(i);
                i++;
            }
            if (numStr.length() > 0) {
                int number = strToNum(numStr);
                // Get the inner String and decode
                // Recurse
                String d = decode(s.substring(i + 1, getIndexOfClosingBracket(s))); //Recurse1 --for inner normal substring
                for (int j = 0; j < number; j++) {
                    decodedString += (d + ""); // Append multiple occurances
                }
            }
            return decodedString + decode(s.substring(getIndexOfClosingBracket(s) + 1)); //Recurse2 --for substring after this special one
        }
        // CASE2-----Process Normal String
        else {
            // Normal Characters
            int i = 0;
            // CASE-----Simple characters
            while (i < s.length() && !StringUtils.isNumeric("" + s.charAt(i)) && s.charAt(i) != '[' && s.charAt(i) != ']') {
                decodedString += (s.charAt(i) + "");
                i++;
            }
            return decodedString + decode(s.substring(i)); //Recurse3 -- for special substring
        }
    }


    static int strToNum(String s) {
        return Integer.valueOf(s);
    }

    static int getIndexOfClosingBracket(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ']') {
                return i;
            }
        }
        return -1;
    }
}

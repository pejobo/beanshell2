/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators.standard;

/**
 *
 * @author Scott Stevenson
 */
public class StringMethods {

    /**
     * Math Operators.
     * <p>
     * This section defines mathematical methods for operator overloads.
     * 
     * 
     */
    public static String plus(String left, String right) {
        return left + right;
    }

    public static String[] plus(String[] left, String[] right) {
        String[] result = null;
        if (left.length == right.length) {
            result = new String[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] + right[i];
            }
        }
        else if (left.length == 1) {
            result = new String[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] + right[i];
            }
        }
        else if (right.length == 1) {
            result = new String[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] + right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }

    public static String[] plus(String[] left, String right) {
        String[] result = new String[left.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = left[i] + right;
        }
        return result;
    }
    public static String[] plus(String left, String[] right) {
        String[] result = new String[right.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = left + right[i];
        }
        return result;
    }
    
    public static char[] range(Character min, Character max) {
        int count = max-min;
        char[] result = new char[count];
        for(int i=0;i<count;i++) {
            result[i] = (char) (min+i);
        }
        return result;
    }

}

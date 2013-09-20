/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators.standard;

import bsh.operators.Extension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Scott Stevenson
 */
public class ListMethods {
    
    @Extension
    public static List asList(double[] array) {
        return Arrays.asList(array);
    }
    @Extension
    public static List asList(float[] array) {
        return Arrays.asList(array);
    }
    @Extension
    public static List asList(int[] array) {
        return Arrays.asList(array);
    }
    @Extension
    public static List asList(String[] array) {
        return Arrays.asList(array);
    }
    
    //----------------------------------------------------------------------
    // Accessor Methods
    //----------------------------------------------------------------------
    public static Object getAt(List list, Integer index) {
        return list.get(index);
    }

    public static List getAt(List list, int[] indices) {
        ArrayList result = new ArrayList(indices.length);
        for (int i = 0; i < indices.length; i++) {
            int j = indices[i];
            result.add(list.get(j));
        }
        return result;
    }

    public static void putAt(List list, Integer index, Integer value) {
        list.set(index, value);
    }
    public static void putAt(List list, int[] indices, int[] values) {
        for (int i = 0; i < indices.length; i++) {
            int j = indices[i];
            list.set(j, values[i]);
        }
    }
    
    //----------------------------------------------------------------------
    // Cast Methods
    //----------------------------------------------------------------------
    
    public static List cast(int[] indices) {
        ArrayList result = new ArrayList(indices.length);
        for (int i = 0; i < indices.length; i++) {
            result.add(indices[i]);
        }
        return result;
    }
    public static List cast(float[] indices) {
        ArrayList result = new ArrayList(indices.length);
        for (int i = 0; i < indices.length; i++) {
            result.add(indices[i]);
        }
        return result;
    }
    public static List cast(double[] indices) {
        ArrayList result = new ArrayList(indices.length);
        for (int i = 0; i < indices.length; i++) {
            result.add(indices[i]);
        }
        return result;
    }
    public static List cast(Object[] indices) {
        ArrayList result = new ArrayList(indices.length);
        for (int i = 0; i < indices.length; i++) {
            result.add(indices[i]);
        }
        return result;
    }
    public static List cast(String[] indices) {
        ArrayList result = new ArrayList(indices.length);
        for (int i = 0; i < indices.length; i++) {
            result.add(indices[i]);
        }
        return result;
    }
    
    
}

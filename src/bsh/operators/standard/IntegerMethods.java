/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators.standard;

/**
 *
 * @author Scott Stevenson
 */
public class IntegerMethods {

    /**
     * Math Operators.
     * <p>
     * This section defines mathematical methods for operator overloads.
     * 
     * 
     */
    public static Integer plus(Integer left, Integer right) {
        return left + right;
    }
    public static Integer minus(Integer left, Integer right) {
        return left - right;
    }

    public static Integer times(Integer left, Integer right) {
        return left * right;
    }

    public static Integer divide(Integer left, Integer right) {
        return left / right;
    }

    public static Integer uminus(Integer left) {
        return -left;
    }

    public static int[] plus(int[] left, int[] right) {
        int[] result = null;
        if (left.length == right.length) {
            result = new int[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] + right[i];
            }
        }
        else if (left.length == 1) {
            result = new int[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] + right[i];
            }
        }
        else if (right.length == 1) {
            result = new int[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] + right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }
    public static int[] plus(int[] left, Integer right) {
        return plus(left, new int[] {right});
    }
    public static int[] plus(Integer left, int[] right) {
        return plus(new int[] {left}, right);
    }
    public static int[] minus(int[] left, int[] right) {
        int[] result = null;
        if (left.length == right.length) {
            result = new int[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] - right[i];
            }
        }
        else if (left.length == 1) {
            result = new int[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] - right[i];
            }
        }
        else if (right.length == 1) {
            result = new int[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] - right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }
    public static int[] minus(int[] left, Integer right) {
        return minus(left, new int[] {right});
    }
    public static int[] minus(Integer left, int[] right) {
        return minus(new int[] {left}, right);
    }
    public static int[] times(int[] left, int[] right) {
        int[] result = null;
        if (left.length == right.length) {
            result = new int[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] * right[i];
            }
        }
        else if (left.length == 1) {
            result = new int[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] * right[i];
            }
        }
        else if (right.length == 1) {
            result = new int[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] * right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }
    public static int[] times(int[] left, Integer right) {
        return times(left, new int[] {right});
    }
    public static int[] times(Integer left, int[] right) {
        return times(new int[] {left}, right);
    }
    public static int[] divide(int[] left, int[] right) {
        int[] result = null;
        if (left.length == right.length) {
            result = new int[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] / right[i];
            }
        }
        else if (left.length == 1) {
            result = new int[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] / right[i];
            }
        }
        else if (right.length == 1) {
            result = new int[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] / right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }
    public static int[] divide(int[] left, Integer right) {
        return divide(left, new int[] {right});
    }
    public static int[] divide(Integer left, int[] right) {
        return divide(new int[] {left}, right);
    }
    public static int[] uminus(int[] left) {
            int[] result = new int[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = -left[i];
            }
        return result;
    }
    
    public static int[] range(Integer min, Integer max) {
        return range(min,max,1);
    }
    public static int[] range(Integer min, Integer max, Integer inc) {
        int range = max-min;
        int count = range/inc + 1;  // add 1 for the starting value
//        int count = range/inc + range%inc;
        int[] result = new int[count];
        for(int i=0;i<count;i++) {
            result[i] = min+inc*i;
        }
        return result;
    }
    

    //----------------------------------------------------------------------
    // Cast Methods
    //----------------------------------------------------------------------
    public static Integer cast(int left) {
        return left;
    }
    public static Object[] cast(int[] left) {
        Integer[] result = new Integer[left.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = left[i];
        }
        return result;
    }

    //----------------------------------------------------------------------
    // Accessor Methods
    //----------------------------------------------------------------------
    public static Integer getAt(int[] array, Integer index) {
        return array[index];
    }

    public static int[] getAt(int[] array, int[] indices) {
        int[] result = new int[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int j = indices[i];
            result[i] = array[j];
        }
        return result;
    }

    public static void putAt(int[] array, Integer index, Integer value) {
        array[index] = value;
    }

    public static void putAt(int[] array, int[] indices, int[] values) {
        for (int i = 0; i < indices.length; i++) {
            int j = indices[i];
            array[j] = values[i];
        }
    }
}

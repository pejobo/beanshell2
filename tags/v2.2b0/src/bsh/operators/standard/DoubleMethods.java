/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators.standard;

/**
 *
 * @author Scott Stevenson
 */
public class DoubleMethods {

    /**
     * Math Operators.
     * <p>
     * This section defines mathematical methods for operator overloads.
     * 
     * 
     */
    public static Double plus(Double left, Double right) {
        return left + right;
    }
    public static Double minus(Double left, Double right) {
        return left - right;
    }

    public static Double times(Double left, Double right) {
        return left * right;
    }

    public static Double divide(Double left, Double right) {
        return left / right;
    }

    public static Double uminus(Double left) {
        return -left;
    }

    public static Double power(Double x, Double y) {
        return Math.pow(x, y);
    }
    
    public static double[] plus(double[] left, double[] right) {
        double[] result = null;
        if (left.length == right.length) {
            result = new double[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] + right[i];
            }
        }
        else if (left.length == 1) {
            result = new double[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] + right[i];
            }
        }
        else if (right.length == 1) {
            result = new double[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] + right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }
    public static double[] plus(double[] left, Double right) {
        return plus(left, new double[] {right});
    }
    public static double[] plus(Double left, double[] right) {
        return plus(new double[] {left}, right);
    }
    public static double[] minus(double[] left, double[] right) {
        double[] result = null;
        if (left.length == right.length) {
            result = new double[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] - right[i];
            }
        }
        else if (left.length == 1) {
            result = new double[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] - right[i];
            }
        }
        else if (right.length == 1) {
            result = new double[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] - right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }
    public static double[] minus(double[] left, Double right) {
        return minus(left, new double[] {right});
    }
    public static double[] minus(Double left, double[] right) {
        return minus(new double[] {left}, right);
    }
    public static double[] times(double[] left, double[] right) {
        double[] result = null;
        if (left.length == right.length) {
            result = new double[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] * right[i];
            }
        }
        else if (left.length == 1) {
            result = new double[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] * right[i];
            }
        }
        else if (right.length == 1) {
            result = new double[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] * right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }
    public static double[] times(double[] left, Double right) {
        return times(left, new double[] {right});
    }
    public static double[] times(Double left, double[] right) {
        return times(new double[] {left}, right);
    }
    public static double[] divide(double[] left, double[] right) {
        double[] result = null;
        if (left.length == right.length) {
            result = new double[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] / right[i];
            }
        }
        else if (left.length == 1) {
            result = new double[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] / right[i];
            }
        }
        else if (right.length == 1) {
            result = new double[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] / right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }
    public static double[] divide(double[] left, Double right) {
        return divide(left, new double[] {right});
    }
    public static double[] divide(Double left, double[] right) {
        return divide(new double[] {left}, right);
    }
    public static double[] uminus(double[] left) {
            double[] result = new double[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = -left[i];
            }
        return result;
    }
    public static double[] power(double[] left, Double exponent) {
            double[] result = new double[left.length];
            double e = exponent;
            for (int i = 0; i < result.length; i++) {
                result[i] = Math.pow(left[i],e);
            }
        return result;
    }
    
    public static double[] range(Double min, Double max) {
        return range(min,max,1.0);
    }
    public static double[] range(Double min, Double max, Double inc) {
        double range = max-min;
        int count = (int)(range/inc) + 1;  // add 1 for the starting value
//        int count = (int)(range/inc) + (int)(range%inc);
        double[] result = new double[count];
        for(int i=0;i<count;i++) {
            result[i] = min+inc*i;
        }
        return result;
    }
    
    

    //----------------------------------------------------------------------
    // Cast Methods
    //----------------------------------------------------------------------
    public static Double cast(Float left) {
        return left.doubleValue();
    }

    public static Double cast(Integer left) {
        return left.doubleValue();
    }

    public static Double cast(double left) {
        return left;
    }

    public static Double cast(int left) {
        return (double) left;
    }

    public static Double cast(float left) {
        return (double) left;
    }

    public static double[] cast(float[] left) {
        double[] result = new double[left.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = left[i];
        }
        return result;
    }
    public static double[] cast(int[] left) {
        double[] result = new double[left.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = left[i];
        }
        return result;
    }
    
    public static Object[] cast(double[] left) {
        Double[] result = new Double[left.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = left[i];
        }
        return result;
    }


    //----------------------------------------------------------------------
    // Accessor Methods
    //----------------------------------------------------------------------
    public static Double getAt(double[] array, Integer index) {
        return array[index];
    }

    public static double[] getAt(double[] array, int[] indices) {
        double[] result = new double[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int j = indices[i];
            result[i] = array[j];
        }
        return result;
    }

    public static void putAt(double[] array, Integer index, Double value) {
        array[index] = value;
    }

    public static void putAt(double[] array, int[] indices, double[] values) {
        for (int i = 0; i < indices.length; i++) {
            int j = indices[i];
            array[j] = values[i];
        }
    }
}

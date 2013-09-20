/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators.standard;

/**
 *
 * @author Scott Stevenson
 */
public class FloatMethods {

    /**
     * Math Operators.
     * <p>
     * This section defines mathematical methods for operator overloads.
     * 
     * 
     */
    public static Float plus(Float left, Float right) {
        return left + right;
    }
    public static Float minus(Float left, Float right) {
        return left - right;
    }

    public static Float times(Float left, Float right) {
        return left * right;
    }

    public static Float divide(Float left, Float right) {
        return left / right;
    }

    public static Float uminus(Float left) {
        return -left;
    }

    public static Float power(Float x, Float y) {
        return (float)Math.pow(x.doubleValue(), y.doubleValue());
    }
    
    public static float[] plus(float[] left, float[] right) {
        float[] result = null;
        if (left.length == right.length) {
            result = new float[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] + right[i];
            }
        }
        else if (left.length == 1) {
            result = new float[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] + right[i];
            }
        }
        else if (right.length == 1) {
            result = new float[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] + right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }
    public static float[] plus(float[] left, Float right) {
        return plus(left, new float[] {right});
    }
    public static float[] plus(Float left, float[] right) {
        return plus(new float[] {left}, right);
    }
    public static float[] minus(float[] left, float[] right) {
        float[] result = null;
        if (left.length == right.length) {
            result = new float[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] - right[i];
            }
        }
        else if (left.length == 1) {
            result = new float[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] - right[i];
            }
        }
        else if (right.length == 1) {
            result = new float[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] - right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }
    public static float[] minus(float[] left, Float right) {
        return minus(left, new float[] {right});
    }
    public static float[] minus(Float left, float[] right) {
        return minus(new float[] {left}, right);
    }
    public static float[] times(float[] left, float[] right) {
        float[] result = null;
        if (left.length == right.length) {
            result = new float[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] * right[i];
            }
        }
        else if (left.length == 1) {
            result = new float[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] * right[i];
            }
        }
        else if (right.length == 1) {
            result = new float[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] * right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }
    public static float[] times(float[] left, Float right) {
        return times(left, new float[] {right});
    }
    public static float[] times(Float left, float[] right) {
        return times(new float[] {left}, right);
    }
    public static float[] divide(float[] left, float[] right) {
        float[] result = null;
        if (left.length == right.length) {
            result = new float[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] / right[i];
            }
        }
        else if (left.length == 1) {
            result = new float[right.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[0] / right[i];
            }
        }
        else if (right.length == 1) {
            result = new float[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left[i] / right[0];
            }
        }
        else {
            throw new IllegalArgumentException("Array length mismatch");
        }
        return result;
    }
    public static float[] divide(float[] left, Float right) {
        return divide(left, new float[] {right});
    }
    public static float[] divide(Float left, float[] right) {
        return divide(new float[] {left}, right);
    }
    public static float[] uminus(float[] left) {
            float[] result = new float[left.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = -left[i];
            }
        return result;
    }
    public static float[] power(float[] left, Float exponent) {
            float[] result = new float[left.length];
            float e = exponent;
            for (int i = 0; i < result.length; i++) {
                result[i] = (float)Math.pow(left[i],e);
            }
        return result;
    }
    
    

    //----------------------------------------------------------------------
    // Cast Methods
    //----------------------------------------------------------------------
    public static Float cast(Integer left) {
        return left.floatValue();
    }

    public static Float cast(float left) {
        return left;
    }

    public static Float cast(int left) {
        return (float) left;
    }

    public static float[] cast(int[] left) {
        float[] result = new float[left.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = left[i];
        }
        return result;
    }
    public static Object[] cast(float[] left) {
        Float[] result = new Float[left.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = left[i];
        }
        return result;
    }


    //----------------------------------------------------------------------
    // Accessor Methods
    //----------------------------------------------------------------------
    public static Float getAt(float[] array, Integer index) {
        return array[index];
    }

    public static float[] getAt(float[] array, int[] indices) {
        float[] result = new float[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int j = indices[i];
            result[i] = array[j];
        }
        return result;
    }

    public static void putAt(float[] array, Integer index, Float value) {
        array[index] = value;
    }

    public static void putAt(float[] array, int[] indices, float[] values) {
        for (int i = 0; i < indices.length; i++) {
            int j = indices[i];
            array[j] = values[i];
        }
    }
}

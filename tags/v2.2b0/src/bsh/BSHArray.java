/**
 * 
 * 
 */
package bsh;

import bsh.operators.OperatorProvider;
import bsh.operators.OperatorUtil;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Array syntax is:<p>
 * [value1, value2, value3, ...]<p>
 * or<p>
 * [value1, min:max, min:inc:max, ...]<p>
 * Internal ranges are expanded into the array.
 * 
 */
class BSHArray extends SimpleNode {

    BSHArray(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter)
            throws EvalError {

        int length = jjtGetNumChildren();
        ArrayList result = new ArrayList(length);

        Class elementType = null;
        boolean mixedType = false;

        if (length == 0) {
            return new Object[0];  // Return empty array
        }

        int expandedLength = 0;

        for (int i = 0; i < length; i++) {

            SimpleNode node = (SimpleNode) jjtGetChild(i);
            Object result_i = node.eval(callstack, interpreter);

            result_i = Primitive.unwrap(result_i);
            Class type_i = result_i.getClass();
            result.add(result_i);
            if (node instanceof BSHRangeExpression) {
                if (type_i.isArray()) {
                    type_i = type_i.getComponentType();
                    if (type_i.isPrimitive()) {
                        type_i = Primitive.boxType(type_i);
                    }
                    expandedLength += Array.getLength(result_i);
                }
                else {
                    throw new EvalError("Range expression result is not an array",this,callstack);
                }
            }
            else {
                expandedLength++;
            }

//            if(result_i instanceof Primitive) {
//                Primitive pi = (Primitive) result_i;
//                type_i = pi.getValue().getClass();
//                result.add(pi.getValue());
//            }
//            else {
//                result.add(result_i);
//            }

            // Keep track of what type of object this array contains
            if (i == 0) {
                elementType = type_i;
            }
            else {
                if (elementType.isAssignableFrom(type_i)) {
                    //OK
                }
                else {
                    elementType = OperatorUtil.commonSuperclass(elementType, type_i);
                }
            }
        }
        // Convert the List to an array
        // Expand any embedded Ranges into the array
        int ii = 0;
        if (Integer.class.isAssignableFrom(elementType)) {
            int[] result2 = new int[expandedLength];
            for (int i = 0; i < result.size(); i++) {
                if (jjtGetChild(i) instanceof BSHRangeExpression) {
                    int[] range = castToint(result.get(i),callstack,interpreter.getNameSpace());
                    for (int j = 0; j < range.length; j++) {
                        result2[ii++] = range[j];
                    }
                }
                else {
                    result2[ii++] = (Integer) result.get(i);
                }
            }
            return result2;
        }
        else if (Float.class.isAssignableFrom(elementType)) {
            float[] result2 = new float[expandedLength];
            for (int i = 0; i < result.size(); i++) {
                if (jjtGetChild(i) instanceof BSHRangeExpression) {
                    float[] range = castTofloat(result.get(i),callstack,interpreter.getNameSpace());
                    for (int j = 0; j < range.length; j++) {
                        result2[ii++] = range[j];
                    }
                }
                else {
                    result2[ii++] = (Float) result.get(i);
                }
            }
            return result2;
        }
        else if (Number.class.isAssignableFrom(elementType)) {
            double[] result2 = new double[expandedLength];
            for (int i = 0; i < result.size(); i++) {
                if (jjtGetChild(i) instanceof BSHRangeExpression) {
                    double[] range = castTodouble(result.get(i),callstack,interpreter.getNameSpace());
                    for (int j = 0; j < range.length; j++) {
                        result2[ii++] = range[j];
                    }
                }
                else {
                    result2[ii++] = ((Number) result.get(i)).doubleValue();
                }
            }
            return result2;
        }
        else if (Boolean.class.isAssignableFrom(elementType)) {
            boolean[] result2 = new boolean[result.size()];
            for (int i = 0; i < result.size(); i++) {
                result2[i] = (Boolean) result.get(i);
            }
            return result2;
        }
        else if (String.class.isAssignableFrom(elementType)) {
            String[] result2 = new String[result.size()];
            for (int i = 0; i < result.size(); i++) {
                result2[i] = (String) result.get(i);
            }
            return result2;
        }
        else if (Character.class.isAssignableFrom(elementType)) {
            char[] result2 = new char[expandedLength];
            for (int i = 0; i < result.size(); i++) {
                if (jjtGetChild(i) instanceof BSHRangeExpression) {
                    char[] range = (char[]) result.get(i);
                    for (int j = 0; j < range.length; j++) {
                        result2[ii++] = range[j];
                    }
                }
                else {
                    result2[ii++] = (Character) result.get(i);
                }
            }
            return result2;
        }
        else {
            Object[] rarray = (Object[]) Array.newInstance(elementType, result.size());
            return result.toArray(rarray);
        }
    }
    
    private int[] castToint(Object array, CallStack callstack, NameSpace ns) throws EvalError {
        Class targetc = new int[0].getClass();
        if(targetc.isInstance(array)) return (int[]) array;
        try {
            Method m = OperatorProvider.findCastMethod(ns, array.getClass(), targetc, null);
            if(m==null) throw new EvalError("Cannot convert range type to array type",this,callstack);
            return (int[]) m.invoke(null, array);
        }
        catch (IllegalAccessException ex) {
            throw new EvalError("Cannot convert range type to array type",this,callstack);
        }
        catch (IllegalArgumentException ex) {
            throw new EvalError("Cannot convert range type to array type",this,callstack);
        }
        catch (InvocationTargetException ex) {
            throw new EvalError("Cannot convert range type to array type",this,callstack);
        }
        
    }
    private float[] castTofloat(Object array, CallStack callstack, NameSpace ns) throws EvalError {
        Class targetc = new float[0].getClass();
        if(targetc.isInstance(array)) return (float[]) array;
        try {
            Method m = OperatorProvider.findCastMethod(ns, array.getClass(), targetc, null);
            if(m==null) throw new EvalError("Cannot convert range type to array type",this,callstack);
            return (float[]) m.invoke(null, array);
        }
        catch (IllegalAccessException ex) {
            throw new EvalError("Cannot convert range type to array type",this,callstack);
        }
        catch (IllegalArgumentException ex) {
            throw new EvalError("Cannot convert range type to array type",this,callstack);
        }
        catch (InvocationTargetException ex) {
            throw new EvalError("Cannot convert range type to array type",this,callstack);
        }
        
    }
    private double[] castTodouble(Object array, CallStack callstack, NameSpace ns) throws EvalError {
        Class targetc = new double[0].getClass();
        if(targetc.isInstance(array)) return (double[]) array;
        try {
            Method m = OperatorProvider.findCastMethod(ns, array.getClass(), targetc, null);
            if(m==null) throw new EvalError("Cannot convert range type to array type",this,callstack);
            return (double[]) m.invoke(null, array);
        }
        catch (IllegalAccessException ex) {
            throw new EvalError("Cannot convert range type to array type",this,callstack);
        }
        catch (IllegalArgumentException ex) {
            throw new EvalError("Cannot convert range type to array type",this,callstack);
        }
        catch (InvocationTargetException ex) {
            throw new EvalError("Cannot convert range type to array type",this,callstack);
        }
        
    }
}

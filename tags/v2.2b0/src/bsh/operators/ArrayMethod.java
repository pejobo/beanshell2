/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class holds the operator method returned from the OperatorProvider class.
 * The opMethod member is the java static method that must be invoked to perform the 
 * operation.  For binary operators it is sometimes required to cast the left or right 
 * arguments to the method.  If a cast is needed the OperatorProvider will return a method
 * to case either the left argument or right argument, but never both.  If both left and right
 * case methods are null then no cast is required.
 * 
 * @author Scott Stevenson
 */
public class ArrayMethod extends ExtendedMethod {

    public ExtendedMethod elementMethod;       // The method to be invoked

    /**
     * 
     * @param opMethod
     * @param castMethods
     * @param castTypes 
     */
    public ArrayMethod(ExtendedMethod elementMethod, Class[] castTypes, boolean[] isArrayElement) {
        this.elementMethod = elementMethod;
        this.castedTypes = castTypes;
        if(elementMethod.resultType!=null) this.resultType = java.lang.reflect.Array.newInstance(elementMethod.resultType, 0).getClass();
        // Roll up cast method to the top so they are not executed for each element.
        this.castMethods = new Method[castTypes.length];
        for (int i = 0; i < isArrayElement.length; i++) {
            if(!isArrayElement[i]) {
                this.castMethods[i] = elementMethod.castMethods[i];
                elementMethod.castMethods[i] = null;
            }
        }
    }

    /**
     * Evaluates a unary or binary operation.
     * 
     * @param lhs fort argument, or single argument for unary operations
     * @param rhs second argument, or null for unary operations
     * @return
     * @throws RuntimeException 
     */
    @Override
    public Object eval(Object... args) throws RuntimeException {
        try {
            if (castMethods != null) {
                if (castMethods.length != args.length) {
                    throw new IllegalArgumentException("Number of arguments does not match.");
                }
                for (int i = 0; i < this.castMethods.length; i++) {
                    Method method = this.castMethods[i];
                    if (method != null) {
                        args[i] = method.invoke(null, args[i]);
                    }
                }
            }
            // Make sure all array arguments have same length
            boolean[] isArray = new boolean[args.length];
            int len = -1;
            for (int i = 0; i < args.length; i++) {
                Object argi = args[i];
                if(argi.getClass().isArray()) {
                    isArray[i] = true;
                    int leni = java.lang.reflect.Array.getLength(argi);
                    if(len==-1) len = leni;
                    if(len!=leni) {
                        throw new IllegalArgumentException("Arrays have different lengths");
                    }
                }
                else {
                    isArray[i] = false;
                }
            }
            // Loop over each element in the array(s) and invoke the method on the array element
            Object result = java.lang.reflect.Array.newInstance(elementMethod.resultType, len);
            Object[] argsi = new Object[args.length];
            for(int i=0;i<len;i++) {
                for (int j = 0; j < args.length; j++) {
                    argsi[j] = args[j];
                    if(isArray[j]) {
                        argsi[j] = java.lang.reflect.Array.get(argsi[j], i);
                    }
                }
                Object resulti = elementMethod.eval(argsi);
                java.lang.reflect.Array.set(result, i, resulti);
            }
            return result;
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException("Error evaluating method: " + elementMethod.getName(), ex);
        }
        catch (IllegalArgumentException ex) {
            throw new RuntimeException("Error evaluating method: " + elementMethod.getName(), ex);
        }
        catch (InvocationTargetException ex) {
            throw new RuntimeException("Error evaluating method: " + elementMethod.getName(), ex);
        }
    }

    @Override
    public String getName() {
        return elementMethod.getName();
    }

}

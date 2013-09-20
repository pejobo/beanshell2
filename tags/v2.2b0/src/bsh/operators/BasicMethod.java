/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
public class BasicMethod extends ExtendedMethod {

    public Method opMethod;       // The method to be invoked

    public BasicMethod(Method opMethod) {
        this.opMethod = opMethod;
        this.resultType = opMethod.getReturnType();
    }

    public BasicMethod(Method opMethod, Method leftCastMethod, Class leftCastType,
            Method rightCastMethod, Class rightCastType) {
        this.opMethod = opMethod;
        this.castMethods = new Method[]{leftCastMethod, rightCastMethod};
        this.castedTypes = new Class[]{leftCastType, rightCastType};
        this.resultType = opMethod.getReturnType();
    }

    /**
     * 
     * @param opMethod
     * @param castMethods
     * @param castTypes 
     */
    public BasicMethod(Method opMethod, Method[] castMethods, Class[] castTypes) {
        this.opMethod = opMethod;
        this.castMethods = castMethods;
        this.castedTypes = castTypes;
        this.resultType = opMethod.getReturnType();
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
            return opMethod.invoke(null, args);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException("Could not evaluage method: " + opMethod.getName(), ex);
        }
        catch (IllegalArgumentException ex) {
            throw new RuntimeException("Could not evaluage method: " + opMethod.getName(), ex);
        }
        catch (InvocationTargetException ex) {
            throw new RuntimeException("Could not evaluage method: " + opMethod.getName(), ex);
        }
    }

    @Override
    public String getName() {
        return opMethod.getName();
    }

}

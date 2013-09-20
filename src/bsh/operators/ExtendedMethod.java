/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators;

import java.lang.reflect.Method;

/**
 *
 * @author Scott Stevenson
 */
public abstract class ExtendedMethod {

    public Method[] castMethods;  // Cast methods to convert input parameters (may be null)
    public Class[] castedTypes;   // Original types input into cast methods
    public Class resultType;
    
    /**
     * Evaluates a unary or binary operation.
     *
     * @param lhs fort argument, or single argument for unary operations
     * @param rhs second argument, or null for unary operations
     * @return
     * @throws RuntimeException
     */
    public abstract Object eval(Object... args) throws RuntimeException;

    /**
     * Compares the types used when this method was created to the types provided.
     * This method is provided to perform quick checks on any cached methods before searching for
     * another.
     * 
     * @param types
     * @return 
     */
    public boolean matchTypes(Class... types) {
        if (this.castedTypes == null) {
            if(types==null || types.length==0) return true;
        }
        return castedTypes.equals(types);
    }
    
    public abstract String getName();
    
}

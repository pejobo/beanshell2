/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Scott Stevenson
 */
public class OperatorUtil {

    public static final Integer NO_METHOD = new Integer(0);

    /**
     * Attempts find a valid operator getAt(object,key) method for the target object.
     * If a method is found, evaluate the method and return the result.
     * If no method is found, return NO_METHOD.
     * 
     * @param target target object for getAt(object,key)
     * @param key key value for getAt(object,key)
     * @return NO_METHOD if a suitable getAt method was not found, or result of getAt(object,key) method.
     * This result may be null.
     */
    public static Object getAt(Object target, Object key) {
        //TODO Implement this method

        return NO_METHOD;
    }

    public static Class commonSuperclass(Class... classes) {
        Class rc = null;
        for (int i = 0; i < classes.length; ++i) {
            Class tc = classes[i];
            if (tc == null || tc.isInterface()) {
                continue;  // or (better) throw an exception
            }
            if (rc == null) {
                rc = tc;
            }
            else {
                while (!rc.isAssignableFrom(tc)) {
                    rc = rc.getSuperclass();
                }
            }
            if (rc == Object.class) {
                break;
            }
        }
        return rc;
    }

    /** 
     * Returns the multi-dimensional array type of for an array of elementType with number of dimensions ndim.
     * 
     * @param elementType
     * @param ndim
     * @return 
     */
    public static Class getArrayType(Class elementType, int ndim) {
        int[] dim = new int[ndim];
        Class type = java.lang.reflect.Array.newInstance(elementType, dim).getClass();
        return type;
    }

    /** 
     * Returns an array with all super types and interfaces implemented by type.
     * If type is an array, this method forwards the call to getAllArraySuperTypes().
     * 
     * @param elementType
     * @param ndim
     * @return 
     */
    public static Class[] getAllSuperTypes(Class type) {
        if (type.isArray()) {
            return getAllArraySuperTypes(type);
        }
        Set<Class> types = new HashSet();
        while (type != null) {
            types.add(type);
            Class[] interfaces = type.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                Class class1 = interfaces[i];
                types.add(class1);
            }
            if (type.isPrimitive()) {
                type = null;
            }
            else if (!type.equals(Object.class)) {
                type = type.getSuperclass();
                if(type!=null && type.equals(Object.class)) type = null;  // Don't include plan Object type except for first time through.
            }
            else {
                type = null;
            }
        }
        return types.toArray(new Class[types.size()]);
    }
    
    /**
     * Return all class supertypes and interfaces for all arrays assignable from
     * an array represented by atype.
     * <p>
     * Assume Foo is a superclass of Bar, and we have a class representing atype=Bar[].class.
     * This method returns an array with elements {Bar[].class, Foo[].class}.
     * Similarly, if Bar implements other interfaces or supertypes, they will also be returned.
     * 
     * @param atype Array class type
     * @return Array of all supertype arrays.
     */
    public static Class[] getAllArraySuperTypes(Class atype) {
        if (!atype.isArray()) {
            return getAllSuperTypes(atype);
        }
        int ndim=0;
        Class type = atype;
        while(type.isArray()) {
            ndim++;
            type = type.getComponentType();
        }
        Class[] allElementTypes = getAllSuperTypes(type);
        Class[] allArrayTypes = new Class[allElementTypes.length];
        for (int i = 0; i < allElementTypes.length; i++) {
            allArrayTypes[i] =  getArrayType(allElementTypes[i],ndim);
        }
        return allArrayTypes;
    }
}

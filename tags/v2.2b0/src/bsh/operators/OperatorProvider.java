/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators;

import bsh.NameSpace;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * 
 * 
 * @author Scott Stevenson
 */
public class OperatorProvider {

    Map<String, Set<Method>> namedMethods;
    Map<Class, Set<Method>> castMethods;  // Cast methods stored by return type Class

    public OperatorProvider() {
        namedMethods = new HashMap();
        castMethods = new HashMap();
    }

    /**
     * Add all operator and extended methods from this class to the cache.
     * 
     * @param className
     * @throws ClassNotFoundException 
     */
    public void cacheExtendedMethods(String className) {
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        }
        catch (ClassNotFoundException ex) {
            return;
        }
        Map<String, Set<Method>> allMethods = new HashMap();
        cacheMethods(clazz, allMethods);

        // Store method sets as list to speed up searches
        for (Map.Entry<String, Set<Method>> entry : allMethods.entrySet()) {
            String name = entry.getKey();
            Set<Method> methods = entry.getValue();
            Set<Method> methodList = namedMethods.get(name);
            if(methodList==null) {
                methodList = new HashSet();
                namedMethods.put(name, methodList);
            }
            methodList.addAll(methods);
            namedMethods.put(name, methodList);
        }

        // Build special collection of cast methods to speed searches
        Set<Method> allCastMethods = namedMethods.get(OperatorType.CAST.getMethodName());
        if (allCastMethods != null) {
            for (Method method : allCastMethods) {
                Class returnType = method.getReturnType();
                Class[] allTypes = OperatorUtil.getAllSuperTypes(returnType);
                for (int i = 0; i < allTypes.length; i++) {
                    addCastMethod(allTypes[i], method);
                }
            }
        }


    }

    private void addCastMethod(Class returnType, Method method) {
        Set<Method> cmethods = castMethods.get(returnType);
        if (cmethods == null) {
            cmethods = new HashSet();
            castMethods.put(returnType, cmethods);
        }
        cmethods.add(method);
    }

    /**
     * Cache all the operator methods supplied by the methodProvider.
     * 
     * @param methodProvider 
     */
    private static void cacheMethods(Class methodProvider, Map<String, Set<Method>> allMethods) {
        // Get all methods from the provider class
        Method[] methods2 = methodProvider.getMethods();
        for (int j = 0; j < methods2.length; j++) {
            Method method = methods2[j];
            int mods = method.getModifiers();
            String mname = method.getName();
            // If we found a public static method with the same name as operator, cache it for 
            // use as an opererator method.  
            // We do not check that the arguments match, so it's up to the 
            // developer of the operator methods class to get this right.
            if (Modifier.isStatic(mods) && Modifier.isPublic(mods)) {
                if(method.isAnnotationPresent(Extension.class) || OperatorType.find(mname)!=null) {
                Set<Method> mset = allMethods.get(mname);
                if (mset == null) {
                    mset = new HashSet();
                    allMethods.put(mname, mset);
                }
                mset.add(method);
                }
            }
        }
    }

    /**
     * Find operator method that matches the provided argument types.
     * Searches in the provided namespace, and up the parent namespace tree until found, or
     * until no more parents.
     * 
     * Rules for matching parameter types are:
     * <ol>
     * <li>Match exact types for all arguments.</li>
     * <li>Match super type for first argument, exact type for second</li>
     * <li>Match derived types for both arguments</li>
     * <li>Match derived type for first argument, cast type for second</li>
     * <li>If allowFirstTypeCast, Match cast type for both arguments</li>
     * <li>Additional parameter types are handled similarly.</li>
     * <li>Fail - no match</li>
     * </ol>
     * @param op
     * @param left
     * @param right
     * @return 
     */
    public static ExtendedMethod findMethod(NameSpace namespace, String name, ExtendedMethod cachedMethod, boolean allowFirstTypeCast, Class... types) {
        OperatorProvider op = namespace.getExtendedMethodProvider();
        ExtendedMethod em = op.findMethod2(namespace,name, cachedMethod, allowFirstTypeCast, types);
        NameSpace parent = namespace.getParent();
        while(em==null && parent!=null) {
            op = parent.getExtendedMethodProvider();
            // keep using top level namespace in findMethod2 to provide casting methods
            em = op.findMethod2(namespace,name, cachedMethod, allowFirstTypeCast, types);
            parent = parent.getParent();
        }
        return em;
    }

    /**
     * Find operator method that matches the provided types.
     * Note: In the old implementation we didn't need the namespace, but now
     * due to the parent-child nature of the namespace, we still need the Namespace 
     * because in case there are cast methods in higher level namespaces.
     * 
     * Rules for matching parameter types are:
     * <ol>
     * <li>Match exact types for all arguments.</li>
     * <li>Match super type for first argument, exact type for second</li>
     * <li>Match derived types for both arguments</li>
     * <li>Match derived type for first argument, cast type for second</li>
     * <li>If allowFirstTypeCast, Match cast type for both arguments</li>
     * <li>Additional parameter types are handled similarly.</li>
     * <li>Fail - no match</li>
     * </ol>
     * @param op
     * @param left
     * @param right
     * @return 
     */
    public ExtendedMethod findMethod2(NameSpace namespace, String name, ExtendedMethod cachedMethod, boolean allowFirstTypeCast, Class... types) {
        if (cachedMethod != null) {
            if (cachedMethod.matchTypes(types)) {
                return cachedMethod;
            }
        }
        Set<Method> methods = this.namedMethods.get(name);
        if (methods == null) {
            return null;
        }
        ExtendedMethod result = this.findBestMethod(namespace, types, methods, allowFirstTypeCast);
        // If could not find method and if one or more types are arrays,
        // Try to find method that takes array element types
        if (result == null && !"getAt".equals(name) && !"putAt".equals(name)) {
            boolean arrays = false;
            Class[] types2 = new Class[types.length];
            boolean[] isArrayElement = new boolean[types.length];
            for (int i = 0; i < types.length; i++) {
                types2[i] = types[i];
                if (types2[i].isArray()) {
                    types2[i] = types2[i].getComponentType();
                    arrays = true;
                    isArrayElement[i] = true;
                }
                else {
                    isArrayElement[i] = false;
                }
            }
            if (arrays) {
                result = findMethod2(namespace, name, null, allowFirstTypeCast, types2);
                if (result != null) {
                    result = new ArrayMethod(result, types, isArrayElement);
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * Returns a cast method to convert an object of type classType to an object of
     * type targetType.
     * Searches in the provided namespace, and up the parent namespace tree until found, or
     * until no more parents.
     * 
     * @param classType Type of existing object.
     * @param targetType Type of cast object.
     * @return static Method to convert from classType to targetType, or null of none exist.
     * 
     */
    public static Method findCastMethod(NameSpace namespace, Class classType, Class targetType, Method cachedMethod) {
        OperatorProvider op = namespace.getExtendedMethodProvider();
        Method em = op.findCastMethod(classType, targetType, cachedMethod);
        NameSpace parent = namespace.getParent();
        while(em==null && parent!=null) {
            op = parent.getExtendedMethodProvider();
            em = op.findCastMethod(classType, targetType, cachedMethod);
            parent = parent.getParent();
        }
        return em;
    }

    /**
     * Returns a cast method to convert an object of type classType to an object of
     * type targetType.
     * 
     * @param classType Type of existing object.
     * @param targetType Type of cast object.
     * @return static Method to convert from classType to targetType, or null of none exist.
     * 
     */
    public Method findCastMethod(Class classType, Class targetType, Method cachedMethod) {
        if (classType == null || targetType == null) {
            return null;
        }

        if (cachedMethod != null) {
            if (targetType.equals(cachedMethod.getReturnType())) {
                if (classType.isAssignableFrom(cachedMethod.getParameterTypes()[0]));
                {
                    return cachedMethod;
                }
            }
        }
        return findCastMethod(classType, targetType);
    }

    /**
     * Returns a cast method to convert an object of type classType to an object of
     * type targetType.
     * 
     * @param classType Type of existing object.
     * @param targetType Type of cast object.
     * @return static Method to convert from classType to targetType, or null of none exist.
     * 
     */
    private Method findCastMethod(Class classType, Class targetType) {
        Set<Method> methods = this.castMethods.get(targetType);
        if (methods == null) {
            return null;
        }
        for (Method method : methods) {
            Class[] types = method.getParameterTypes();
            if (types[0].equals(classType)) {
                return method;
            }
        }
        return null;
    }

    private ExtendedMethod findBestMethod(NameSpace namespace, Class[] types, Set<Method> methods, boolean allowFirstTypeCast) {
        int nparms = types.length;
        int ndim = types.length;
        // For now do not accept null types
        for (int i = 0; i < types.length; i++) {
            if (types[i] == null) {
                return null;
            }
        }
        int[] lda = new int[ndim];  // leading dimensions of ndim array
        lda[0] = 1;
        if (ndim > 1) {
            lda[1] = 2; // First type (supertype, exacttype)
            if (allowFirstTypeCast) {
                lda[1] = 3; // First type (cast, supertype, exacttype)
            }
        }
        for (int i = 2; i < ndim; i++) {
            lda[i] = lda[i - 1] * 3; // (cast, supertype, exacttype)
        }
        int[][] indices = new int[methods.size()][];
        Method[] methods2 = new Method[methods.size()];
        int im=0;
        for (Method method : methods) {
            methods2[im] = method;
            Class[] mtypes = method.getParameterTypes();
            if (mtypes.length == nparms) {
                int[] mindices = new int[nparms];
                for (int j = 0; j < types.length; j++) {
                    Class typej = types[j];
                    if (mtypes[j].equals(typej)) {
                        mindices[j] = 0;
                    }
                    else if (mtypes[j].isAssignableFrom(typej) && (mtypes[j].isArray() == typej.isArray())) {
                        mindices[j] = 1;
                    }
                    else if (j > 0 || allowFirstTypeCast) {
                        Method castMethod = findCastMethod(namespace, typej, mtypes[j], null);
                        if (castMethod != null) {
                            mindices[j] = 2;
                        }
                        else {
                            mindices = null;
                            break; // break j loop
                        }
                    }
                    else {
                        mindices = null;
                        break; // break j loop
                    }

                }
                indices[im] = mindices;
            }
            im++;
        }
        // Find the highest ranked method
        int index = -1;
        int minRank = Integer.MAX_VALUE;
        for (int i = 0; i < indices.length; i++) {
            int[] mindices = indices[i];
            if (mindices != null) {
                int rank = computeRank(mindices, lda);
                if (rank < minRank) {
                    minRank = rank;
                    index = i;
                }
            }
        }
        if (index < 0) {
            return null;
        }
        Method bestMethod = methods2[index];
        Class[] mtypes = bestMethod.getParameterTypes();
        Method[] castMethods2 = new Method[mtypes.length];
        for (int i = 0; i < castMethods2.length; i++) {
            if (!types[i].isAssignableFrom(mtypes[i])) {
                castMethods2[i] = findCastMethod(namespace, types[i], mtypes[i], null);;
            }
        }
        return new BasicMethod(bestMethod, castMethods2, types);
    }

    public final int computeRank(int[] indices, int[] lda) {
        int rank = 0;
        for (int i = 0; i < indices.length; i++) {
            rank += indices[i] * lda[i];
        }
        return rank;
    }

    /**
     * Returns a new collection of all extended/operator methods in target namespace
     * including all parent namespaces.
     * 
     * @param ns
     * @return 
     */
    public static Map<String, Set<Method> > findAllExtendedMethods(NameSpace ns) {
        Map<String, Set<Method> > result = new HashMap();
        while(ns!=null) {
            OperatorProvider op = ns.getExtendedMethodProvider();
            for (Map.Entry<String, Set<Method>> entry : op.namedMethods.entrySet()) {
                String mname = entry.getKey();
                Set<Method> methods = entry.getValue();
                Set<Method> methods2 = result.get(mname);
                if(methods2==null) {
                    methods2 = new HashSet();
                    result.put(mname, methods2);
                }
                methods2.addAll(methods);
            }
            ns = ns.getParent();
        }
        return result;
    }
    
}

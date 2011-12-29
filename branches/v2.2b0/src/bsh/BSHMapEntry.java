/**
 * 
 * 
 */
package bsh;

import java.util.Map;

class BSHMapEntry extends SimpleNode {

    BSHMapEntry(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter)
            throws EvalError {
        throw new EvalError("BSDMapEntry cannot must be evaluated with the target map", this, callstack);
    }

    /**
     * Evaluate this node and store all entries in the provided map.
     * 
     * @param callstack
     * @param interpreter
     * @param map
     * @return Class type of the value added to map.
     * @throws EvalError 
     */
    public Class eval(CallStack callstack, Interpreter interpreter, Map map)
            throws EvalError {


        Class elementType = null;

        SimpleNode keynode = (SimpleNode) jjtGetChild(0);
        Object key = keynode.eval(callstack, interpreter);
        key = Primitive.unwrap(key);

        SimpleNode valuenode = (SimpleNode) jjtGetChild(1);
        Object value = valuenode.eval(callstack, interpreter);
        value = Primitive.unwrap(value);

        map.put(key, value);

        if (value != null) {
            return value.getClass();
        }
        return null;
    }
}

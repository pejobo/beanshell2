/**
 * 
 * 
 */
package bsh;

import java.util.LinkedHashMap;

class BSHMap extends SimpleNode {

    BSHMap(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter)
            throws EvalError {

        int length = jjtGetNumChildren();

        if (length == 0) {
            return new LinkedHashMap();  // Return empty array
        }

        LinkedHashMap result = new LinkedHashMap(length * 4 / 3 + 3);

        for (int i = 0; i < length; i++) {

            BSHMapEntry node = (BSHMapEntry) jjtGetChild(i);
            node.eval(callstack, interpreter, result);
        }
        return result;
    }
}

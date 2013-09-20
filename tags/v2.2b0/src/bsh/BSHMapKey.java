/**
 * 
 * 
 * 
 * 
 */
package bsh;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class BSHMapKey extends SimpleNode {

    public static volatile boolean internStrings = true;
    public Object value;
    public boolean isVariable = false;

    BSHMapKey(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter)
            throws EvalError {
        if (isVariable) {
            try {
                String name = (String) value;
                Object value = interpreter.getNameSpace().getVariable(name);
                if (value == null) {
                    throw new EvalError("Variable key not found: " + name,this,callstack);
                }
                return value;
            }
            catch (UtilEvalError ex) {
                    throw new EvalError("Variable key not found: "+value,this,callstack,ex);
            }
        }
        else {
            return value;
        }
    }

    private char getEscapeChar(char ch) {
        switch (ch) {
            case 'b':
                ch = '\b';
                break;

            case 't':
                ch = '\t';
                break;

            case 'n':
                ch = '\n';
                break;

            case 'f':
                ch = '\f';
                break;

            case 'r':
                ch = '\r';
                break;

            // do nothing - ch already contains correct character
            case '"':
            case '\'':
            case '\\':
                break;
        }

        return ch;
    }

    public void charSetup(String str) {
        char ch = str.charAt(0);
        if (ch == '\\') {
            // get next character
            ch = str.charAt(1);

            if (Character.isDigit(ch)) {
                ch = (char) Integer.parseInt(str.substring(1), 8);
            }
            else {
                ch = getEscapeChar(ch);
            }
        }

        value = new Primitive(new Character(ch).charValue());
    }

    void stringSetup(String str) {
        StringBuilder buffer = new StringBuilder();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch == '\\') {
                // get next character
                ch = str.charAt(++i);

                if (Character.isDigit(ch)) {
                    int endPos = i;

                    // check the next two characters
                    int max = Math.min(i + 2, len - 1);
                    while (endPos < max) {
                        if (Character.isDigit(str.charAt(endPos + 1))) {
                            endPos++;
                        }
                        else {
                            break;
                        }
                    }

                    ch = (char) Integer.parseInt(str.substring(i, endPos + 1), 8);
                    i = endPos;
                }
                else {
                    ch = getEscapeChar(ch);
                }
            }

            buffer.append(ch);
        }

        String s = buffer.toString();
        if (internStrings) {
            s = s.intern();
        }
        value = s;
    }
}

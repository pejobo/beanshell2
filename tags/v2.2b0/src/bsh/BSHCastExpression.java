/*****************************************************************************
 *                                                                           *
 *  This file is part of the BeanShell Java Scripting distribution.          *
 *  Documentation and updates may be found at http://www.beanshell.org/      *
 *                                                                           *
 *  Sun Public License Notice:                                               *
 *                                                                           *
 *  The contents of this file are subject to the Sun Public License Version  *
 *  1.0 (the "License"); you may not use this file except in compliance with *
 *  the License. A copy of the License is available at http://www.sun.com    * 
 *                                                                           *
 *  The Original Code is BeanShell. The Initial Developer of the Original    *
 *  Code is Pat Niemeyer. Portions created by Pat Niemeyer are Copyright     *
 *  (C) 2000.  All Rights Reserved.                                          *
 *                                                                           *
 *  GNU Public License Notice:                                               *
 *                                                                           *
 *  Alternatively, the contents of this file may be used under the terms of  *
 *  the GNU Lesser General Public License (the "LGPL"), in which case the    *
 *  provisions of LGPL are applicable instead of those above. If you wish to *
 *  allow use of your version of this file only under the  terms of the LGPL *
 *  and not to allow others to use your version of this file under the SPL,  *
 *  indicate your decision by deleting the provisions above and replace      *
 *  them with the notice and other provisions required by the LGPL.  If you  *
 *  do not delete the provisions above, a recipient may use your version of  *
 *  this file under either the SPL or the LGPL.                              *
 *                                                                           *
 *  Patrick Niemeyer (pat@pat.net)                                           *
 *  Author of Learning Java, O'Reilly & Associates                           *
 *  http://www.pat.net/~pat/                                                 *
 *                                                                           *
 *****************************************************************************/
package bsh;

import bsh.operators.OperatorProvider;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
Implement casts.

I think it should be possible to simplify some of the code here by
using the Types.getAssignableForm() method, but I haven't looked 
into it.
 */
class BSHCastExpression extends SimpleNode {

    Method castMethod;

    public BSHCastExpression(int id) {
        super(id);
    }

    /**
    @return the result of the cast.
     */
    public Object eval(
            CallStack callstack, Interpreter interpreter) throws EvalError {
        NameSpace namespace = callstack.top();
        Class toType = ((BSHType) jjtGetChild(0)).getType(
                callstack, interpreter);
        SimpleNode expression = (SimpleNode) jjtGetChild(1);

        // evaluate the expression
        Object fromValue = expression.eval(callstack, interpreter);
        Class fromType = fromValue.getClass();

        // TODO: need to add isJavaCastable() test for strictJava
        // (as opposed to isJavaAssignable())
        try {
            //SWS BEGIN  Insert code for overloaded cast operations
            Object fromValue2 = Primitive.unwrap(fromValue);
            Class fromType2 = (fromValue2!=null)?fromValue2.getClass():null;
            castMethod = OperatorProvider.findCastMethod(interpreter.getNameSpace(),fromType2, toType, castMethod);
            if (castMethod != null) {
                Object toValue;
                try {
                    toValue = castMethod.invoke(null, fromValue2);
                    return Primitive.wrap(toValue, toType);  // Wraps value if primitive, or returns toValue unchanged.
                }
                catch (IllegalAccessException ex) {
                }
                catch (IllegalArgumentException ex) {
                }
                catch (InvocationTargetException ex) {
                }
            }
            //SWS END
            
            return Types.castObject(fromValue, toType, Types.CAST);
        }
        catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
    }
}

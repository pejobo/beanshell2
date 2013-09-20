/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators;

import bsh.ParserConstants;

/**
 *
 * @author Scott Stevenson
 */
public enum OperatorType {
    PLUS("+","plus",true),
    MINUS("-","minus",true),
    TIMES("*","times",true),
    DIVIDE("/","divide",true),
    PLUS_EQUALS("+=","plusEquals",false),
    MINUS_EQUALS("-=","minusEquals",false),
    TIMES_EQUALS("*=","timesEquals",false),
    DIVIDE_EQUALS("/=","divideEquals",false),
    GETAT("[]","getAt",false),
    PUTAT("[]","putAt",false),
    CAST("()","cast",false),
    UMINUS("-","negate",true),
    POWER("**","power",true),
    RANGE("..","range",true);


    private String operator;
    private String methodName;
    private boolean allowLeftCast;

    private OperatorType(String symbol,String methodName, boolean allowLeftCast) {
        this.operator = symbol;
        this.methodName = methodName;
        this.allowLeftCast = allowLeftCast;
    }

    public String getSymbol() {
        return operator;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean getAllowLeftCast() {
        return allowLeftCast;
    }
    
    public static OperatorType getType(int kind) {
        switch(kind) {
            case ParserConstants.PLUS: return PLUS;
            case ParserConstants.MINUS: return MINUS;
            case ParserConstants.STAR: return TIMES;
            case ParserConstants.SLASH: return DIVIDE;
            case ParserConstants.PLUSASSIGN: return PLUS_EQUALS;
            case ParserConstants.MINUSASSIGN: return MINUS_EQUALS;
            case ParserConstants.STARASSIGN: return TIMES_EQUALS;
            case ParserConstants.SLASHASSIGN: return DIVIDE_EQUALS;
            default:
                return null;
        }
    }

    /**
     * Finds an operator type by it's method name.
     * 
     * @param mname
     * @return 
     */
    public static OperatorType find(String mname) {
        if(mname==null) return null;
        OperatorType[] types = OperatorType.values();
        for (int i = 0; i < types.length; i++) {
            if(mname.equals(types[i].getMethodName())) return types[i];
        }
        return null;
    }
}

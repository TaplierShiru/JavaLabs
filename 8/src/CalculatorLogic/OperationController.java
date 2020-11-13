package CalculatorLogic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OperationController {

    public final static String EMPTY_STRING = "";

    public final static String PLUS = "+";
    public final static String MINUS = "-";
    public final static String MULT = "*";
    public final static String DIV = "/";
    public final static String POW = "^";
    public final static String SQRT_FULL = "sqrt";
    public final static String SQRT_SMALL = "s";
    public final static String EQUAL = "=";
    public final static String MATH_POINT = ",";

    public final static String RESET = "c";
    public final static String RESET_LAST = "l";
    public final static String RESET_LAST_FILL = "c-last";

    public final static Set<String> SIGN_SET = new HashSet<>(
            Arrays.asList(PLUS, MINUS, MULT, DIV, EQUAL, POW, SQRT_FULL, SQRT_SMALL)
    );

    public final static Set<String> OP_INPLACE = new HashSet<>(
            Arrays.asList(SQRT_FULL, SQRT_SMALL)
    );


    public static float SignToOperation(String sign, float first, float second) {

        switch (sign){
            case PLUS:{
                return first + second;
            }

            case MINUS:{
                return first - second;
            }

            case MULT:{
                return first * second;
            }

            case DIV:{
                if (Math.abs(second) <= 1e-7){
                    throw new ArithmeticException("Divide by zero!");
                }

                return first / second;
            }

            case POW:{
                return (float)Math.pow(first, second);
            }

            case SQRT_FULL:
            case SQRT_SMALL: {

                if (first < 0.0f){
                    throw new ArithmeticException("Negative number in sqrt operation!");
                }

                return (float)Math.sqrt(first);
            }
        }

        return Float.NaN;
    }

    public static boolean isGoodSign(String sign){
        return SIGN_SET.contains(sign.toLowerCase());
    }
    public static boolean isOperationInPlace(String sign) {
        return OP_INPLACE.contains(sign.toLowerCase());
    }

    public static boolean isReset(String sign){
        return OperationController.RESET.equals(sign.toLowerCase());
    }

    public static boolean isResetLast(String sign){
        return OperationController.RESET_LAST.equals(sign.toLowerCase()) ||
                OperationController.RESET_LAST_FILL.equals(sign.toLowerCase());
    }

}

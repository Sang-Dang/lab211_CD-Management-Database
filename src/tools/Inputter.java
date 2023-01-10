package tools;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Inputter {
    private Inputter() {}
    
    private static final Scanner IN = new Scanner(System.in);
    
    /**
     * Input any type using predefined conditions 
     * @param <T> datatype to input
     * @param msg message to display before input
     * @param condition success/failure condition for input - success = true
     * @param control input function (using Scanner)
     * @param errormsg error message when fail
     * @return variable with defined data value
     */
    public static <T> T input(String msg, Predicate<T> condition, Function<T,T> control, String errormsg) {
        boolean cont;
        T returnValue = null;
        do {
            cont = true;
            try {
                System.out.print(msg);
                returnValue = control.apply(returnValue);
                if(!condition.test(returnValue)) {
                    throw new Exception(errormsg);
                }
            } catch(Exception e) {
                System.out.println(e);
                cont = false;
            }
        } while(!cont);
        return returnValue;
    }
    
    public static String inputStringPattern(String msg, String pattern) {
        return input(
                msg,
                p -> p.matches(pattern),
                f -> IN.nextLine().trim(),
                "Input does not match the pattern " + Util.regexIDToString(pattern)
        );
    }
    
    public static String inputString(String msg, boolean allowBlank) {
        return input(
                msg,
                p -> allowBlank ? true : Validate.nonBlankString(p),
                f -> IN.nextLine().trim(),
                "String cannot be empty"
        ); 
    }
    
    public static double inputDouble(String msg) {
        return input(
                msg,
                p -> true,
                f -> Double.parseDouble(IN.nextLine().trim()),
                ""
        );
    }
    
    public static int inputBoundedInt(String msg, int lowerBound, int upperBound) {
        return input(
                msg,
                p ->  p <= upperBound && p >= lowerBound,
                f -> Integer.parseInt(IN.nextLine().trim()),
                "Input out of bounds"
        );
    }
    
    public static String inputYesNo(String msg) {
        return input(
                msg,
                p -> p.equalsIgnoreCase("y") || p.equalsIgnoreCase("n"),
                f -> IN.nextLine().trim(),
                "Input must be Y or N"
        );
    }
}
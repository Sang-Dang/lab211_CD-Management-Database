/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author User
 */
public class Validate {
    private Validate() {}
    
    public static boolean nonBlankString(String s) {
        s = s.trim();
        return !("".equals(s) || s.isEmpty());
    }
    
    public static boolean doubleRange(double d, double lowerBound, double upperBound, boolean inclusive) {
        // casual safeguard for when I'm an idiot and input values the wrong way around :(
        if(lowerBound > upperBound) {
            double temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }
        
        return inclusive ? d <= upperBound && d >= lowerBound : d < upperBound && d > lowerBound;
    }
    
    public static boolean stringPattern(String s, String pattern) {
        return s.matches(pattern);
    }
}

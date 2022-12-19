package tools;

/**
 *
 * @author User
 */
public class Util {
    private Util() {}
    
    /**
     * Converts regular expressions to a string format - NOTE: THIS ONLY WORKS FOR BASIC REGEX IN THE FORMAT ABC\\d{3}
     * @param regex Regular Expression ID
     * @return String
     */
    public static String regexIDToString(String regex) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(i = 0; i < regex.length() && regex.charAt(i) != '\\'; i++) {
            sb.append(regex.charAt(i));
        }
        int count = Integer.parseInt(String.valueOf(regex.charAt(i+3)));
        for(int j = 0; j < count; j++) {
            sb.append("x");
        }
        
        return sb.toString();
    }
    
    public static void printDivider() {
        System.out.println("------------------------------");
    }
}

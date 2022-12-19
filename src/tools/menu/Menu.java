package tools.menu;

import manager.MenuItems;
import tools.Inputter;

/**
 *
 * @author User
 */
public class Menu {
    public static void printTable(String[] options) {
        System.out.println("");
        System.out.println(new Table().generateTable(options));
    }
    public static void printTable(String[] options, String title) {
        System.out.println("");
        System.out.println(new Table().generateTable(options,title));
    }
    public static void printNumberedTable(String[] options) {
        System.out.println("");
        System.out.println(new NumberedTable().generateTable(options));
    }
    public static void printNumberedTable(String[] options, String title) {
        System.out.println("");
        System.out.print(new NumberedTable().generateTable(options,title));
    }
    
    public static MenuItems getChoice(String[] options, String title) {
        printNumberedTable(options,title);
        int input = Inputter.inputBoundedInt("Enter choice: ", 1, options.length);
        return MenuItems.valueOfDescription(options[input - 1]);
    }
}

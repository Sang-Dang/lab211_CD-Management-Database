/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import controller.CDController;
import controller.factory.ControllerInterface;
import java.util.Arrays;
import tools.Inputter;
import tools.Singleton;
import tools.Util;
import tools.menu.Menu;

/**
 *
 * @author User
 */
public class Manager {

    // MENU FUNCTION IMPLEMENTATIONS
    public void addCD() {
        Object temp;
        if ((temp = CDController.addModel()) != null) {
            System.out.print("Added: ");
            CDController.viewModelDetails(temp);
        } else {
            System.out.println("CD add failed.");
        }
    }

    public void removeCD() {
        Object temp;
        if ((temp = CDController.deleteModel()) != null) {
            System.out.print("Removed: ");
            CDController.viewModelDetails(temp);
        } else {
            System.out.println("CD removal failed.");
        }
    }

    public void searchCDByTitle() {
        System.out.println("Found " + CDController.searchByTitle() + " CDs.");
    }

    public void displayCatalog() {
        System.out.println("Showing " + CDController.showAll() + " CDs");
    }

    public void displayAllAlphabetically() {
        CDController.showSorted();
    }

    public void updateCD() {
        if (CDController.updateModel() != null) {
            System.out.println("Update successful.");
        } else {
            System.out.println("Update unsuccessful.");
        }
    }

    public void saveAll() {
        if (CDController.saveModels()) {
            System.out.println("Save success!");
        } else {
            System.out.println("Save fail...");
        }
    }

    // ---------------------- 
    private static final String MAIN_TITLE = "CD HOUSE MANAGER";

    public Manager() {
        init_Controllers();
        init_Data();
    }

    ControllerInterface CDController;

    public final boolean init_Controllers() {
        CDController = Singleton.getInstance(CDController.class);

        return CDController != null;
    }

    public final void init_Data() {
        CDController.loadModels();
    }

    /**
     * CALL THIS TO RUN.
     */
    public void run() {
        boolean cont;
        do {
            cont = true;
            String[] options = Arrays.copyOf(CDController.getOptionList(), CDController.getOptionList().length + 1);
            options[CDController.getOptionList().length] = "Exit";
            MenuItems choice = Menu.getChoice(options, MAIN_TITLE);
            System.out.println("");
            if (choice != MenuItems.EXIT) {
                Util.printDivider();
                System.out.println("## " + choice.getDescription().toUpperCase() + " ##");
            }
            switch (choice) {
                case ADD_CD:
                    this.addCD();
                    break;
                case REMOVE_CD:
                    this.removeCD();
                    break;
                case SEARCH_CD_BY_TITLE:
                    this.searchCDByTitle();
                    break;
                case DISPLAY_CATALOG:
                    this.displayCatalog();
                    break;
                case DISPLAY_ALL_ALPHABETICALLY:
                    this.displayAllAlphabetically();
                    break;
                case UPDATE_CD:
                    this.updateCD();
                    break;
                case SAVE_ALL:
                    this.saveAll();
                    break;
                default:
                    if (CDController.getChanged()) {
                        if (Inputter.inputYesNo("Changes detected. Do you want to save? (Y/N) ").equalsIgnoreCase("Y")) {
                            this.saveAll();
                        }
                    }
                    System.out.println("Exiting system...");
                    cont = false;
            }
            if (cont) {
                Util.printDivider();
            }
        } while (cont);
    }
}

package manager;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public enum MenuItems {
    ADD_CD("Add CD"),
    REMOVE_CD("Remove CD"),
    SEARCH_CD_BY_TITLE("Search CD by Title"),
    DISPLAY_CATALOG("Display catalog"),
    DISPLAY_ALL_ALPHABETICALLY("Display all alphabetically"),
    UPDATE_CD("Update CD"),
    SAVE_ALL("Save all"),
    EXIT("Exit");
    
    private final String description;
    
    private static final Map<String, MenuItems> MAP = new HashMap<>();
    static {
        for(MenuItems m: values()) {
            MAP.put(m.description, m);
        }
    }
    
    private MenuItems(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public static MenuItems valueOfDescription(String description) {
        return MAP.get(description);
    }
}

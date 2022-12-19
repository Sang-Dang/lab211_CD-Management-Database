package model;

import model.factory.AbstractModel;
import tools.Validate;
import exceptions.ModelException;

/**
 * Model for CD. Constructor format: String ID, String title, String collection, String type, double price
 * 
 * @author User
 */
public class CDModel extends AbstractModel {
    
    private static final double MAX_PRICE = 100000; // imagine a CD selling for more than $100,000
    private static final double MIN_PRICE = 0; // you don't owe me money :)
    private static final String ID_PATTERN = "CD\\d{4}";
    
    public enum CDType {
        AUDIO("Audio"),
        VIDEO("Video");
        
        private final String description;
        CDType(String des) {
            this.description = des;
        }
        
        public String getDescription() {
            return this.description;
        }
        
        public static String enumToString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            for(CDType c: values()) {
                sb.append(c.description).append(" ");
            }
            sb.append("]");
            return sb.toString();
        }
   }
    public enum CDCollection {
        GAME("Game"),
        MOVIE("Movie"),
        MUSIC("Music");
        
        private final String description;
        CDCollection(String des) {
            this.description = des;
        }
        
        public String getDescription() {
            return this.description;
        }
        
        public static String enumToString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            for(CDCollection c: values()) {
                sb.append(c.description).append(" ");
            }
            sb.append("]");
            return sb.toString();
        }
    }
    
    private CDCollection collection;
    private CDType type;
    private double price;
    
    public CDModel() {
        super();
        this.collection = null;
        this.type = null;
        price = 0;
    }
    public CDModel(String ID, String title, String collection, String type, double price) throws ModelException {
        super(ID, title);
        setCollection(collection);
        setType(type);
        setPrice(price);
    }
    
    public String getCollection() {
        return collection.description;
    }
    public final void setCollection(String collection) throws ModelException {
        collection = collection.toUpperCase();
        try {
            this.collection = CDCollection.valueOf(collection);
        } catch(IllegalArgumentException | NullPointerException e) {
            throw new ModelException("Collection unvalidated");
        }
    }
    public String getType() {
        return type.description;
    }
    public final void setType(String type) throws ModelException {
        type = type.toUpperCase();
        try {
            this.type = CDType.valueOf(type);
        } catch(IllegalArgumentException | NullPointerException e) {
            throw new ModelException("Type unvalidated");
        }
    } 
    public double getPrice() {
        return price;
    }
    public final void setPrice(double price) throws ModelException {
        if(validatePrice(price)) {
            this.price = price;
        } else {
            throw new ModelException("Price unvalidated");
        }
    }
    
    @Override
    protected boolean validateID(String id) {
        return Validate.stringPattern(id, ID_PATTERN);
    }
    @Override
    protected boolean validateTitle(String title) {
        return Validate.nonBlankString(title);
    }
    public boolean validatePrice(double price) {
        return Validate.doubleRange(price, MIN_PRICE, MAX_PRICE, true);
    }
    
    public static String getPattern() {
        return ID_PATTERN;
    }
}

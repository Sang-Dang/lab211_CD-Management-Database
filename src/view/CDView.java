package view;

import view.factory.AbstractView;
import model.CDModel;
import tools.Inputter;
import tools.Util;

/**
 * CD View: Used for showing CDs on the screen and BASIC input checking (like nonblank)
 * 
 * @author User
 */
public class CDView extends AbstractView<CDModel>{
    
    // Standard input without validation. Validation occurs in Controller
    
    // override to print pattern too. default prints only "Input ID: "
    @Override
    public String inputID() {
        return Inputter.inputString("Input ID of the " + getModelName() + " (" + Util.regexIDToString(CDModel.getPattern()) + "): ", false);
    }
    
    public double inputPrice() {
        return Inputter.inputDouble("Input CD price: ");
    }
    
    public String inputCollection() {
        return Inputter.inputString("Input CD collection " + getCollections() + ": ", false);
    }
    
    public String inputType() {
        return Inputter.inputString("Input CD type " + getTypes() + ": ", false);
    }
    
    // Raw input for updating
    public String inputRawID() {
        return Inputter.inputString("Input ID of the CD to be updated (" + Util.regexIDToString(CDModel.getPattern()) + "): ", false);
    }
    
    public String inputRawTitle() {
        return Inputter.inputString("Input new CD title: ", true);
    }
    
    public String inputRawCollection() {
        return Inputter.inputString("Input new CD collection " + getCollections() + ": ", true);
    }
    
    public String inputRawType() {
        return Inputter.inputString("Input new CD type: " + getTypes() + ": ", true);
    }
    
    public String inputRawPrice() {
        return Inputter.inputString("Input new CD price: ", true);
    }
 
    @Override
    public String toString(CDModel model) {
        StringBuilder sb = new StringBuilder();
        if(model == null)
            return sb.toString();
        sb.append("CD: {\n");
        sb.append("\tID: ").append(model.getID()).append("\n");
        sb.append("\tTitle: ").append(model.getTitle()).append("\n");
        sb.append("\tCollection: ").append(model.getCollection()).append("\n");
        sb.append("\tType: ").append(model.getType()).append("\n");
        sb.append("\tPrice: ").append(model.getPrice()).append("\n");
        sb.append("};");
        
        return sb.toString();
    }
    
    public String getTypes() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(CDModel.CDType i: CDModel.CDType.values()) {
            sb.append(i.getDescription()).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public String getCollections() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(CDModel.CDCollection i: CDModel.CDCollection.values()) {
            sb.append(i.getDescription()).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    @Override
    public String getModelName() {
        return CDModel.class.getSimpleName().substring(0, CDModel.class.getSimpleName().length() - 5);
    }
}

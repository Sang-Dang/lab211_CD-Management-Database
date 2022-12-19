package databasecontrol;

import databasecontrol.factory.AbstractDatabaseControl;
import java.util.ArrayList;
import java.util.List;
import model.CDModel;

/**
 *
 * @author User
 */
public class CDDatabaseControl extends AbstractDatabaseControl<CDModel> {
    
    private final int NUM_ATTRIBUTES = 5;

    @Override
    public String getFilepath() {
        return PROJECT_DATA_FOLDER + CDModel.class.getSimpleName() + EXTENSION;
    }
    
    @Override
    public String toSaveString(CDModel model) {
        if(model == null) 
            return null;
        
        StringBuilder sb = new StringBuilder();
        sb.append(model.getID()).append(SEP);
        sb.append(model.getTitle()).append(SEP);
        sb.append(model.getCollection()).append(SEP);
        sb.append(model.getType()).append(SEP);
        sb.append(model.getPrice());
        return sb.toString();
    }

    @Override
    public List<String> toSaveString(List<CDModel> model) {
        if(model == null)
            return null;
        List<String> result = new ArrayList<>();
        for(CDModel i: model) {
            String temp = toSaveString(i);
            if(temp != null) 
                result.add(temp);
        }
        return result;
    }

    @Override
    public int getNumAttributes() {
        return NUM_ATTRIBUTES;
    }
}

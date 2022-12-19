package service;

import databasecontrol.CDDatabaseControl;
import exceptions.ModelException;
import java.util.List;
import model.CDModel;
import service.factory.AbstractService;
import tools.Singleton;

/**
 * Store model-specific low-level methods here including load and parseLine function.
 * @author User
 */
public class CDService extends AbstractService<CDModel,CDDatabaseControl> {
    
    private CDService() {
        database = Singleton.getInstance(CDDatabaseControl.class);
    }

    @Override
    public int load() {
        int count = 0;
        List<String> lines = database.readLinesFromFile();
        for(String i: lines) {
            String[] parts = i.split(",");
            if(parts.length != database.getNumAttributes())
                return 0;
            int index = -1;
            String ID = parts[++index];
            String title = parts[++index];
            String collection = parts[++index];
            String type = parts[++index];
            double price = Double.parseDouble(parts[++index]);
            
            CDModel creation;
            try {
                creation = new CDModel(ID,title,collection,type,price);
                if(add(creation)) count++;
            } catch(ModelException e) {
                System.out.println(e);
            }
        }
        return count;
    }

    @Override
    public boolean save() {
        database.saveLinesToFile(database.toSaveString(toList()));
        changed = false;
        return true;
    }
}

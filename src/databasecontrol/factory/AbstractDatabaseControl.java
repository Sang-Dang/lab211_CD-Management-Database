package databasecontrol.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.factory.ModelInterface;

/**
 * 
 * @author User
 * @param <ModelClass> Model to use
 */
public abstract class AbstractDatabaseControl<ModelClass extends ModelInterface> implements DatabaseControlInterface<ModelClass> {
    public final String SEP = ",";
    
    protected final String EXTENSION = ".csv";
    protected final String PROJECT_DATA_FOLDER = "projectdata\\";
    
    public abstract String getFilepath();
    public abstract String toSaveString(ModelClass model);
    public abstract List<String> toSaveString(List<ModelClass> model);
    public abstract int getNumAttributes();
    
    @Override
    public List<String> readLinesFromFile() {
        List<String> returnValue = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(getFilepath()));
            String s = "";
            while((s = input.readLine()) != null) {
                returnValue.add(s);
            }
            input.close();
        } catch(IOException e) {
            System.out.println(e);
            return null;
        }
        return returnValue;
    }
    
    @Override
    public boolean saveLinesToFile(List<String> lines) {
        try {
            PrintWriter output = new PrintWriter(getFilepath());
            lines.forEach((i) -> {
                output.println(i);
            });
            output.close();
        } catch(IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}

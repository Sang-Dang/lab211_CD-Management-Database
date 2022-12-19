package databasecontrol.factory;

import java.util.List;
import model.factory.ModelInterface;

/**
 *
 * @author User
 * @param <ModelClass>
 */
public interface DatabaseControlInterface<ModelClass extends ModelInterface> {
    public List<String> readLinesFromFile();
    public boolean saveLinesToFile(List<String> lines);
}

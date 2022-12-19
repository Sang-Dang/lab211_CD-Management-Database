package view.factory;

import java.util.List;
import model.factory.ModelInterface;

/**
 *
 * @author User
 * @param <ModelClass>
 */
public interface ViewInterface<ModelClass extends ModelInterface> {
    public void printModel(ModelClass model);
    public void printModel(List<ModelClass> model);
    public String inputID();
    public String inputTitle();
}

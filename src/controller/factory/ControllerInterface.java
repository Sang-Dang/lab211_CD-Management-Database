package controller.factory;

import exceptions.ModelException;
import model.factory.ModelInterface;

/**
 *
 * @author User
 * @param <ModelClass>
 */
public interface ControllerInterface<ModelClass extends ModelInterface> {
    public ModelClass addModel();
    public int searchByTitle();
    public int showAll();
    public void showSorted();
    public ModelClass updateModel();
    public ModelClass deleteModel();
    public int loadModels();
    public boolean saveModels();
    public ModelClass createModel() throws ModelException;
    public String[] getOptionList();
    public void viewModelDetails(ModelClass model);
    public void viewModelDetails(Object model);
    public boolean getChanged();
}

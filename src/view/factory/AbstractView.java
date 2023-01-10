package view.factory;

import java.util.List;
import model.factory.ModelInterface;
import tools.Inputter;

/**
 *
 * @author User
 * @param <ModelClass>
 */
public abstract class AbstractView<ModelClass extends ModelInterface> implements ViewInterface<ModelClass> {
    
    @Override
    public void printModel(ModelClass model) {
        if(model != null)
            System.out.println(toString(model));
    }
    
    @Override
    public void printModel(List<ModelClass> model) {
        if(model != null) {
            model.stream().forEach(p -> {printModel(p);});
        }
    }
    
    @Override
    public String inputID() {
        return Inputter.inputString("Input ID of the " + getModelName() + ": ", false);
    }
    
    @Override
    public String inputTitle() {
        return Inputter.inputString("Input " + getModelName() + " title: ", false);
    }
    
    public abstract String toString(ModelClass model);
    public abstract String getModelName();
}

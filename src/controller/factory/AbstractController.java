package controller.factory;

import exceptions.ModelException;
import model.factory.ModelInterface;
import service.factory.ServiceInterface;
import view.factory.ViewInterface;

/**
 * Abstract Controller: Interface to the user (methods in main will ONLY use
 * this class). This controller mainly operates on the service class and
 * contains HIGH-LEVEL implementations of service operations (including input
 * and output of data). NOTE: DEAL WITH EXCEPTIONS HERE, avoid parameters in methods. 
 *
 * @author User
 * @param <ViewClass> used to print stuff
 * @param <ModelClass> literally the item being used...
 * @param <ServiceClass> the service being used.
 */
public abstract class AbstractController<ViewClass extends ViewInterface<ModelClass>, ModelClass extends ModelInterface, ServiceClass extends ServiceInterface> implements ControllerInterface<ModelClass> {

    protected ViewClass view;
    protected ServiceClass services;

    @Override
    public ModelClass addModel() {
        try {
            ModelClass creation = createModel();
            services.add(creation);
            return creation;
        } catch (ModelException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int loadModels() {
        return services.load();
    }

    @Override
    public boolean saveModels() {
        return services.save();
    }

    @Override
    public int showAll() {
        view.printModel(services.toList());
        return services.toList().size();
    }

    @Override
    public void viewModelDetails(ModelClass model) {
        view.printModel(model);
    }
    
    @Override
    public void viewModelDetails(Object model) {
        viewModelDetails((ModelClass) model);
    }

    @Override
    public boolean getChanged() {
        return services.getChanged();
    }
    
    @Override
    public ModelClass deleteModel() {
        String ID = view.inputID();
        ModelInterface found = services.searchKey(ID);
        if (found != null) {
            if (services.remove(found)) {
                return (ModelClass) found;
            }
        }
        return null;
    }
}

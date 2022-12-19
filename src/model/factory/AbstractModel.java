package model.factory;

import exceptions.ModelException;

/**
 * All models must have ID and title
 * @author User
 */
public abstract class AbstractModel implements ModelInterface {
    private String ID;
    private String title;
    
    public AbstractModel() {
        this.ID = "";
        this.title = "";
    }
    
    public AbstractModel(String ID, String title) throws ModelException {
        setID(ID);
        setTitle(title);
    }
    
    @Override
    public String getID() {
        return ID;
    }
    
    public final void setID(String ID) throws ModelException {
        if(validateID(ID)) {
            this.ID = ID;
        } else {
            throw new ModelException("ID unvalidated");
        }
    }
    
    @Override
    public String getTitle() {
        return title;
    }
    
    public final void setTitle(String title) throws ModelException {
        if(validateTitle(title)) {
            this.title = title;
        } else {
            throw new ModelException("Title unvalidated");
        }
    }
    
    protected abstract boolean validateID(String id);
    protected abstract boolean validateTitle(String title);
}

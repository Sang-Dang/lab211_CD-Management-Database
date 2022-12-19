package service.factory;

import exceptions.ModelException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import model.factory.ModelInterface;

/**
 * General interface for all services. AbstractService.java is an ArrayList implementation. 
 * @author User
 * @param <ModelClass>
 */
public interface ServiceInterface<ModelClass extends ModelInterface> {
    public boolean add(ModelClass model) throws ModelException, IllegalArgumentException;
    public boolean remove(ModelClass model);
    public boolean update(ModelClass updateData);
    public List<ModelClass> search(Predicate<ModelClass> condition);
    public int load();
    public boolean save();
    public List<ModelClass> toList();
    public ModelClass searchKey(String key);
    public List<Integer> searchIndex(Predicate<ModelClass> condition);
    public List<ModelClass> generateSortedList(Comparator<ModelClass> compare);
    public boolean getChanged();
}
    

package service.factory;

import databasecontrol.factory.DatabaseControlInterface;
import exceptions.ModelException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import model.factory.ModelInterface;

/**
 * Abstract Service: This class contains the storage medium for the model and
 * has all LOW-LEVEL operations for this medium (basic add, remove, update
 * implementations). Use parameterized methods.
 *
 * @author User
 * @param <ModelClass>
 * @param <Database>
 */
public abstract class AbstractService<ModelClass extends ModelInterface, Database extends DatabaseControlInterface> implements ServiceInterface<ModelClass> {

    protected ArrayList<ModelClass> list;
    protected boolean changed;

    protected Database database;

    public AbstractService() {
        list = new ArrayList<>();
        changed = false;
    }

    @Override
    public boolean add(ModelClass model) throws ModelException, IllegalArgumentException {
        if (model != null) {
            // test for duplicate keys
            if (search(p -> p.getID().equals(model.getID())).isEmpty()) {
                list.add(model);
                changed = true;
                return true;
            } else {
                throw new ModelException("ID already exists.");
            }
        }
        throw new IllegalArgumentException("Cannot add null model to list");
    }

    @Override
    public boolean remove(ModelClass model) {
        if (model != null) {
            if (list.remove(model)) {
                changed = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ModelClass> search(Predicate<ModelClass> condition) {
        List<ModelClass> findings = list.stream().filter(condition).collect(Collectors.toList());
        return findings;
    }

    @Override
    public boolean update(ModelClass updateData) {
        List<Integer> temp;
        int changeIndex = (temp = searchIndex(p -> p.getID().equals(updateData.getID()))).isEmpty() ? -1 : temp.get(0);
        if (changeIndex == -1) {
            return false;
        }
        list.set(changeIndex, updateData);
        changed = true;
        return true;
    }

    @Override
    public List<Integer> searchIndex(Predicate<ModelClass> condition) {
        List<Integer> findings = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (condition.test(list.get(i))) {
                findings.add(i);
            }
        }
        return findings;
    }

    @Override
    public ModelClass searchKey(String key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID().equals(key)) {
                return list.get(i);
            }
        }
        return null;
    }

    @Override
    public List<ModelClass> toList() {
        return list.stream().collect(Collectors.toList());
    }

    @Override
    public List<ModelClass> generateSortedList(Comparator<ModelClass> compare) {
        List<ModelClass> sorted = new ArrayList<>();
        sorted.addAll(list);
        Collections.sort(sorted, compare);
        return sorted;
    }

    @Override
    public boolean getChanged() {
        return changed;
    }
}

package controller;

import controller.factory.AbstractController;
import exceptions.ModelException;
import java.util.List;
import model.CDModel;
import service.CDService;
import tools.Singleton;
import view.CDView;

/**
 * Contains special methods. Must have createModel(), updateModel()
 * 
 * @author User
 */
public class CDController extends AbstractController<CDView, CDModel, CDService> {
    
    private CDController() {
        view = Singleton.getInstance(CDView.class);
        services = Singleton.getInstance(CDService.class);
    }

    @Override
    public CDModel createModel() throws ModelException {
        String ID = view.inputID();
        String title = view.inputTitle();
        String collection = view.inputCollection();
        String type = view.inputType();
        double price = view.inputPrice();
        CDModel creation = new CDModel(ID, title, collection, type, price);
        return creation;
    }

    @Override
    public void showSorted() {
        view.printModel(services.generateSortedList((CDModel a, CDModel b) -> a.getTitle().compareTo(b.getTitle())));
    }
    
    @Override
    public int searchByTitle() {
        String title = view.inputTitle();
        List<CDModel> found;
        view.printModel(found = services.search(p -> p.getTitle().contains(title)));
        return found.size();
    }

    @Override
    public CDModel updateModel() {
        String ID = view.inputRawID();
        List<CDModel> findings;
        if(!((findings = services.search(p -> p.getID().equals(ID))).isEmpty())) {
            CDModel old = findings.get(0);
            String temp;
            
            String title = (temp = view.inputRawTitle()).isEmpty() ? old.getTitle() : temp;
            String collection = (temp = view.inputRawCollection()).isEmpty() ? old.getCollection() : temp;
            String type = (temp = view.inputRawType()).isEmpty() ? old.getType() : temp;
            double price = (temp = view.inputRawPrice()).isEmpty() ? old.getPrice() : Double.parseDouble(temp);
            CDModel creation;
            try {
                creation = new CDModel(ID,title,collection,type,price);
            } catch(ModelException e) {
                System.out.println(e);
                return null;
            }
            
            if(services.update(creation))
                return creation;
        }
        return null;
    }

    @Override
    public String[] getOptionList() {
        return new String[] {
            "Add CD",  
            "Search CD by Title",
            "Display catalog",
            "Display all alphabetically",
            "Update CD",
            "Remove CD",
            "Save all"
        };
    }
}

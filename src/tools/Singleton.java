package tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class Singleton {
    private Singleton() {}
    
    public static Map<Class,Object> allInstances = new HashMap<>();
    
    synchronized public static <T> T getInstance(Class<T> myClass) {
        if(!Singleton.allInstances.containsKey(myClass)) {
            try {
            Constructor run = myClass.getDeclaredConstructor();
            run.setAccessible(true);
            
            Singleton.allInstances.put(myClass, run.newInstance());
            } catch(IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                System.out.println(e);
                return null;
            }
        }
        return (T) Singleton.allInstances.get(myClass);
    }
}

package by.kalilaska.sphere.storage;

import by.kalilaska.sphere.entity.impl.Sphere;
import by.kalilaska.sphere.entity.impl.SphereCharacteristics;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lovcov on 25.05.2017.
 */
public class ResultStorage {
    private final static Logger LOGGER = LogManager.getLogger(ResultStorage.class);
    private static ResultStorage instance;

    private Map<Sphere, SphereCharacteristics> results;

    private ResultStorage(){
        if(instance != null){
            throw new RuntimeException("You can not make another class instance");
        }
        results = new HashMap<>();
    }

    public static ResultStorage getInstance(){
        if(instance == null){
            synchronized (ResultStorage.class){
                if(instance == null){
                    instance = new ResultStorage();
                }
            }
        }
        return instance;
    }

    public void addOneCouple(Sphere sphere, SphereCharacteristics sphereCharacteristics){
        if(sphere != null && sphereCharacteristics != null){
            results.put(sphere, sphereCharacteristics);
        }
    }

    public void addSeveralCouples(Map<Sphere, SphereCharacteristics> map){
        if(map != null && !map.isEmpty()){
            results.putAll(map);
        }
    }

    public boolean changeOneCouple(int index, Sphere sphere){
        if(index >= 0){
            int count = 0;
            Sphere sphereForChange = null;
            Iterator<Sphere> iterator = results.keySet().iterator();

            while (iterator.hasNext()){
                if(count == index){
                    sphereForChange = iterator.next();
                    break;
                }
                iterator.next();
                count++;
            }

            if(sphereForChange != null){

                LOGGER.log(Level.INFO, "sphere before change: \n" + sphereForChange);

                SphereCharacteristics characteristicsBeforeChange =results.get(sphereForChange).clone();

                sphereForChange.changeCoordinatesAndRadius(sphere.getCentre(), sphere.getRadius());
                SphereCharacteristics characteristicsAfterChange = results.get(sphereForChange);
                LOGGER.log(Level.INFO, "sphere after change: \n" + sphereForChange);

                if(characteristicsBeforeChange != null){
                    return !characteristicsBeforeChange.equals(characteristicsAfterChange);
                }
            }
        }
        return false;
    }

    public int getDataSize(){
        return results.size();
    }

    public boolean removeOneCouple(int index){
        if(index < 0){
            return false;
        }
        int count = 0;
        Iterator<Sphere> iterator = results.keySet().iterator();

        while (iterator.hasNext()){
            iterator.next();
            if(count == index){
                iterator.remove();
                return true;
            }
            count++;
        }
        return false;
    }

    public boolean removeAllData(){
        int size = results.size();
        results.clear();
        return size != results.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Sphere> keyIterator = results.keySet().iterator();
        Sphere sphere;
        while (keyIterator.hasNext()){
            sphere = keyIterator.next();
            sb.append("\n");
            sb.append(sphere);
            sb.append("\n");
            sb.append(results.get(sphere));
        }
        return sb.toString();
    }

}

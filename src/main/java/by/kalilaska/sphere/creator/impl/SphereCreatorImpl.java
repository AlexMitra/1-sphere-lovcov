package by.kalilaska.sphere.creator.impl;

import by.kalilaska.sphere.creator.SphereCreator;
import by.kalilaska.sphere.entity.CentrePoint;
import by.kalilaska.sphere.entity.impl.Sphere;
import by.kalilaska.sphere.entity.impl.SphereCharacteristics;
import by.kalilaska.sphere.exception.CentreCoordinateNotValidException;
import by.kalilaska.sphere.exception.DataAfterValidationNotExistException;
import by.kalilaska.sphere.exception.RadiusNotValidException;
import by.kalilaska.sphere.factory.CentrePointFactory;
import by.kalilaska.sphere.factory.SphereCharacteristicsFactory;
import by.kalilaska.sphere.factory.SphereFactory;
import by.kalilaska.sphere.parser.OneRowParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by lovcov on 29.05.2017.
 */
public class SphereCreatorImpl implements SphereCreator{
    private final static Logger LOGGER = LogManager.getLogger(SphereCreatorImpl.class);

    @Override
    public Map<Sphere, SphereCharacteristics> createSphereAndCharacteristics(List<String> fileContentList,
                                                                             CentrePointFactory centrePointFactory, SphereFactory sphereFactory,
                                                                             SphereCharacteristicsFactory sphereCharacteristicsFactory)
            throws DataAfterValidationNotExistException{
        if(fileContentList == null || fileContentList.isEmpty()){
            throw new DataAfterValidationNotExistException("After validation data not exist");
        }
        HashMap<Sphere, SphereCharacteristics> sphereMap = new HashMap<>();
        List<String> rowList = new ArrayList<>();
        String strX;
        String strY;
        String strZ;
        String strRadius;
        CentrePoint centrePoint;
        Sphere sphere;
        SphereCharacteristics sphereCharacteristics;
        for (int i = 0; i < fileContentList.size(); i++) {
            OneRowParser.parsStringToList(fileContentList.get(i), rowList);

            strX = rowList.get(0);
            strY = rowList.get(1);
            strZ = rowList.get(2);
            strRadius = rowList.get(3);

            try {
                centrePoint = createCentrePoint(centrePointFactory, strX, strY, strZ);
                sphere = createSphere(sphereFactory, centrePoint, strRadius);
                if(sphere != null){
                    sphereCharacteristics = sphereCharacteristicsFactory.createSphereCharacteristics(sphere);

                    sphere.notifyObservers();
                    sphereMap.put(sphere, sphereCharacteristics);
                }
            } catch (CentreCoordinateNotValidException e) {
                LOGGER.log(Level.WARN, "coordinates not valid: " + strX + ", " + strY + ", " + strZ);
            } catch (RadiusNotValidException e) {
                LOGGER.log(Level.WARN, "radius not valid, coordinates: " + strX + ", " + strY + ", " + strZ + ", radius: " + strRadius);
            }
            rowList.clear();
        }
        return sphereMap;
    }

    @Override
    public CentrePoint createCentrePoint(CentrePointFactory centrePointFactory, String strX, String strY, String strZ) throws CentreCoordinateNotValidException {
        Double x;
        Double y;
        Double z;

        try{
            x = Double.parseDouble(strX);
            y = Double.parseDouble(strY);
            z = Double.parseDouble(strZ);
        }catch (NumberFormatException e){
            throw new CentreCoordinateNotValidException("some coordinates not valid: " + strX + ", " + strY + ", " + strZ, e);
        }catch (NullPointerException e){
            throw new CentreCoordinateNotValidException("some coordinates not valid: " + strX + ", " + strY + ", " + strZ, e);
        }

        CentrePoint centrePoint = centrePointFactory.createPoint(x, y, z);
        return centrePoint;
    }

    @Override
    public Sphere createSphere(SphereFactory sphereFactory, CentrePoint centrePoint, String strRadius)
            throws RadiusNotValidException {
        Double radius;

        try{
            radius = Double.parseDouble(strRadius);
        }catch (NumberFormatException e){
            throw new RadiusNotValidException("radius not valid: " + strRadius);
        }catch (NullPointerException e){
            throw new RadiusNotValidException("radius not valid: " + strRadius);
        }

        if(centrePoint != null && radius > 0){
            Sphere sphere = sphereFactory.createSphere(centrePoint, radius);
            return sphere;
        }

        return null;
    }

}

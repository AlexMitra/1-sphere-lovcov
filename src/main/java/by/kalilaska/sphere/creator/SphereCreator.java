package by.kalilaska.sphere.creator;

import by.kalilaska.sphere.entity.CentrePoint;
import by.kalilaska.sphere.entity.impl.Sphere;
import by.kalilaska.sphere.entity.impl.SphereCharacteristics;
import by.kalilaska.sphere.exception.CentreCoordinateNotValidException;
import by.kalilaska.sphere.exception.DataAfterValidationNotExistException;
import by.kalilaska.sphere.exception.RadiusNotValidException;
import by.kalilaska.sphere.factory.CentrePointFactory;
import by.kalilaska.sphere.factory.SphereCharacteristicsFactory;
import by.kalilaska.sphere.factory.SphereFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by lovcov on 29.05.2017.
 */
public interface SphereCreator {
    Map<Sphere, SphereCharacteristics> createSphereAndCharacteristics(List<String> fileContentList,
                                       CentrePointFactory centrePointFactory, SphereFactory sphereFactory,
                                       SphereCharacteristicsFactory sphereCharacteristicsFactory)
            throws DataAfterValidationNotExistException;

    CentrePoint createCentrePoint(CentrePointFactory centrePointFactory, String strX, String strY,
                                  String strZ) throws CentreCoordinateNotValidException;

    Sphere createSphere(SphereFactory sphereFactory, CentrePoint centrePoint, String strRadius)
            throws RadiusNotValidException;
}

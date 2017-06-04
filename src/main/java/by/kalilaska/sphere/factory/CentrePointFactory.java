package by.kalilaska.sphere.factory;

import by.kalilaska.sphere.entity.CentrePoint;

/**
 * Created by lovcov on 25.05.2017.
 */
public interface CentrePointFactory {

    CentrePoint createPoint(double x, double y, double z);
}

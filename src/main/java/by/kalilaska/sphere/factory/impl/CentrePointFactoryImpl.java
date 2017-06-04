package by.kalilaska.sphere.factory.impl;
import by.kalilaska.sphere.entity.CentrePoint;
import by.kalilaska.sphere.factory.CentrePointFactory;

/**
 * Created by lovcov on 25.05.2017.
 */
public class CentrePointFactoryImpl implements CentrePointFactory {
    @Override
    public CentrePoint createPoint(double x, double y, double z) {
        return new CentrePoint(x, y, z);
    }
}

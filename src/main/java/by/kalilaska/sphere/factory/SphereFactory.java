package by.kalilaska.sphere.factory;

import by.kalilaska.sphere.entity.CentrePoint;
import by.kalilaska.sphere.entity.impl.Sphere;

/**
 * Created by lovcov on 25.05.2017.
 */
public interface SphereFactory {

    Sphere createSphere(CentrePoint centre, double radius);
}

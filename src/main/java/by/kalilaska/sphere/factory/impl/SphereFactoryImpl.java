package by.kalilaska.sphere.factory.impl;

import by.kalilaska.sphere.entity.CentrePoint;
import by.kalilaska.sphere.entity.impl.Sphere;
import by.kalilaska.sphere.factory.SphereFactory;

/**
 * Created by lovcov on 25.05.2017.
 */
public class SphereFactoryImpl implements SphereFactory{
    @Override
    public Sphere createSphere(CentrePoint centre, double radius) {
        return new Sphere(centre, radius);
    }

}

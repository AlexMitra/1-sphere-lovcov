package by.kalilaska.sphere.factory.impl;

import by.kalilaska.sphere.entity.ObservableSubject;
import by.kalilaska.sphere.entity.impl.SphereCharacteristics;
import by.kalilaska.sphere.factory.SphereCharacteristicsFactory;

/**
 * Created by lovcov on 25.05.2017.
 */
public class SphereCharacteristicsFactoryImpl implements SphereCharacteristicsFactory{
    @Override
    public SphereCharacteristics createSphereCharacteristics(ObservableSubject observableSubject) {
        return new SphereCharacteristics(observableSubject);
    }
}

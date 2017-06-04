package by.kalilaska.sphere.factory;

import by.kalilaska.sphere.entity.ObservableSubject;
import by.kalilaska.sphere.entity.impl.SphereCharacteristics;

/**
 * Created by lovcov on 25.05.2017.
 */
public interface SphereCharacteristicsFactory {
    SphereCharacteristics createSphereCharacteristics(ObservableSubject observableSubject);
}

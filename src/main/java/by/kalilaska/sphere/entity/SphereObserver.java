package by.kalilaska.sphere.entity;

/**
 * Created by lovcov on 25.05.2017.
 */
public interface SphereObserver {
    void updateSphereCharacteristics(long sphereId, double sphereSurfaceArea, double sphereVolume,
                                     String intersectionXOY, String intersectionXOZ, String intersectionYOZ);
}

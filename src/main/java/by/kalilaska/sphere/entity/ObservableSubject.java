package by.kalilaska.sphere.entity;

/**
 * Created by lovcov on 25.05.2017.
 */
public interface ObservableSubject {
    void registerObserver(SphereObserver sphereObserver);
    void removeObserver(SphereObserver sphereObserver);
    void notifyObservers();
}

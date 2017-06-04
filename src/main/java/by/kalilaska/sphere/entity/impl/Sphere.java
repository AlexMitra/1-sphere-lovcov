package by.kalilaska.sphere.entity.impl;

import by.kalilaska.sphere.calculator.SphereCharacteristicsCalculator;
import by.kalilaska.sphere.entity.CentrePoint;
import by.kalilaska.sphere.entity.IdGenerator;
import by.kalilaska.sphere.entity.ObservableSubject;
import by.kalilaska.sphere.entity.SphereObserver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by lovcov on 25.05.2017.
 */
public class Sphere implements ObservableSubject, Cloneable {
    private final static Logger LOGGER = LogManager.getLogger(Sphere.class);

    private final long sphereId = IdGenerator.generateSphereId();

    private ArrayList<SphereObserver> sphereObservers;
    private CentrePoint centre;
    private double radius;

    public Sphere() {
        sphereObservers = new ArrayList<>();
    }

    public Sphere(CentrePoint centre, double radius) {
        sphereObservers = new ArrayList<>();
        this.centre = centre;
        this.radius = radius;
    }

    public CentrePoint getCentre() {
        return centre;
    }

    public double getRadius() {
        return radius;
    }

    public long getSphereId() {
        return sphereId;
    }

    public void setCentre(CentrePoint centre) {
        this.centre = centre;
        notifyObservers();
    }

    public void setRadius(double radius) {
        this.radius = radius;
        notifyObservers();
    }

    public void changeCoordinatesAndRadius(CentrePoint centre, double radius){
        this.centre = centre;
        this.radius = radius;
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Sphere" + sphereId + " {" +
                "centre=(" + centre.getX() + ", " + centre.getY() + ", " + centre.getZ() + ")" +
                ", radius=" + radius +
                '}';
    }

    @Override
    public void registerObserver(SphereObserver sphereObserver) {
        if(!sphereObservers.contains(sphereObserver)){
            sphereObservers.add(sphereObserver);
        }
    }

    @Override
    public void removeObserver(SphereObserver sphereObserver) {
        if(sphereObservers.contains(sphereObserver)){
            sphereObservers.remove(sphereObserver);
        }
    }

    @Override
    public void notifyObservers() {
        if(sphereObservers!= null || !sphereObservers.isEmpty()){
            for (int i = 0; i < sphereObservers.size(); i++) {
                if(sphereObservers.get(i).getClass() == SphereCharacteristics.class){
                    SphereCharacteristicsCalculator.calculate(this, (SphereCharacteristics)sphereObservers.get(i));
                }
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sphere sphere = (Sphere) o;

        if (sphereId != sphere.sphereId) return false;
        if (Double.compare(sphere.radius, radius) != 0) return false;
        return centre.equals(sphere.centre);
    }

    @Override
    public int hashCode() {
        return (int) (sphereId ^ (sphereId >>> 32));
    }

    @Override
    public Sphere clone(){
        Sphere cloned = null;
        try {
            cloned = (Sphere) super.clone();
            cloned.centre = centre.clone();
            cloned.sphereObservers.clear();
            SphereCharacteristics sphereObserver;
            if(!sphereObservers.isEmpty()){
                for (int i = 0; i < sphereObservers.size(); i++) {
                    sphereObserver = (SphereCharacteristics)sphereObservers.get(i);
                    cloned.registerObserver(sphereObserver.clone());
                }
                cloned.notifyObservers();
            }

        } catch(CloneNotSupportedException e) {
            LOGGER.log(Level.ERROR, "Can not create Sphere clone");
        }
        return cloned;
    }
}

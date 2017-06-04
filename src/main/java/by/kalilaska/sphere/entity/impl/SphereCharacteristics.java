package by.kalilaska.sphere.entity.impl;

import by.kalilaska.sphere.entity.ObservableSubject;
import by.kalilaska.sphere.entity.SphereObserver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by lovcov on 25.05.2017.
 */
public class SphereCharacteristics implements SphereObserver, Cloneable {
    private final static Logger LOGGER = LogManager.getLogger(SphereCharacteristics.class);

    private long sphereId;
    private double sphereSurfaceArea;
    private double sphereVolume;
    private String intersectionXOY;
    private String intersectionXOZ;
    private String intersectionYOZ;

    public SphereCharacteristics() {
    }

    public SphereCharacteristics(ObservableSubject observableSubject) {
        observableSubject.registerObserver(this);
    }

    @Override
    public void updateSphereCharacteristics(long sphereId, double sphereSurfaceArea, double sphereVolume,
                                            String intersectionXOY, String intersectionXOZ, String intersectionYOZ) {
        this.sphereId = sphereId;
        this.sphereSurfaceArea = sphereSurfaceArea;
        this.sphereVolume = sphereVolume;
        this.intersectionXOY = intersectionXOY;
        this.intersectionXOZ = intersectionXOZ;
        this.intersectionYOZ = intersectionYOZ;
    }

    public long getSphereId() {
        return sphereId;
    }

    public double getSphereSurfaceArea() {
        return sphereSurfaceArea;
    }

    public double getSphereVolume() {
        return sphereVolume;
    }

    public String getIntersectionXOY() {
        return intersectionXOY;
    }

    public String getIntersectionXOZ() {
        return intersectionXOZ;
    }

    public String getIntersectionYOZ() {
        return intersectionYOZ;
    }

    @Override
    public String toString() {
        return "SphereCharacteristics" + sphereId + "{" +
                "sphereSurfaceArea=" + String.format("%.3f", sphereSurfaceArea) +
                ", sphereVolume=" + String.format("%.3f", sphereVolume) +
                ",\n intersectionXOY='" + intersectionXOY + '\'' +
                ",\n intersectionXOZ='" + intersectionXOZ + '\'' +
                ",\n intersectionYOZ='" + intersectionYOZ + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SphereCharacteristics that = (SphereCharacteristics) o;

        if (Double.compare(that.sphereSurfaceArea, sphereSurfaceArea) != 0) return false;
        if (Double.compare(that.sphereVolume, sphereVolume) != 0) return false;
        if (intersectionXOY != null ? !intersectionXOY.equals(that.intersectionXOY) : that.intersectionXOY != null)
            return false;
        if (intersectionXOZ != null ? !intersectionXOZ.equals(that.intersectionXOZ) : that.intersectionXOZ != null)
            return false;
        return intersectionYOZ != null ? intersectionYOZ.equals(that.intersectionYOZ) : that.intersectionYOZ == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(sphereSurfaceArea);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sphereVolume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (intersectionXOY != null ? intersectionXOY.hashCode() : 0);
        result = 31 * result + (intersectionXOZ != null ? intersectionXOZ.hashCode() : 0);
        result = 31 * result + (intersectionYOZ != null ? intersectionYOZ.hashCode() : 0);
        return result;
    }

    @Override
    public SphereCharacteristics clone() {
        SphereCharacteristics cloned = null;
        try {
            cloned = (SphereCharacteristics) super.clone();
        } catch(CloneNotSupportedException e) {
            LOGGER.log(Level.ERROR, "Can not create SphereCharacteristics clone");
        }
        return cloned;
    }
}

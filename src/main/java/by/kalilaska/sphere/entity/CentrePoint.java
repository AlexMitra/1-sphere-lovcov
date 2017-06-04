package by.kalilaska.sphere.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by lovcov on 25.05.2017.
 */
public class CentrePoint implements Cloneable{
    private final static Logger LOGGER = LogManager.getLogger(CentrePoint.class);

    private double x;
    private double y;
    private double z;

    public CentrePoint() {
        x = 0.0;
        y = 0.0;
        z = 0.0;
    }

    public CentrePoint(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "CentrePoint{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CentrePoint that = (CentrePoint) o;

        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        return Double.compare(that.z, z) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public CentrePoint clone() throws CloneNotSupportedException {
        CentrePoint cloned = null;
        try {
            cloned = (CentrePoint) super.clone();
        } catch(CloneNotSupportedException e) {
            LOGGER.log(Level.ERROR, "Can not create CentrePoint clone");
        }
        return cloned;
    }
}

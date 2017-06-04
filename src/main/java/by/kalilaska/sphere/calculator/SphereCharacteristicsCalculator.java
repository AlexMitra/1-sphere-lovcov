package by.kalilaska.sphere.calculator;

import by.kalilaska.sphere.entity.impl.Sphere;
import by.kalilaska.sphere.entity.impl.SphereCharacteristics;

/**
 * Created by lovcov on 29.05.2017.
 */
public class SphereCharacteristicsCalculator {
    private final static int SPHERE_SURFACE_AREA_FACTOR = 4;
    private final static double SPHERE_VOLUME_FACTOR = 4.0d/3.0d;
    private final static double SPHERE_SEGMENT_VOLUME_FACTOR = 1.0d/3.0d;

    public final static String DEFAULT_XOY_INTERSECTION = "this sphere do not intersect with XOY";
    public final static String DEFAULT_XOZ_INTERSECTION = "this sphere do not intersect with XOZ";
    public final static String DEFAULT_YOZ_INTERSECTION = "this sphere do not intersect with YOZ";

    public final static String XOY_INTERSECTION = "this sphere intersect with XOY, values ratio: ";
    public final static String XOZ_INTERSECTION = "this sphere intersect with XOZ, values ratio: ";
    public final static String YOZ_INTERSECTION = "this sphere intersect with YOZ, values ratio: ";

    public final static String XOY_TOUCHING = "this sphere touch XOY";
    public final static String XOZ_TOUCHING = "this sphere touch XOZ";
    public final static String YOZ_TOUCHING = "this sphere touch YOZ";

    public static void calculate(Sphere sphere, SphereCharacteristics sphereCharacteristics){

        long sphereId = sphere.getSphereId();
        double sphereSurfaceArea = calculateSphereSurfaceArea(sphere);
        double sphereVolume = calculateSphereVolume(sphere);
        String intersectionXOY = intersectionWithXOY(sphere);
        String intersectionXOZ = intersectionWithXOZ(sphere);
        String intersectionYOZ = intersectionWithYOZ(sphere);

        sphereCharacteristics.updateSphereCharacteristics(sphereId, sphereSurfaceArea, sphereVolume, intersectionXOY,
                intersectionXOZ, intersectionYOZ);
    }

    public static double calculateSphereSurfaceArea(Sphere sphere){
        double radius = sphere.getRadius();
        return SPHERE_SURFACE_AREA_FACTOR*Math.PI*Math.pow(radius, 2);
    }

    public static double calculateSphereVolume(Sphere sphere){
        double radius = sphere.getRadius();
        return SPHERE_VOLUME_FACTOR*Math.PI*Math.pow(radius, 3);
    }

    public static String intersectionWithXOY(Sphere sphere){
        double minZ = sphere.getCentre().getZ() - sphere.getRadius();
        double maxZ = sphere.getCentre().getZ() + sphere.getRadius();
        if(minZ*maxZ >0){
            return DEFAULT_XOY_INTERSECTION;
        }else if(minZ*maxZ <0){
            double volumeAbove = volumePartCalculator(maxZ, sphere.getRadius());
            double volumeUnder = volumePartCalculator(minZ, sphere.getRadius());
            return XOY_INTERSECTION + String.format("%.3f", (volumeAbove/volumeUnder));
        }else{
            return XOY_TOUCHING;
        }
    }

    public static String intersectionWithXOZ(Sphere sphere){
        double minY = sphere.getCentre().getY() - sphere.getRadius();
        double maxY = sphere.getCentre().getY() + sphere.getRadius();
        if(minY*maxY >0){
            return DEFAULT_XOZ_INTERSECTION;
        }else if(minY*maxY <0){
            double volumeAbove = volumePartCalculator(maxY, sphere.getRadius());
            double volumeUnder = volumePartCalculator(minY, sphere.getRadius());
            return XOZ_INTERSECTION + String.format("%.3f", (volumeAbove/volumeUnder));
        }else{
            return XOZ_TOUCHING;
        }
    }

    public static String intersectionWithYOZ(Sphere sphere){
        double minX = sphere.getCentre().getX() - sphere.getRadius();
        double maxX = sphere.getCentre().getX() + sphere.getRadius();
        if(minX*maxX >0){
            return DEFAULT_YOZ_INTERSECTION;
        }else if(minX*maxX <0){
            double volumeAbove = volumePartCalculator(maxX, sphere.getRadius());
            double volumeUnder = volumePartCalculator(minX, sphere.getRadius());
            return YOZ_INTERSECTION + String.format("%.3f", (volumeAbove/volumeUnder));
        }else{
            return YOZ_TOUCHING;
        }
    }

    private static double volumePartCalculator(double coordinate, double radius){
        if(coordinate<0){
            coordinate = -1*coordinate;
        }
        double volumePart = Math.PI*Math.pow(coordinate, 2)*(radius - SPHERE_SEGMENT_VOLUME_FACTOR*coordinate);
        return volumePart;
    }
}

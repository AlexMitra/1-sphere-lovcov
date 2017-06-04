package by.kalilaska.sphere.entity;

/**
 * Created by lovcov on 31.05.2017.
 */
public class IdGenerator {
    private static long count = 1000;

    public static long generateSphereId(){
        long id = count++;
        return id;
    }

}

package by.kalilaska.sphere.storage;

import by.kalilaska.sphere.entity.CentrePoint;
import by.kalilaska.sphere.entity.impl.Sphere;
import by.kalilaska.sphere.entity.impl.SphereCharacteristics;
import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * Created by lovcov on 01.06.2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResultStorageTest {

    private static ResultStorage resultStorage;

    @BeforeClass
    public static void initResultKeeper(){
        resultStorage = ResultStorage.getInstance();
    }

    @Test
    public void addNullCouple(){
        long expected = resultStorage.getDataSize();
        resultStorage.addOneCouple(null, null);
        long actual = resultStorage.getDataSize();
        Assert.assertEquals("storage keeper strange work", expected, actual);
    }

    @Test
    public void addOneCouple() {
        CentrePoint centrePoint = new CentrePoint(1, 1, 1);
        Sphere sphere = new Sphere(centrePoint, 1);
        SphereCharacteristics sphereCharacteristics = new SphereCharacteristics(sphere);
        sphere.notifyObservers();

        resultStorage.addOneCouple(sphere, sphereCharacteristics);
        long expected = 1;
        long actual = resultStorage.getDataSize();
        Assert.assertEquals("storage keeper strange work", expected, actual);
    }

    @Test
    public void changeOneCoupleIncorrect() {
        CentrePoint centrePoint = new CentrePoint(3, 3, 3);
        Sphere sphere = new Sphere(centrePoint, 3);
        boolean condition = resultStorage.changeOneCouple(-3, sphere);

        Assert.assertFalse("storage has less data", condition);
    }

    @Test
    public void changeOneCouple() {
        CentrePoint centrePoint = new CentrePoint(3, 3, 3);
        Sphere sphere = new Sphere(centrePoint, 3);
        boolean condition = resultStorage.changeOneCouple(0, sphere);

        Assert.assertTrue("method works incorrect", condition);
    }

    @Test
    public void removeOneCoupleIncorrect(){
        boolean condition = resultStorage.removeOneCouple(-999999);
        Assert.assertFalse("method works incorrect", condition);
    }

    @Test
    public void removeOneCouple(){
        boolean condition = resultStorage.removeOneCouple(0);
        Assert.assertTrue("method works incorrect", condition);
    }

    @AfterClass
    public static void clearResultKeeper(){
        resultStorage.removeAllData();
    }
}
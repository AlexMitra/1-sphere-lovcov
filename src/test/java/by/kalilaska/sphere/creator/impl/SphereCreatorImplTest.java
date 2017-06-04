package by.kalilaska.sphere.creator.impl;

import by.kalilaska.sphere.creator.SphereCreator;
import by.kalilaska.sphere.entity.CentrePoint;
import by.kalilaska.sphere.exception.CentreCoordinateNotValidException;
import by.kalilaska.sphere.exception.DataAfterValidationNotExistException;
import by.kalilaska.sphere.exception.RadiusNotValidException;
import by.kalilaska.sphere.factory.CentrePointFactory;
import by.kalilaska.sphere.factory.SphereCharacteristicsFactory;
import by.kalilaska.sphere.factory.SphereFactory;
import by.kalilaska.sphere.factory.impl.CentrePointFactoryImpl;
import by.kalilaska.sphere.factory.impl.SphereCharacteristicsFactoryImpl;
import by.kalilaska.sphere.factory.impl.SphereFactoryImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lovcov on 31.05.2017.
 */
public class SphereCreatorImplTest {

    private static SphereCreator sphereCreator;
    private static CentrePointFactory centrePointFactory;
    private static SphereFactory sphereFactory;
    private static SphereCharacteristicsFactory sphereCharacteristicsFactory;

    @BeforeClass
    public static void initSphereCreator(){
        sphereCreator = new SphereCreatorImpl();
    }

    @BeforeClass
    public static void initFactories(){
        centrePointFactory = new CentrePointFactoryImpl();
        sphereFactory = new SphereFactoryImpl();
        sphereCharacteristicsFactory = new SphereCharacteristicsFactoryImpl();
    }

    @Test(expected = DataAfterValidationNotExistException.class)
    public void createSphereAndCharacteristicsFromNull() throws DataAfterValidationNotExistException {
        List<String> dataNullList = null;
        long expcted = 0;
        long actual = sphereCreator.createSphereAndCharacteristics(dataNullList, centrePointFactory,
                sphereFactory, sphereCharacteristicsFactory).size();

        Assert.assertEquals("map must be empty", expcted, actual);
    }

    @Test(expected = DataAfterValidationNotExistException.class)
    public void createSphereAndCharacteristicsFromEmpty() throws DataAfterValidationNotExistException{
        List<String> dataEmptyList = new LinkedList<>();
        long expected = 0;
        long actual = sphereCreator.createSphereAndCharacteristics(dataEmptyList, centrePointFactory, sphereFactory,
                sphereCharacteristicsFactory).size();
        Assert.assertEquals("map must be empty", expected, actual);
    }

    @Test
    public void createSphereAndCharacteristicsNotValid(){
        List<String> dataNotValidList = new LinkedList<>();
        dataNotValidList.add("bla bla bla bla");
        dataNotValidList.add("1..0 1... +-+11 *1");
        dataNotValidList.add("1 1 1 -1");

        boolean condition = false;
        try {
            condition = sphereCreator.createSphereAndCharacteristics(dataNotValidList, centrePointFactory, sphereFactory,
                    sphereCharacteristicsFactory).isEmpty();
        } catch (DataAfterValidationNotExistException e) {

        }
        Assert.assertTrue("this list must be clean", condition);
    }

    @Test
    public void createSphereAndCharacteristics(){
        List<String> dataList = new LinkedList<>();
        dataList.add("1 1 1 1");
        dataList.add("0.0 0.2 0.3 0.4");
        dataList.add("9990 0.2 0.3 0.4");
        dataList.add("0.0 0.0002 0.3 0.4");
        dataList.add("0.0 0.0002 0.3 0.00004");
        long expected = 5;
        long actual = 0;
        try {
            actual = sphereCreator.createSphereAndCharacteristics(dataList, centrePointFactory, sphereFactory,
                    sphereCharacteristicsFactory).size();
        } catch (DataAfterValidationNotExistException e) {

        }
        Assert.assertEquals("junky sphereCharacteristics creator", expected, actual);
    }

    @Test(expected = CentreCoordinateNotValidException.class)
    public void createCentrePointWithNull() throws CentreCoordinateNotValidException {
        String strX = null;
        String strY = null;
        String strZ = null;
        sphereCreator.createCentrePoint(centrePointFactory, strX, strY, strZ);
        Assert.fail("this method should not work with this data");
    }

    @Test(expected = CentreCoordinateNotValidException.class)
    public void createCentrePointNotValid() throws CentreCoordinateNotValidException {
        String strX = "dfgdf";
        String strY = "1";
        String strZ = "1";
        sphereCreator.createCentrePoint(centrePointFactory, strX, strY, strZ);
        Assert.fail("this method should not work with this data");
    }

    @Test
    public void createCentrePoint() throws CentreCoordinateNotValidException {
        String strX = "-1.2";
        String strY = "1.0";
        String strZ = "1.2";
        Assert.assertNotNull("data for create point is valid, method works incorrect",
                sphereCreator.createCentrePoint(centrePointFactory, strX, strY, strZ));
    }

    @Test
    public void createSphereWithNull() throws RadiusNotValidException {
        CentrePoint centrePoint = null;
        String radius = "999";
        Assert.assertNull("sphereCreator.createSphere() should not work with this data",
                sphereCreator.createSphere(sphereFactory, centrePoint, radius));
    }

    @Test(expected = RadiusNotValidException.class)
    public void createSphereInvalidRadius() throws RadiusNotValidException {
        CentrePoint centrePoint = centrePointFactory.createPoint(1, 1, 1);
        String radius = "dfgdfg";
        sphereCreator.createSphere(sphereFactory, centrePoint, radius);
        Assert.fail("this method should not work with this data");
    }

    @Test
    public void createSphereNoRadius() throws RadiusNotValidException {
        CentrePoint centrePoint = centrePointFactory.createPoint(1, 1, 1);
        String radius = "-999";
        Assert.assertNull("sphereCreator.createSphere() should not work with this data",
                sphereCreator.createSphere(sphereFactory, centrePoint, radius));
    }

    @Test
    public void createSphere() throws RadiusNotValidException {
        CentrePoint centrePoint = centrePointFactory.createPoint(1, 1, 1);
        String radius = "999";
        Assert.assertNotNull("data for create sphere is valid, method works incorrect",
                sphereCreator.createSphere(sphereFactory, centrePoint, radius));
    }


}
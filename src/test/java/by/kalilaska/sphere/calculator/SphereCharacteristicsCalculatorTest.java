package by.kalilaska.sphere.calculator;

import by.kalilaska.sphere.entity.CentrePoint;
import by.kalilaska.sphere.entity.impl.Sphere;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by lovcov on 01.06.2017.
 */
@RunWith(Parameterized.class)
public class SphereCharacteristicsCalculatorTest {

    private Sphere sphere;
    private double expectedSurfaceArea;
    private double expectedVolume;
    private String expectedIntersectionXOY;
    private String expectedIntersectionXOZ;
    private String expectedIntersectionYOZ;

    public SphereCharacteristicsCalculatorTest(Sphere sphere,
                                               double expectedSurfaceArea, double expectedVolume,
                                               String expectedIntersectionXOY, String expectedIntersectionXOZ,
                                               String expectedIntersectionYOZ){
        this.sphere = sphere;
        this.expectedSurfaceArea = expectedSurfaceArea;
        this.expectedVolume = expectedVolume;
        this.expectedIntersectionXOY = expectedIntersectionXOY;
        this.expectedIntersectionXOZ = expectedIntersectionXOZ;
        this.expectedIntersectionYOZ = expectedIntersectionYOZ;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData(){

        double x1 = 13;
        double y1 = -13;
        double z1 = 1;
        double radius1 = 3;
        CentrePoint centre1 = new CentrePoint(x1, y1, z1);
        Sphere sphere1 = new Sphere(centre1, radius1);
        double expectedSurfaceArea1 = 113.097;
        double expectedVolume1 = 113.097;

        String expectedIntersectionXOY1 = SphereCharacteristicsCalculator.XOY_INTERSECTION + String.format("%.3f", (2.857));
        String expectedIntersectionXOZ1 = SphereCharacteristicsCalculator.DEFAULT_XOZ_INTERSECTION;
        String expectedIntersectionYOZ1 = SphereCharacteristicsCalculator.DEFAULT_YOZ_INTERSECTION;

        double x2 = 13;
        double y2 = -13;
        double z2 = 1;
        double radius2 = 10;
        CentrePoint centre2 = new CentrePoint(x2, y2, z2);
        Sphere sphere2 = new Sphere(centre2, radius2);
        double expectedSurfaceArea2 = 1256.637;
        double expectedVolume2 = 4188.790;
        String expectedIntersectionXOY2 = SphereCharacteristicsCalculator.XOY_INTERSECTION + String.format("%.3f", (1.352));
        String expectedIntersectionXOZ2 = SphereCharacteristicsCalculator.DEFAULT_XOZ_INTERSECTION;
        String expectedIntersectionYOZ2 = SphereCharacteristicsCalculator.DEFAULT_YOZ_INTERSECTION;

        double x3 = 0;
        double y3 = 0;
        double z3 = 0;
        double radius3 = 5;
        CentrePoint centre3 = new CentrePoint(x3, y3, z3);
        Sphere sphere3 = new Sphere(centre3, radius3);
        double expectedSurfaceArea3 = 314.159;
        double expectedVolume3 = 523.598;
        String expectedIntersectionXOY3 = SphereCharacteristicsCalculator.XOY_INTERSECTION + String.format("%.3f", (1.000));
        String expectedIntersectionXOZ3 = SphereCharacteristicsCalculator.XOZ_INTERSECTION + String.format("%.3f", (1.000));
        String expectedIntersectionYOZ3 = SphereCharacteristicsCalculator.YOZ_INTERSECTION + String.format("%.3f", (1.000));

        double x4 = 5;
        double y4 = 5;
        double z4 = 5;
        double radius4 = 5;
        CentrePoint centre4 = new CentrePoint(x4, y4, z4);
        Sphere sphere4 = new Sphere(centre4, radius4);
        double expectedSurfaceArea4 = 314.159;
        double expectedVolume4 = 523.598;
        String expectedIntersectionXOY4 = SphereCharacteristicsCalculator.XOY_TOUCHING;
        String expectedIntersectionXOZ4 = SphereCharacteristicsCalculator.XOZ_TOUCHING;
        String expectedIntersectionYOZ4 = SphereCharacteristicsCalculator.YOZ_TOUCHING;

        double x5 = -5;
        double y5 = -5;
        double z5 = -5;
        double radius5 = 5;
        CentrePoint centre5 = new CentrePoint(x5, y5, z5);
        Sphere sphere5 = new Sphere(centre5, radius5);
        double expectedSurfaceArea5 = 314.159;
        double expectedVolume5 = 523.598;
        String expectedIntersectionXOY5 = SphereCharacteristicsCalculator.XOY_TOUCHING;
        String expectedIntersectionXOZ5 = SphereCharacteristicsCalculator.XOZ_TOUCHING;
        String expectedIntersectionYOZ5 = SphereCharacteristicsCalculator.YOZ_TOUCHING;

        Object[][] data = new Object[][]{{sphere1, expectedSurfaceArea1, expectedVolume1,
                expectedIntersectionXOY1, expectedIntersectionXOZ1, expectedIntersectionYOZ1},

                {sphere2, expectedSurfaceArea2, expectedVolume2,
                        expectedIntersectionXOY2, expectedIntersectionXOZ2, expectedIntersectionYOZ2},

                {sphere3, expectedSurfaceArea3, expectedVolume3,
                expectedIntersectionXOY3, expectedIntersectionXOZ3, expectedIntersectionYOZ3},

                {sphere4, expectedSurfaceArea4, expectedVolume4,
                expectedIntersectionXOY4, expectedIntersectionXOZ4, expectedIntersectionYOZ4},

                {sphere5, expectedSurfaceArea5, expectedVolume5,
                expectedIntersectionXOY5, expectedIntersectionXOZ5, expectedIntersectionYOZ5}};
        return Arrays.asList(data);
    }

    @Test
    public void calculateSphereSurfaceArea() {
        double actual = SphereCharacteristicsCalculator.calculateSphereSurfaceArea(sphere);
        Assert.assertEquals("calculator count surface area incorrect for this sphere: " + sphere,
                expectedSurfaceArea, actual, 0.001);

    }

    @Test
    public void calculateSphereVolume() {
        double actual = SphereCharacteristicsCalculator.calculateSphereVolume(sphere);
        Assert.assertEquals("calculator count volume incorrect for this sphere: " + sphere,
                expectedVolume, actual, 0.001);
    }

    @Test
    public void intersectionWithXOY(){
        boolean condition = SphereCharacteristicsCalculator.intersectionWithXOY(sphere)
                .equals(expectedIntersectionXOY);
        Assert.assertTrue("calculator count intersection with XOY incorrect for this sphere: " + sphere,
                condition);
    }

    @Test
    public void intersectionWithXOZ(){
        boolean condition = SphereCharacteristicsCalculator.intersectionWithXOZ(sphere)
                .equals(expectedIntersectionXOZ);
        Assert.assertTrue("calculator count intersection with XOZ incorrect for this sphere: " + sphere,
                condition);

    }

    @Test
    public void intersectionWithYOZ(){
        boolean condition = SphereCharacteristicsCalculator.intersectionWithYOZ(sphere)
                .equals(expectedIntersectionYOZ);
        Assert.assertTrue("calculator count intersection with YOZ incorrect for this sphere: " + sphere,
                condition);
    }

}
package by.kalilaska.sphere.validator.impl;

import by.kalilaska.sphere.appExecutor.AppExecutor;
import by.kalilaska.sphere.exception.DataNotExistException;
import by.kalilaska.sphere.validator.DataValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lovcov on 31.05.2017.
 */
public class DataValidatorImplTest {
    private static DataValidator dataValidator;

    @BeforeClass
    public static void initDataValidator(){
        dataValidator = new DataValidatorImpl(AppExecutor.getAmountDataInOneRow());
    }

    @Test(expected = DataNotExistException.class)
    public void validateNullData() throws DataNotExistException {
        List<String> dataNullList = null;
        dataValidator.validatePointAndRadiusData(dataNullList);
        Assert.assertNull("this list must be null", dataNullList);
    }

    @Test(expected = DataNotExistException.class)
    public void validateEmptyData() throws DataNotExistException {
        List<String> dataEmptyList = new LinkedList<>();
        dataValidator.validatePointAndRadiusData(dataEmptyList);
        boolean condition = dataEmptyList.isEmpty();
        Assert.assertTrue("this list must be empty", condition);
    }

    @Test()
    public void validateNotValidData(){
        List<String> dataNotValidList = new LinkedList<>();
        dataNotValidList.add("bla bla bla");
        dataNotValidList.add("1..0 1... +-+11 *1");
        dataNotValidList.add("1 1 1 -1");
        dataNotValidList.add("1 1 1 1 1");
        dataNotValidList.add("1 1 1");
        try {
            dataValidator.validatePointAndRadiusData(dataNotValidList);
        } catch (DataNotExistException e) {

        }
        boolean condition = dataNotValidList.isEmpty();
        Assert.assertTrue("this list must be empty", condition);
    }

    @Test()
    public void validatePointAndRadiusData(){
        List<String> dataList = new LinkedList<>();
        dataList.add("bla bla bla");
        dataList.add("1..0 1... +-+11 *1");
        dataList.add("1 1 1 1");
        dataList.add("1.0; 2.0, 3.0 4.0");
        dataList.add("1 1 1 1 1");
        dataList.add("1 1 1");
        try {
            dataValidator.validatePointAndRadiusData(dataList);
        } catch (DataNotExistException e) {

        }
        long expected = 2;
        long actual = dataList.size();
        Assert.assertEquals("this list must be empty", expected, actual);
    }

}
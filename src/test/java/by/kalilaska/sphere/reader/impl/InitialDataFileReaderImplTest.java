package by.kalilaska.sphere.reader.impl;

import by.kalilaska.sphere.appExecutor.AppExecutor;
import by.kalilaska.sphere.reader.InitialDataFileReader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by lovcov on 30.05.2017.
 */
public class InitialDataFileReaderImplTest {

    private static InitialDataFileReader fileReader;

    @BeforeClass
    public static void initFileReader(){
        fileReader = new InitialDataFileReaderImpl();
    }

    @Test
    public void readFileUnsuccess() throws IOException {
        String fileName = AppExecutor.getSphereDataFileName() + "dlfkgdkfjg";
        boolean condition = fileReader.readFile(fileName).isEmpty();
        Assert.assertTrue("this crazy file is not exist", condition);
    }

    @Test
    public void readEmptyFile() {
        String fileName = AppExecutor.getTestFileName();
        boolean condition = fileReader.readFile(fileName).isEmpty();
        Assert.assertTrue("file must be empty", condition);
    }

    @Test
    public void readFileSuccess() {
        String fileName = AppExecutor.getSphereDataFileName();
        boolean condition = fileReader.readFile(fileName).isEmpty();
        Assert.assertFalse("test failed, this is really file", condition);
    }

}
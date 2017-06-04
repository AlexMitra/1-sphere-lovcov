package by.kalilaska.sphere.appExecutor;

import by.kalilaska.sphere.creator.SphereCreator;
import by.kalilaska.sphere.creator.impl.SphereCreatorImpl;
import by.kalilaska.sphere.entity.impl.Sphere;
import by.kalilaska.sphere.entity.impl.SphereCharacteristics;
import by.kalilaska.sphere.exception.DataAfterValidationNotExistException;
import by.kalilaska.sphere.exception.DataNotExistException;
import by.kalilaska.sphere.factory.CentrePointFactory;
import by.kalilaska.sphere.factory.SphereCharacteristicsFactory;
import by.kalilaska.sphere.factory.SphereFactory;
import by.kalilaska.sphere.factory.impl.CentrePointFactoryImpl;
import by.kalilaska.sphere.factory.impl.SphereCharacteristicsFactoryImpl;
import by.kalilaska.sphere.factory.impl.SphereFactoryImpl;
import by.kalilaska.sphere.reader.InitialDataFileReader;
import by.kalilaska.sphere.reader.impl.InitialDataFileReaderImpl;
import by.kalilaska.sphere.storage.ResultStorage;
import by.kalilaska.sphere.validator.DataValidator;
import by.kalilaska.sphere.validator.impl.DataValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;


public class AppExecutor {
    private final static String PATH = "src/main/resources/file/";
    private final static String SPHERE_DATA_FILE_NAME = "InitialSphereData.txt";
    private final static String TEST_FILE_NAME = "TestData.txt";
    private final static int AMOUNT_DATA_IN_ONE_ROW = 4;

    private final static Logger LOGGER = LogManager.getLogger(AppExecutor.class);

    public static void execute() {
        ResultStorage resultStorage = null;

        InitialDataFileReader fileReader = new InitialDataFileReaderImpl();
        DataValidator dataValidator = new DataValidatorImpl(AMOUNT_DATA_IN_ONE_ROW);

        CentrePointFactory centrePointFactory = new CentrePointFactoryImpl();
        SphereFactory sphereFactory = new SphereFactoryImpl();
        SphereCharacteristicsFactory sphereCharacteristicsFactory = new SphereCharacteristicsFactoryImpl();


        SphereCreator sphereCreator = new SphereCreatorImpl();

        List<String> fileContentList = fileReader.readFile(PATH + SPHERE_DATA_FILE_NAME);
        Map<Sphere, SphereCharacteristics> sphereMap;
        try {
            dataValidator.validatePointAndRadiusData(fileContentList);

            resultStorage = ResultStorage.getInstance();
            sphereMap = sphereCreator.createSphereAndCharacteristics(fileContentList,
                    centrePointFactory, sphereFactory,
                    sphereCharacteristicsFactory);
            resultStorage.addSeveralCouples(sphereMap);
        } catch (DataNotExistException e){
            LOGGER.log(Level.ERROR, "Data not exist, " + e.getMessage());
        } catch (DataAfterValidationNotExistException e) {
            LOGGER.log(Level.ERROR, "After validation data not exist, " + e.getMessage());
        }

        LOGGER.log(Level.INFO, "Results: " + resultStorage);
    }

    public static String getSphereDataFileName(){
        return PATH + SPHERE_DATA_FILE_NAME;
    }

    public static String getTestFileName(){
        return PATH + TEST_FILE_NAME;
    }

    public static int getAmountDataInOneRow(){
        return AMOUNT_DATA_IN_ONE_ROW;
    }

}

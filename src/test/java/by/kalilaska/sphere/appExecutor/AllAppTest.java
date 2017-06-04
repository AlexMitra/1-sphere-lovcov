package by.kalilaska.sphere.appExecutor;

import by.kalilaska.sphere.calculator.SphereCharacteristicsCalculatorTest;
import by.kalilaska.sphere.creator.impl.SphereCreatorImplTest;
import by.kalilaska.sphere.parser.OneRowParserTest;
import by.kalilaska.sphere.reader.impl.InitialDataFileReaderImplTest;
import by.kalilaska.sphere.storage.ResultStorageTest;
import by.kalilaska.sphere.validator.impl.DataValidatorImplTest;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by lovcov on 30.05.2017.
 */
@Suite.SuiteClasses({InitialDataFileReaderImplTest.class,
        DataValidatorImplTest.class, SphereCreatorImplTest.class,
        OneRowParserTest.class, SphereCharacteristicsCalculatorTest.class, ResultStorageTest.class})
@RunWith(Suite.class)
public class AllAppTest {
    @AfterClass
    public static void runApplication(){
        AppExecutor.execute();
    }
}

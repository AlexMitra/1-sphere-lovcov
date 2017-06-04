package by.kalilaska.sphere.validator.impl;

import by.kalilaska.sphere.exception.DataNotExistException;
import by.kalilaska.sphere.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;

/**
 * Created by lovcov on 25.05.2017.
 */
public class DataValidatorImpl implements DataValidator{
    private final static Logger LOGGER = LogManager.getLogger(DataValidatorImpl.class);

    private final static int DEFAUL_AMOUNT_DATA_IN_ONE_ROW = 4;
    private final static String REGEX_FOR_UNSIGNED_NUMBER = "(-|\\+)?(\\d)+(\\.)?(\\d)*";
    private final static String REGEX_FOR_SIGNED_NUMBER = "(\\+)?(\\d)+(\\.)?(\\d)*";
    private final static String REGEX_FOR_DELIMIT = "(\\s)*(,|;)?(\\s)*";

    private static String RegexForValidation = "";

    public DataValidatorImpl() {
        createRegexForValidation(DEFAUL_AMOUNT_DATA_IN_ONE_ROW);
    }

    public DataValidatorImpl(int amountDataInOneRow) {
        createRegexForValidation(amountDataInOneRow);
    }

    @Override
    public void validatePointAndRadiusData(List<String> source) throws DataNotExistException {
        if(source == null || source.isEmpty()){
            throw new DataNotExistException("After reading data not exist");
        }

        Iterator<String> iterator = source.iterator();
        String row;
        while (iterator.hasNext()){
            row = iterator.next();

            if (!row.matches(RegexForValidation)){
                iterator.remove();
            }

        }

    }

    private void createRegexForValidation(int amountDataInOneRow){
        if(RegexForValidation.isEmpty()){
            for (int i = 0; i <amountDataInOneRow ; i++) {
                if(i == (amountDataInOneRow-1)){
                    RegexForValidation += REGEX_FOR_SIGNED_NUMBER;
                }else{
                    RegexForValidation += REGEX_FOR_UNSIGNED_NUMBER;
                }
                RegexForValidation += REGEX_FOR_DELIMIT;
            }
        }
    }

}

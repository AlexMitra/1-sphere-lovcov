package by.kalilaska.sphere.validator;

import by.kalilaska.sphere.exception.DataNotExistException;

import java.util.List;

/**
 * Created by lovcov on 25.05.2017.
 */
public interface DataValidator {
    void validatePointAndRadiusData(List<String> source) throws DataNotExistException;
}

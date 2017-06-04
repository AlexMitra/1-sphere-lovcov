package by.kalilaska.sphere.reader.impl;

import by.kalilaska.sphere.reader.InitialDataFileReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lovcov on 25.05.2017.
 */
public class InitialDataFileReaderImpl implements InitialDataFileReader {
    private final static Logger LOGGER = LogManager.getLogger(InitialDataFileReaderImpl.class);

    public InitialDataFileReaderImpl() {
    }

    public List<String> readFile(String fileName) {

        List<String> dataList = new LinkedList<>();
        try {
            dataList = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "input exception");
        }
        return dataList;

    }

}

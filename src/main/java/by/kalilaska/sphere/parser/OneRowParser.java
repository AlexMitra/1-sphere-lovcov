package by.kalilaska.sphere.parser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lovcov on 29.05.2017.
 */
public class OneRowParser {
    private final static String REGEX_FOR_DELIMIT_SYMBOLS = "(,|;)";
    private final static String REGEX_FOR_BLANK_SYMBOLS = "(\\s)+";

    public static void parsStringToList(String source, List<String> rowList){
        String row;
        row = source.replaceAll(REGEX_FOR_DELIMIT_SYMBOLS, "");
        rowList.addAll(Arrays.asList(row.split(REGEX_FOR_BLANK_SYMBOLS)));
    }
}

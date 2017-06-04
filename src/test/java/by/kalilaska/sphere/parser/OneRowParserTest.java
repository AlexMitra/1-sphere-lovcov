package by.kalilaska.sphere.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lovcov on 01.06.2017.
 */
public class OneRowParserTest {
    @Test
    public void parsStringToList() throws Exception {
        String row = "1.0,,,   2;3., 5;7; .5 55 ;;5;; .25;";
        List<String> list = new LinkedList<>();
        long expected = 7;
        OneRowParser.parsStringToList(row, list);
        long actual = list.size();
        Assert.assertEquals("hzhzhz", expected, actual);
    }

}
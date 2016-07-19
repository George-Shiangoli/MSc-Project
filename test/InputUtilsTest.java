import exception.InvalidDataClassException;
import exception.InvalidTimeSeriesException;
import exception.MissingTimeSeriesException;
import exception.NoDataClassException;
import io.InputUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Tests for the static methods of the InputUtils class of the io package.
 * Created by George Shiangoli on 19/07/2016.
 */
public class InputUtilsTest {

    private List<List<String>> incorrectData;

    @Before
    public void init() {
        String path = new File("").getAbsolutePath() + File.separator
                + "test_resources" + File.separator
                + "InputUtilsTest_Resources" + File.separator
                + "validateInput.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            incorrectData = reader.lines().map(line -> Stream.of(line.split(",")).filter(s -> !s.trim().isEmpty())
                                                        .collect(Collectors.toList())).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Validating individual time series line input

    @Test (expected = NoDataClassException.class)
    public void noSpecifiedClassFails() {
        InputUtils.validateLine(incorrectData.get(0));
    }

    @Test (expected = InvalidDataClassException.class)
    public void incorrectClassFormatFails() {
        InputUtils.validateLine(incorrectData.get(1));
    }

    @Test (expected = InvalidTimeSeriesException.class)
    public void incorrectTimeSeriesDataFails() {
        InputUtils.validateLine(incorrectData.get(2));
    }

    @Test (expected = NoDataClassException.class)
    public void blankClassFails() {
        InputUtils.validateLine(incorrectData.get(3));
    }

    @Test (expected = MissingTimeSeriesException.class)
    public void missingTimeSeriesFails() { InputUtils.validateLine(incorrectData.get(4)); }
}
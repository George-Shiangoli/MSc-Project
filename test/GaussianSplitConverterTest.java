import org.junit.Test;
import static org.junit.Assert.*;
import preprocessing.DataConverter;
import preprocessing.GaussianSplitConverter;

import java.util.Arrays;
import java.util.Collections;

/**
 * Tests for the GaussianSplitConverter class of the preprocessing package.
 * Created by George Shiangoli on 27/07/2016.
 */
public class GaussianSplitConverterTest {

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorWithAlphabetSizeOfOneFails() {
        new GaussianSplitConverter(1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorWithAlphabetSizeOfZeroFails() {
        new GaussianSplitConverter(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeAlphabetSizeFails() {
        new GaussianSplitConverter(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorWithAlphabetSizeGreaterThan26Fails() {
        new GaussianSplitConverter(27);
    }

    @Test
    public void testToWordWithSmallestAlphabetAndSingleValue() {
        DataConverter converter = new GaussianSplitConverter(2);
        assertEquals("a", converter.toWord(Collections.singletonList(-0.2345)));
    }

    @Test
    public void testToWordWithSmallestAlphabet() {
        DataConverter converter = new GaussianSplitConverter(2);
        assertEquals("abbba", converter.toWord(Arrays.asList(-0.2345, 1.1, 0.556, 0.00001,-0.5)));
    }

    @Test
    public void testToWordWithSmallestAlphabetAndBorderlineValue() {
        DataConverter converter = new GaussianSplitConverter(2);
        assertEquals("ab", converter.toWord(Arrays.asList(-0.01, 0.0)));
    }

}
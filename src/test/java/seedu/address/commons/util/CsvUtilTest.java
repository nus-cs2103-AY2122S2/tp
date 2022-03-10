package seedu.address.commons.util;

import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.testutil.TestUtil;


/**
 * Tests CSV Read and Write
 */
public class CsvUtilTest {

    private static final Path TEST_FILE = TestUtil.getFilePathInSandboxFolder("test.csv");

    @Test
    public void writeAndReadCsvFileWithoutHeader_exceptionThrown() {
        Assertions.assertThrows(DataConversionException.class, () -> {
            CsvUtil.writePeople(new ArrayList<>(), TEST_FILE);
            CsvUtil.loadCsvFile(TEST_FILE);
        });
    }
}

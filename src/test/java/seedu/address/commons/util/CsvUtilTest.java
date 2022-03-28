package seedu.address.commons.util;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.storage.CsvAdaptedInsurancePackage;
import seedu.address.storage.CsvAdaptedPerson;
import seedu.address.testutil.TestUtil;


/**
 * Tests CSV Read and Write
 */
public class CsvUtilTest {

    private static CsvAdaptedPerson DUMMY_PERSON =
            new CsvAdaptedPerson(null, null, null, null, null, null);

    // fake list with only 1 person
    private static List<CsvAdaptedPerson> DUMMY_PERSON_LIST = Arrays.asList(new CsvAdaptedPerson[]{DUMMY_PERSON});

    private static CsvAdaptedInsurancePackage DUMMY_PACKAGE = new CsvAdaptedInsurancePackage("Test", "Test");

    // fake list with only 1 package
    private static List<CsvAdaptedInsurancePackage> DUMMY_PACKAGE_LIST =
            Arrays.asList(new CsvAdaptedInsurancePackage[]{DUMMY_PACKAGE});

    private static final Path TEST_FILE = TestUtil.getFilePathInSandboxFolder("test.csv");

    @Test
    public void writeAndReadAbCsvFile() {
        try {
            CsvUtil.saveAbCsvFile(DUMMY_PERSON_LIST, TEST_FILE);
            List<CsvAdaptedPerson> results = CsvUtil.loadAbCsvFile(TEST_FILE);
            Assertions.assertEquals(DUMMY_PERSON_LIST.size(), results.size());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void writeAndReadIpCsvFile() {
        try {
            CsvUtil.saveIpCsvFile(DUMMY_PACKAGE_LIST, TEST_FILE);
            List<CsvAdaptedInsurancePackage> results = CsvUtil.loadIpCsvFile(TEST_FILE);
            Assertions.assertEquals(DUMMY_PACKAGE_LIST.size(), results.size());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

}

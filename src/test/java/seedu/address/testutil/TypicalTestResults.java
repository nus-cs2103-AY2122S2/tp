package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MedBook;
import seedu.address.model.testresult.TestResult;

/**
 * A utility class containing a list of {@code TestResult} objects to be used in tests.
 */
public class TypicalTestResults {

    public static final TestResult TEST_RESULT_A = new TestResultBuilder().withNric("G1234567L")
            .withMedicalTest("X-Ray").withDate("2022-02-04")
            .withResult("Broken bone")
            .build();
    public static final TestResult TEST_RESULT_B = new TestResultBuilder().withNric("G1234567L")
            .withMedicalTest("CT Scan").withDate("2022-02-05")
            .withResult("Brain damage detected")
            .build();
    public static final TestResult TEST_RESULT_C = new TestResultBuilder().withNric("G1234568L")
            .withMedicalTest("MRI").withDate("2022-02-07")
            .withResult("Extensive tissue loss in the right cerebral hemisphere")
            .build();

    private TypicalTestResults() {}

    /**
     * Returns an {@code MedBook} with all the typical test results.
     */
    public static MedBook getTypicalMedBook() {
        MedBook ab = new MedBook();
        for (TestResult testResult : getTypicalTestResult()) {
            ab.addTestResult(testResult);
        }
        return ab;
    }

    public static List<TestResult> getTypicalTestResult() {
        return new ArrayList<>(Arrays.asList(TEST_RESULT_A, TEST_RESULT_B, TEST_RESULT_C));
    }
}

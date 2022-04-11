package seedu.address.model.testresult;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTestResults.TEST_RESULT_A;
import static seedu.address.testutil.TypicalTestResults.TEST_RESULT_B;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TestResultBuilder;

public class TestResultTest {

    @Test
    public void equals() {
        TestResult testResult = new TestResultBuilder(TEST_RESULT_A).build();

        assertTrue(TEST_RESULT_A.equals(testResult));
        assertTrue(TEST_RESULT_A.equals(TEST_RESULT_A));

        assertFalse(TEST_RESULT_A.equals(null));
        assertFalse(TEST_RESULT_A.equals(5));

        assertFalse(TEST_RESULT_A.equals(TEST_RESULT_B));

        TestResult editedTestResult = new TestResultBuilder(TEST_RESULT_A).withNric("G1234565L").build();
        assertFalse(TEST_RESULT_A.equals(editedTestResult));

        editedTestResult = new TestResultBuilder(TEST_RESULT_A).withMedicalTest("MRI").build();
        assertFalse(TEST_RESULT_A.equals(editedTestResult));
    }
}

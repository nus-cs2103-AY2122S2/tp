package seedu.address.model.testresult;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

import seedu.address.model.patient.Nric;

/**
 * Represents a TestResult in Medbook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TestResult {

    // Relationship fields - owner nric
    private final Nric ownerNric;

    // Data fields
    private final TestDate testDate;
    private final MedicalTest medicalTest;
    private final Result result;

    /**
     * Every field must be present and not null.
     */
    public TestResult(Nric ownerNric, TestDate testDate, MedicalTest medicalTest, Result result) {
        requireAllNonNull(ownerNric, testDate, medicalTest, result);
        this.ownerNric = ownerNric;
        this.testDate = testDate;
        this.medicalTest = medicalTest;
        this.result = result;
    }

    public Nric getOwnerNric() {
        return ownerNric;
    }

    public TestDate getTestDate() {
        return testDate;
    }

    public MedicalTest getMedicalTest() {
        return medicalTest;
    }

    public Result getResult() {
        return result;
    }


    /**
     * Returns true if both contacts have the same owner, date, medical test taken and test result.
     * This defines a weaker notion of equality between two test results.
     */
    public boolean isSameTestResult(TestResult otherTestResult) {
        if (otherTestResult == this) {
            return true;
        }

        return otherTestResult != null
                && otherTestResult.getOwnerNric().equals(getOwnerNric())
                && otherTestResult.getTestDate().equals(getTestDate())
                && otherTestResult.getMedicalTest().equals(getMedicalTest())
                && otherTestResult.getResult().equals(getResult());
    }

    /**
     * Returns true if both contacts have the same data fields.
     * This defines a stronger notion of equality between two test results.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TestResult)) {
            return false;
        }

        TestResult otherTestResult = (TestResult) other;
        return otherTestResult.getOwnerNric().equals(getOwnerNric())
                && otherTestResult.getTestDate().equals(getTestDate())
                && otherTestResult.getMedicalTest().equals(getMedicalTest())
                && otherTestResult.getResult().equals(getResult());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(testDate, medicalTest, result);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Owner NRIC: ")
                .append(getOwnerNric())
                .append("; Test Date: ")
                .append(getTestDate())
                .append("; Test Taken: ")
                .append(getMedicalTest())
                .append("; Test Results: ")
                .append(getResult());

        return builder.toString();
    }

}

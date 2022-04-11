package seedu.address.testutil;

import org.junit.jupiter.api.Test;
import seedu.address.model.patient.Nric;
import seedu.address.model.prescription.DrugName;
import seedu.address.model.prescription.Instruction;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.PrescriptionDate;
import seedu.address.model.testresult.MedicalTest;
import seedu.address.model.testresult.Result;
import seedu.address.model.testresult.TestDate;
import seedu.address.model.testresult.TestResult;

/**
 * A utility class to help with building TestResult objects.
 */
public class TestResultBuilder {

    public static final String DEFAULT_NRIC = "S7654321L";
    public static final String DEFAULT_MEDICAL_TEST = "X-Ray";
    public static final String DEFAULT_DATE = "2022-04-08";
    public static final String DEFAULT_RESULT = "Broken bone";

    private Nric patientNric;

    // Data fields
    private TestDate testDate;
    private MedicalTest medicalTest;
    private Result result;

    /**
     * Creates a {@code TestResultBuilder} with the default details.
     */
    public TestResultBuilder() {
        patientNric = new Nric(DEFAULT_NRIC);
        medicalTest = new MedicalTest(DEFAULT_MEDICAL_TEST);
        testDate = new TestDate(DEFAULT_DATE);
        result = new Result(DEFAULT_RESULT);
    }

    /**
     * Initializes the Test Result with the data of {@code testResultToCopy}.
     */
    public TestResultBuilder(TestResult testResultToCopy) {
        patientNric = testResultToCopy.getPatientNric();
        medicalTest = testResultToCopy.getMedicalTest();
        testDate = testResultToCopy.getTestDate();
        result = testResultToCopy.getResult();
    }

    /**
     * Sets the {@code patientNric} of the {@code TestResult} that we are building.
     */
    public TestResultBuilder withNric(String nric) {
        this.patientNric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code medicalTest} of the {@code TestResult} that we are building.
     */
    public TestResultBuilder withMedicalTest(String testName) {
        this.medicalTest = new MedicalTest(testName);
        return this;
    }

    /**
     * Sets the {@code testDate} of the {@code TestResult} that we are building.
     */
    public TestResultBuilder withDate(String date) {
        this.testDate = new TestDate(date);
        return this;
    }

    /**
     * Sets the {@code result} of the {@code Prescription} that we are building.
     */
    public TestResultBuilder withResult(String result) {
        this.result = new Result(result);
        return this;
    }

    public TestResult build() {
        return new TestResult(patientNric, testDate, medicalTest, result);
    }
}

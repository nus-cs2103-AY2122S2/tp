package seedu.address.model.testresult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTestResults.TEST_RESULT_A;
import static seedu.address.testutil.TypicalTestResults.TEST_RESULT_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.testresult.exceptions.DuplicateTestResultException;
import seedu.address.model.testresult.exceptions.TestResultNotFoundException;
import seedu.address.testutil.TestResultBuilder;

public class UniqueTestResultListTest {

    private final UniqueTestResultList uniqueTestResultList = new UniqueTestResultList();

    @Test
    public void contains_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTestResultList.contains(null));
    }

    @Test
    public void contains_testResultNotInList_returnsFalse() {
        assertFalse(uniqueTestResultList.contains(TEST_RESULT_A));
    }

    @Test
    public void contains_testResultInList_returnsTrue() {
        uniqueTestResultList.add(TEST_RESULT_A);
        assertTrue(uniqueTestResultList.contains(TEST_RESULT_A));
    }


    @Test
    public void add_nullTestResult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTestResultList.add(null));
    }

    @Test
    public void add_duplicateTestResult_throwsDuplicateTestResultException() {
        uniqueTestResultList.add(TEST_RESULT_A);
        assertThrows(DuplicateTestResultException.class, () -> uniqueTestResultList.add(TEST_RESULT_A));
    }

    @Test
    public void setTestResult_nullTargetTestResult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTestResultList
                .setTestResult(null, TEST_RESULT_A));
    }

    @Test
    public void setTestResult_nullEditedTestResult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTestResultList
                .setTestResult(TEST_RESULT_A, null));
    }

    @Test
    public void setTestResult_targetTestResultNotInList_throwsPrescriptionNotFoundException() {
        assertThrows(TestResultNotFoundException.class, () -> uniqueTestResultList
                .setTestResult(TEST_RESULT_A, TEST_RESULT_A));
    }

    @Test
    public void setTestResult_editedTestResultIsSame_success() {
        uniqueTestResultList.add(TEST_RESULT_A);
        uniqueTestResultList.setTestResult(TEST_RESULT_A, TEST_RESULT_A);
        UniqueTestResultList expectedUniqueTestResultList = new UniqueTestResultList();
        expectedUniqueTestResultList.add(TEST_RESULT_A);
        assertEquals(expectedUniqueTestResultList, uniqueTestResultList);
    }

    @Test
    public void setTestResult_editedTestResultHasSameField_success() {
        uniqueTestResultList.add(TEST_RESULT_A);
        TestResult editedTestResult = new TestResultBuilder(TEST_RESULT_A).withMedicalTest("MRI")
                .withDate("2022-02-05")
                .build();
        uniqueTestResultList.setTestResult(TEST_RESULT_A, editedTestResult);
        UniqueTestResultList expectedUniqueTestResultList = new UniqueTestResultList();
        expectedUniqueTestResultList.add(editedTestResult);
        assertEquals(expectedUniqueTestResultList, uniqueTestResultList);
    }

    @Test
    public void setTestResult_editedTestResultHasDifferentIdentity_success() {
        uniqueTestResultList.add(TEST_RESULT_A);
        uniqueTestResultList.setTestResult(TEST_RESULT_A, TEST_RESULT_B);
        UniqueTestResultList expectedUniqueTestResultList = new UniqueTestResultList();
        expectedUniqueTestResultList.add(TEST_RESULT_B);
        assertEquals(expectedUniqueTestResultList, uniqueTestResultList);
    }

    @Test
    public void setTestResult_editedTestResultHasNonUniqueIdentity_throwsDuplicateTestResultException() {
        uniqueTestResultList.add(TEST_RESULT_A);
        uniqueTestResultList.add(TEST_RESULT_B);
        assertThrows(DuplicateTestResultException.class, () ->
                uniqueTestResultList.setTestResult(TEST_RESULT_A, TEST_RESULT_B));
    }

    @Test
    public void remove_nullTestResult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTestResultList.remove(null));
    }

    @Test
    public void remove_testResultDoesNotExist_throwsTestResultNotFoundException() {
        assertThrows(TestResultNotFoundException.class, () -> uniqueTestResultList.remove(TEST_RESULT_A));
    }

    @Test
    public void remove_existingTestResult_removesTestResult() {
        uniqueTestResultList.add(TEST_RESULT_A);
        uniqueTestResultList.remove(TEST_RESULT_A);
        UniqueTestResultList expectedUniqueTestResultList = new UniqueTestResultList();
        assertEquals(expectedUniqueTestResultList, uniqueTestResultList);
    }

    @Test
    public void setTestResults_nullUniqueTestResultList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueTestResultList.setTestResults((UniqueTestResultList) null));
    }

    @Test
    public void setTestResults_uniqueTestResultList_replacesOwnListWithProvidedUniqueTestResultList() {
        uniqueTestResultList.add(TEST_RESULT_A);
        UniqueTestResultList expectedUniqueTestResultList = new UniqueTestResultList();
        expectedUniqueTestResultList.add(TEST_RESULT_B);
        uniqueTestResultList.setTestResults(expectedUniqueTestResultList);
        assertEquals(expectedUniqueTestResultList, uniqueTestResultList);
    }

    @Test
    public void setTestResults_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueTestResultList.setTestResults((List<TestResult>) null));
    }

    @Test
    public void setTestResult_list_replacesOwnListWithProvidedList() {
        uniqueTestResultList.add(TEST_RESULT_A);
        List<TestResult> testResultList = Collections.singletonList(TEST_RESULT_B);
        uniqueTestResultList.setTestResults(testResultList);
        UniqueTestResultList expectedUniqueTestResultList = new UniqueTestResultList();
        expectedUniqueTestResultList.add(TEST_RESULT_B);
        assertEquals(expectedUniqueTestResultList, uniqueTestResultList);
    }

    @Test
    public void setTestResult_listWithDuplicateTestResults_throwsDuplicateTestResultException() {
        List<TestResult> listWithDuplicateTestResults = Arrays.asList(TEST_RESULT_A, TEST_RESULT_A);
        assertThrows(DuplicateTestResultException.class, () ->
                uniqueTestResultList.setTestResults(listWithDuplicateTestResults));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueTestResultList
                .asUnmodifiableObservableList().remove(0));
    }
}

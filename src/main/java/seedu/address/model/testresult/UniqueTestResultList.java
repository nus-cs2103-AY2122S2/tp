package seedu.address.model.testresult;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.testresult.exceptions.DuplicateTestResultException;
import seedu.address.model.testresult.exceptions.TestResultNotFoundException;

/**
 * A list of test results that enforces uniqueness between its elements and does not allow nulls.
 * A test results is considered unique by comparing using {@code TestResult#isSameTestResult(TestResult)}. As such,
 * adding and updating of test results uses TestResult#isSameTestResult(TestResult) for equality so as to ensure that
 * the test result being added or updated is unique in terms of identity in the UniqueTestResultList. However, the
 * removal of a test result uses TestResult#equals(Object) so as to ensure that the test result with exactly the same
 * fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see TestResult#isSameTestResult(TestResult)
 */
public class UniqueTestResultList implements Iterable<TestResult> {

    private final ObservableList<TestResult> internalList = FXCollections.observableArrayList();
    private final ObservableList<TestResult> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent test result as the given argument.
     */
    public boolean contains(TestResult toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTestResult);
    }

    /**
     * Adds a test result to the list.
     * The test result must not already exist in the list.
     */
    public void add(TestResult toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTestResultException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the test result {@code target} in the list with {@code editedTestResult}.
     * {@code target} must exist in the list.
     * The test result identity of {@code editedTestResult} must not be the same as another existing test result in the
     * list.
     */
    public void setTestResult(TestResult target, TestResult editedTestResult) {
        requireAllNonNull(target, editedTestResult);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TestResultNotFoundException();
        }

        if (!target.isSameTestResult(editedTestResult) && contains(editedTestResult)) {
            throw new DuplicateTestResultException();
        }

        internalList.set(index, editedTestResult);
    }

    /**
     * Removes the equivalent test result from the list.
     * The test result must exist in the list.
     */
    public void remove(TestResult toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TestResultNotFoundException();
        }
    }

    public void setTestResults(UniqueTestResultList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code testResults}.
     * {@code testResults} must not contain duplicate testResults.
     */
    public void setTestResults(List<TestResult> testResults) {
        requireAllNonNull(testResults);
        if (!testResultsAreUnique(testResults)) {
            throw new DuplicateTestResultException();
        }

        internalList.setAll(testResults);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TestResult> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TestResult> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTestResultList // instanceof handles nulls
                        && internalList.equals(((UniqueTestResultList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code testResults} contains only unique testResults.
     */
    private boolean testResultsAreUnique(List<TestResult> testResults) {
        for (int i = 0; i < testResults.size() - 1; i++) {
            for (int j = i + 1; j < testResults.size(); j++) {
                if (testResults.get(i).isSameTestResult(testResults.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

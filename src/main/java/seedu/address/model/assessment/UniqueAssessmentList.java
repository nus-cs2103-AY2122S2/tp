package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assessment.exceptions.AssessmentNotFoundException;
import seedu.address.model.assessment.exceptions.DuplicateAssessmentException;


/**
 * A list of Assessments that enforces the uniqueness between its elements and does not allow nulls.
 * An Assessment is considered unique by comparing using {@code Assessment#isSameAssessment(Assessment)}.
 * Adding and updating an Assessment uses {@code Assessment#isSameAssessment(Assessment)} for equality
 * to ensure that the assessment being added is unique in terms of identity in the UniqueAssessmentList.
 * However, the removal of an assessment uses {@code Assessment#equals(Object)} to ensure that the assessment
 * with exactly the same fields is removed.
 *
 * @see Assessment#isSameAssessment(Assessment)
 */
public class UniqueAssessmentList {
    private final ObservableList<Assessment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Assessment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent assessment as the given argument,
     * uses {@code Assessment#isSameAssessment(Assessment)}
     */
    public boolean contains(Assessment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAssessment);
    }

    /**
     * Returns true if the list contains an assessment with the given name.
     */
    public boolean containsByName(AssessmentName name) {
        requireNonNull(name);
        return internalList.stream().anyMatch(x -> x.hasName(name));
    }

    /**
     * Adds an assessment to the list.
     * The assessment must not already exist in the list.
     */
    public void add(Assessment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssessmentException();
        }
        internalList.add(toAdd);
    }

    public Assessment getByName(AssessmentName name) {
        requireNonNull(name);
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).hasName(name)) {
                Assessment assessment = internalList.get(i);
                return assessment;
            }
        }
        throw new AssessmentNotFoundException();
    }

    /**
     * Replaces the assessment {@code target} in the list with {@code editedAssessment}.
     * {@code target} must exist in the list.
     * The name of {@code editedAssessment} must not be the same as another existing assessment in the list.
     */
    public void setAssessment(Assessment target, Assessment editedAssessment) {
        requireAllNonNull(target, editedAssessment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AssessmentNotFoundException();
        }

        if (!target.isSameAssessment(editedAssessment) && contains(editedAssessment)) {
            throw new DuplicateAssessmentException();
        }

        internalList.set(index, editedAssessment);
    }

    /**
     * Removes the equivalent assessment from the list.
     * The assessment must exist in the list.
     */
    public void remove(Assessment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AssessmentNotFoundException();
        }
    }

    /**
     * Removes the assessment with the given name and returns the Assessment object.
     * @throws AssessmentNotFoundException if no assessment in the internal list has the given name.
     */
    public Assessment removeByName(AssessmentName name) {
        requireNonNull(name);
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).hasName(name)) {
                Assessment toRemove = internalList.get(i);
                remove(toRemove);
                return toRemove;
            }
        }
        throw new AssessmentNotFoundException();
    }

    /**
     * Returns the backing list as an unmodifiable list.
     */
    public ObservableList<Assessment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof UniqueAssessmentList
                && internalList.equals(((UniqueAssessmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Replaces the contents of this list with {@code assessments},
     * which must not contain duplicate assessments.
     */
    public void setAssessments(List<Assessment> assessments) {
        requireAllNonNull(assessments);
        if (!assessmentsAreUnique(assessments)) {
            throw new DuplicateAssessmentException();
        }
        internalList.setAll(assessments);
    }

    private boolean assessmentsAreUnique(List<Assessment> assessments) {
        for (int i = 0; i < assessments.size() - 1; i++) {
            for (int j = i + 1; j < assessments.size(); j++) {
                if (assessments.get(i).isSameAssessment(assessments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

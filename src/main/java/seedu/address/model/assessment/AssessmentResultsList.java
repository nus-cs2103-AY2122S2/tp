package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.model.assessment.exceptions.AssessmentNotFoundException;
import seedu.address.model.assessment.exceptions.DuplicateAssessmentException;
import seedu.address.model.assessment.exceptions.StudentResultNotFoundException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.model.tutorial.TutorialName;

/**
 * Contains a list of AssessmentResults.
 * Each AssessmentResults in the list should correspond to an Assessment in the UniqueAssessmentList.
 *
 * TODO: implement remove and other setters, depending on how commands are implemented.
 * @see AssessmentResults
 * @see StudentResult
 * @see UniqueAssessmentList
 */
public class AssessmentResultsList {
    // Identity field
    private final TutorialName tutorialName;

    // Data field
    private final List<AssessmentResults> assessmentResultsList = new ArrayList<>();
    private final List<AssessmentResults> unmodifiableResultsList = Collections.unmodifiableList(assessmentResultsList);

    /**
     * Constructs an AssessmentResultsList.
     *
     * @param tutName the TutorialName of the Tutorial that the AssessmentResultsList is contained in.
     */
    public AssessmentResultsList(TutorialName tutName) {
        requireNonNull(tutName);
        tutorialName = tutName;
    }

    /**
     * Returns true if assessmentResultsList contains the AssessmentResults.
     */
    public boolean contains(AssessmentResults toCheck) {
        requireNonNull(toCheck);
        return assessmentResultsList.stream().anyMatch(toCheck::isSameAssessmentResults);
    }

    /**
     * Returns true if assessmentResultsList contains an AssessmentResult with the given name.
     */
    public boolean hasAssessmentResultsByName(AssessmentName assessmentName) {
        requireNonNull(assessmentName);
        return assessmentResultsList.stream().anyMatch(x -> x.hasAssessmentName(assessmentName));
    }

    /**
     * Returns true if assessmentResultsList contains the result of a student with {@Code studentId} for
     * the assessment with {@Code assessmentName}.
     */
    public boolean hasStudentResult(AssessmentName assessmentName, NusNetId studentId) {
        requireAllNonNull(assessmentName, studentId);
        if (!hasAssessmentResultsByName(assessmentName)) {
            throw new AssessmentNotFoundException();
        }
        AssessmentResults assessmentResults = getAssessmentResultsByName(assessmentName);
        return assessmentResults.hasStudentResultByStudentId(studentId);
    }

    /**
     * Adds {@code result} to the AssessmentResults with {@code assessmentName}.
     */
    public void addStudentResult(AssessmentName assessmentName, StudentResult result) {
        requireAllNonNull(assessmentName, result);
        if (!hasAssessmentResultsByName(assessmentName)) {
            throw new AssessmentNotFoundException();
        }
        AssessmentResults assessmentResults = getAssessmentResultsByName(assessmentName);
        assessmentResults.add(result);
    }

    /**
     * Removes the {@code StudentResult} of the student with NusNetId matching {@code studentId} from all assessments.
     */
    public void removeStudentResult(NusNetId studentId) {
        requireNonNull(studentId);
        for (int i = 0; i < assessmentResultsList.size(); i++) {
            AssessmentResults assessmentResults = assessmentResultsList.get(i);
            assessmentResults.removeByStudentId(studentId);
        }
    }

    /**
     * Sets the result of the student with {@code studentId} for the assessment with {@code assessmentName} to
     * {@code score}.
     */
    public void setStudentResult(AssessmentName assessmentName, Name studentName, NusNetId studentId, Score score) {
        requireAllNonNull(assessmentName, studentId, score);
        if (!hasAssessmentResultsByName(assessmentName)) {
            throw new AssessmentNotFoundException();
        }
        if (!hasStudentResult(assessmentName, studentId)) {
            throw new StudentResultNotFoundException();
        }
        AssessmentResults assessmentResults = getAssessmentResultsByName(assessmentName);
        assessmentResults.set(studentName, studentId, score);
    }

    /**
     * Adds an AssessmentResults to the list.
     * The AssessmentResults must not already be in the list.
     */
    public void add(AssessmentResults toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssessmentException();
        }
        assessmentResultsList.add(toAdd);
    }

    /**
     * Returns the AssessmentResults with the given name from the list.
     */
    public AssessmentResults getAssessmentResultsByName(AssessmentName name) {
        requireNonNull(name);
        for (int i = 0; i < assessmentResultsList.size(); i++) {
            if (assessmentResultsList.get(i).hasAssessmentName(name)) {
                return assessmentResultsList.get(i);
            }
        }
        throw new AssessmentNotFoundException();
    }

    /**
     * Removes the AssessmentResults with the given name from the list.
     */
    public void removeAssessmentResultsByName(AssessmentName name) {
        requireNonNull(name);
        for (int i = 0; i < assessmentResultsList.size(); i++) {
            if (assessmentResultsList.get(i).hasAssessmentName(name)) {
                assessmentResultsList.remove(i);
                return;
            }
        }
        throw new AssessmentNotFoundException();
    }

    /**
     * Returns the backing list as an unmodifiable {@code List}.
     */
    public List<AssessmentResults> asUnmodifiableList() {
        return unmodifiableResultsList;
    }
}

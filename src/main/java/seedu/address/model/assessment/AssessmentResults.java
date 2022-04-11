package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assessment.exceptions.DuplicateStudentResultException;
import seedu.address.model.assessment.exceptions.StudentResultNotFoundException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;

/**
 * Contains a list of the students' results for a particular Assessment (found in UniqueAssessmentList).
 * The students are in the same Tutorial.
 * Has a whole-part relationship with StudentResult.
 *
 * TODO: implement remove and setStudentResult (for updating student's result)
 * @see seedu.address.model.person.UniquePersonList
 * @see StudentResult
 */
public class AssessmentResults {
    // Identity field
    private final AssessmentName assessmentName;

    // Data field
    private final ObservableList<StudentResult> results = FXCollections.observableArrayList();
    private final ObservableList<StudentResult> unmodifiableResults =
            FXCollections.unmodifiableObservableList(results);

    /**
     * Constructs an AssessmentResults.
     *
     * @param name name of the Assessment.
     */
    public AssessmentResults(AssessmentName name) {
        requireNonNull(name);
        assessmentName = name;
    }

    public AssessmentName getAssessmentName() {
        return assessmentName;
    }

    /**
     * Returns true if given AssessmentResults belong to the same assessment as this.
     * Used to check for duplicates when adding an assessment to the address book.
     * (unlikely to reach this as should check for duplicates in UniqueAssessmentList first before adding
     * AssessmentResultsList).
     */
    public boolean isSameAssessmentResults(AssessmentResults ar) {
        return this.assessmentName.name.equals(ar.assessmentName.name);
    }

    /**
     * Returns true if the AssessmentResults has given name.
     */
    public boolean hasAssessmentName(AssessmentName name) {
        return assessmentName.equals(name);
    }

    /**
     * Returns true if results list already contains the StudentResult.
     */
    public boolean contains(StudentResult toCheck) {
        requireNonNull(toCheck);
        return results.stream().anyMatch(toCheck::isSameStudentResult);
    }

    /**
     * Adds a StudentResult to the list.
     * The Student's result must not already be in the list.
     * Can redefine this to allow updating for results already in the list.
     */
    public void add(StudentResult toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStudentResultException();
        }
        results.add(toAdd);
    }

    /**
     * Removes the StudentResult with {@code studentId} from results.
     * @param studentId
     */
    public void removeByStudentId(NusNetId studentId) {
        requireNonNull(studentId);
        for (int i = 0; i < results.size(); i++) {
            StudentResult current = results.get(i);
            if (current.getStudentId().equals(studentId)) {
                results.remove(current);
                return;
            }
        }
    }

    /**
     * Sets the result of the student with {@code studentId} to {@code score}.
     */
    public void set(Name studentName, NusNetId studentId, Score score) {
        requireAllNonNull(studentName, studentId, score);
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getStudentId().equals(studentId)) {
                StudentResult studentResult = new StudentResult(studentName, studentId, score);
                results.set(i, studentResult);
                return;
            }
        }
        throw new StudentResultNotFoundException();
    }

    public ObservableList<StudentResult> asUnmodifiableStudentResultsList() {
        return unmodifiableResults;
    }

    /**
     * Returns true if {@code results} contains a StudentResult with {@code studentId}.
     */
    public boolean hasStudentResultByStudentId(NusNetId studentId) {
        requireNonNull(studentId);
        return results.stream().anyMatch(x -> x.getStudentId().equals(studentId));
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof AssessmentResults
                && assessmentName.equals(((AssessmentResults) other).assessmentName)
                && results.equals(((AssessmentResults) other).results));
    }

}

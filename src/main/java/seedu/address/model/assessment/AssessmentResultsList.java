package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assessment.exceptions.DuplicateAssessmentException;
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
    private final ObservableList<AssessmentResults> assessmentResultsList = FXCollections.observableArrayList();
    private final ObservableList<AssessmentResults> unmodifiableAssessmentResultsList =
            FXCollections.unmodifiableObservableList(assessmentResultsList);

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
     * Used to add an AssessmentResults to the list.
     * The AssessmentResults must not already be in the list.
     */
    public void add(AssessmentResults toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssessmentException();
        }
        assessmentResultsList.add(toAdd);
    }

}

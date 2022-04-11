package seedu.address.testutil;

import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Weightage;

/**
 * A utility class to help with building Assessment objects.
 */
public class AssessmentBuilder {

    public static final String DEFAULT_NAME = "Default Assessment";
    public static final String DEFAULT_WEIGHTAGE = "10";
    public static final String DEFAULT_FULL_MARK = "40";

    private AssessmentName assessmentName;
    private Weightage weightage;
    private FullMark fullMark;

    /**
     * Creates a {@code AssessmentBuilder} with the default details.
     */
    public AssessmentBuilder() {
        assessmentName = new AssessmentName(DEFAULT_NAME);
        weightage = new Weightage(DEFAULT_WEIGHTAGE);
        fullMark = new FullMark(DEFAULT_FULL_MARK);
    }

    /**
     * Initializes the AssessmentBuilder with the data of {@code assessmentToCopy}.
     */
    public AssessmentBuilder(Assessment assessmentToCopy) {
        assessmentName = assessmentToCopy.getAssessmentName();
        weightage = assessmentToCopy.getWeightage();
        fullMark = assessmentToCopy.getFullMark();
    }

    /**
     * Sets the {@code Name} of the {@code Assessment} that we are building.
     */
    public AssessmentBuilder withName(String assessmentName) {
        this.assessmentName = new AssessmentName(assessmentName);
        return this;
    }

    /**
     * Sets the {@code Weightage} of the {@code Assessment} that we are building.
     */
    public AssessmentBuilder withWeightage(String weightage) {
        this.weightage = new Weightage(weightage);
        return this;
    }

    /**
     * Sets the {@code Weightage} of the {@code Assessment} that we are building.
     */
    public AssessmentBuilder withFullMark(String fullMark) {
        this.fullMark = new FullMark(fullMark);
        return this;
    }

    public Assessment build() {
        return new Assessment(assessmentName, weightage, fullMark);
    }
}

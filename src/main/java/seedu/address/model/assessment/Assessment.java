package seedu.address.model.assessment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.DisplayType;
import seedu.address.model.Displayable;
/**
 * Represents an Assessment for all the tutorials in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Assessment implements Displayable {
    // Identity fields
    private static final DisplayType displayType = DisplayType.ASSESSMENT;
    private final AssessmentName name;

    // Data fields
    private final Weightage weightage;
    private final FullMark fullMark;

    /**
     * Every field must be present and not null.
     */
    public Assessment(AssessmentName name, Weightage weightage, FullMark fullMark) {
        requireAllNonNull(name, weightage, fullMark);
        this.name = name;
        this.weightage = weightage;
        this.fullMark = fullMark;
    }

    public AssessmentName getAssessmentName() {
        return name;
    }

    public Weightage getWeightage() {
        return weightage;
    }

    public FullMark getFullMark() {
        return fullMark;
    }

    @Override
    public DisplayType getDisplayType() {
        return displayType;
    }

    /**
     * Returns true if both assessments have the same name.
     * Example usage: check for duplicates.
     */
    public boolean isSameAssessment(Assessment other) {
        if (other == this) {
            return true;
        }
        return other != null && other.getAssessmentName().equals(getAssessmentName());
    }

    /**
     * Returns true if assessment has given name.
     */
    public boolean hasName(AssessmentName name) {
        return this.name.equals(name);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getAssessmentName())
                .append("; Weightage: ")
                .append(getWeightage())
                .append("; Full mark: ")
                .append(getFullMark());
        return stringBuilder.toString();
    }

    /**
     * Returns true if both assessments have the same identity and data fields.
     * Defines a stronger notion of equality between 2 assessments.
     * Example usage: deleting an Assessment from the UniqueAssessmentList
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Assessment)) {
            return false;
        }

        Assessment otherAssessment = (Assessment) other;
        return otherAssessment.getAssessmentName().equals(getAssessmentName())
                && otherAssessment.getFullMark().equals(getFullMark())
                && otherAssessment.getWeightage().equals(getWeightage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weightage, fullMark);
    }
}

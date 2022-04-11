package seedu.address.model.assessment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.DisplayType.SCORE;

import java.util.Objects;

import seedu.address.model.DisplayType;
import seedu.address.model.Displayable;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;

/**
 * Contains the score of a student for a particular Assessment in the address book.
 * Part of AssessmentResults, should not exist without an AssessmentResults object.
 *
 * @see AssessmentResults
 * @see Score
 */
public class StudentResult implements Displayable {
    // Identity field
    private final Name studentName;
    private final NusNetId studentId;

    // Data field
    private final Score score;

    /**
     * Constructs a StudentResult.
     *
     * @param id the student's NusNetId.
     * @param s the student's Score.
     */
    public StudentResult(Name name, NusNetId id, Score s) {
        requireAllNonNull(id, s);
        studentName = name;
        studentId = id;
        score = s;
    }

    public Name getStudentName() {
        return studentName;
    }

    public NusNetId getStudentId() {
        return studentId;
    }

    public Score getScore() {
        return score;
    }

    /**
     * Returns true if the two StudentResults have the same NusNetId.
     * Defines a weaker notion of equality between 2 StudentResults.
     * Used for checking of duplicates in {@link AssessmentResults}
     */
    public boolean isSameStudentResult(StudentResult other) {
        return this == other
                || (other != null
                && other.getStudentId().equals(getStudentId()));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getStudentId())
                .append("; Score: ")
                .append(getScore());
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        return this == o
                || (o instanceof StudentResult
                && studentName.equals(((StudentResult) o).studentName)
                && studentId.equals(((StudentResult) o).studentId)
                && score.equals(((StudentResult) o).score));
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, score);
    }

    @Override
    public DisplayType getDisplayType() {
        return SCORE;
    }
}

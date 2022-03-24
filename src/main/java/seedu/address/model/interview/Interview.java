package seedu.address.model.interview;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

import seedu.address.model.applicant.Applicant;
import seedu.address.model.position.Position;


/**
 * Represents an Interview in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interview {

    //Data fields
    private final Applicant applicant;
    private final LocalDateTime date;
    private final Position position;
    private final Status status;

    /**
     * Every field must be present and not null.
     */
    public Interview(Applicant applicant, LocalDateTime date, Position position) {
        requireAllNonNull(applicant, date);
        this.applicant = applicant;
        this.date = date;
        this.position = position;
        this.status = new Status();
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Position getPosition() {
        return position;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Checks if the interview is for the specified applicant.
     */
    public boolean isInterviewForApplicant(Applicant a) {
        return applicant.isSamePerson(a);
    }

    /**
     * Checks if the interview is for the specified position.
     */
    public boolean isInterviewForPosition(Position p) {
        return position.isSamePosition(p);
    }

    /**
     * Returns true if both interviews have the same data fields.
     * This defines a stronger notion of equality between two interviews.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Interview)) {
            return false;
        }

        Interview otherInterview = (Interview) other;
        return otherInterview.getApplicant().equals(getApplicant())
                && otherInterview.getDate().equals(getDate())
                && otherInterview.getPosition().equals(getPosition());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(applicant.getName())
                .append("; Date: ")
                .append(getDate())
                .append("; Position: ")
                .append(position.getPositionName())
                .append(getStatus());
        return builder.toString();
    }

}

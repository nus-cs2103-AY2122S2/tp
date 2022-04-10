package seedu.address.model.interview;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.applicant.Applicant;
import seedu.address.model.position.Position;


/**
 * Represents an Interview in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interview {

    //Data fields
    private Applicant applicant;
    private final LocalDateTime date;
    private Position position;
    private final Status status;

    /**
     * Every field must be present and not null.
     */
    public Interview(Applicant applicant, LocalDateTime date, Position position) {
        requireAllNonNull(applicant, date, position);
        this.applicant = applicant;
        this.date = date;
        this.position = position;
        this.status = new Status();
    }

    /**
     * Create Interview object when loading from database
     * Every field must be present and not null.
     */
    public Interview(Applicant applicant, LocalDateTime date, Position position, Status status) {
        requireAllNonNull(applicant, date, status, position);
        this.applicant = applicant;
        this.date = date;
        this.position = position;
        this.status = status;
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

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Checks if the interview is for the specified applicant.
     */
    public boolean isInterviewForApplicant(Applicant a) {
        return applicant.isSameApplicant(a);
    }

    /**
     * Checks if the interview is for the specified position.
     */
    public boolean isInterviewForPosition(Position p) {
        return position.isSamePosition(p);
    }

    /**
     * Checks if the given interview will conflict with the current interview.
     */
    public boolean isConflictingInterview(Interview i) {
        boolean isSameApplicant = i.isInterviewForApplicant(this.applicant);

        // Interview has to be at least 1 hour before or after the current interview time for it not to clash
        return isSameApplicant && !(i.date.isBefore(this.date.minusMinutes(59))
                        || i.date.isAfter(this.date.plusMinutes(59)));
    }

    /**
     * Checks if the given interview can be passed based on the number of offers given for its position.
     */
    public boolean isPassableInterview() {
        return this.position.canExtendOffer();
    }

    /**
     * Checks if the given interview has Pending status
     */
    public boolean isPendingStatus() {
        return status.isPendingStatus();
    }

    /**
     * Checks if the given interview can be passed based on the number of offers given for its position.
     */
    public boolean isAcceptableInterview() {
        return status.isPassedStatus() && this.position.canAcceptOffer();
    }

    /**
     * Checks if the current interview can be failed.
     */
    public boolean isFailableInterview() {
        return isPendingStatus();
    }

    /**
     * Checks if the given interview can be rejected based on the number of offers.
     */
    public boolean isRejectableInterview() {
        return status.isPassedStatus() && this.position.canRejectOffer();
    }



    /**
     * Marks an interview as passed and increments the position offering.
     */
    public void markAsPassed() {
        this.status.markAsPassed();
    }

    /**
     * Marks an interview as failed.
     */
    public void markAsFailed() {
        this.status.markAsFailed();
    }

    /**
     * Marks an interview as accepted.
     * The interview must already have been passed to be accepted.
     */
    public void markAsAccepted() {
        this.status.markAsAccepted();
    }

    /**
     * Marks an interview as rejected.
     * The interview must already have been passed to be rejected.
     */
    public void markAsRejected() {
        this.status.markAsRejected();
    }

    /**
     * Checks whether an interview is passed.
     */
    public boolean isPassedStatus() {
        return this.status.isPassedStatus();
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
                && otherInterview.getStatus().equals(getStatus())
                && otherInterview.getPosition().equals(getPosition());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(applicant.getName())
                .append("; Date: ")
                .append(getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .append("; Position: ")
                .append(position.getPositionName())
                .append("; Status: ")
                .append(getStatus());
        return builder.toString();
    }

    /**
     * Creates csv output for interview
     */
    public String convertToCsv() {
        String applicantCsv = this.applicant.convertToCsv();
        String positionCsv = this.position.convertToCsv();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return this.date.format(formatter) + "," + this.status + "," + applicantCsv + "," + positionCsv;
    }
}

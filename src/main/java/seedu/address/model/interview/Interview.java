package seedu.address.model.interview;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.model.person.Person;

/**
 * Represents an Interview in the interview schedule.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interview {
    private final Person candidate;
    private final LocalDateTime interviewDateTime;

    /**
     * Every field must be present and not null.
     */
    public Interview(Person candidate, LocalDateTime interviewDateTime) {
        requireAllNonNull(candidate, interviewDateTime);
        this.candidate = candidate;
        this.interviewDateTime = interviewDateTime;
    }

    /**
     * Returns true if both interviews have candidates with the same name.
     * This defines a weaker notion of equality between two interviews.
     */
    public boolean isSameInterview(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }
        return otherInterview != null
                && otherInterview.getCandidate().getName().equals(getCandidate().getName())
                && otherInterview.getInterviewDateTime().equals(getInterviewDateTime());
    }

    public boolean isConflictingInterview(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }
        return otherInterview != null
                && otherInterview.getInterviewDateTime().equals(getInterviewDateTime());
    }

    public Person getCandidate() {
        return this.candidate;
    }

    public LocalDateTime getInterviewDateTime() {
        return this.interviewDateTime;
    }

    public LocalTime getInterviewTime() {
        return this.interviewDateTime.toLocalTime();
    }

    public LocalDate getInterviewDate() {
        return this.interviewDateTime.toLocalDate();
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getStudentID().equals(getStudentID())
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getCourse().equals(getCourse())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public String toString() {
        return this.candidate.getName() + " " + this.candidate.getStudentID() + " "
                + this.getInterviewDate() + " " + this.getInterviewTime();
    }
}

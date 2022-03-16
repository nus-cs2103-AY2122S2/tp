package seedu.address.model.interview;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.model.person.Person;

public class Interview {
    private final Person candidate;
    private final LocalDateTime interviewDateTime;

    public Interview(Person candidate, LocalDateTime interviewDateTime) {
        this.candidate = candidate;
        this.interviewDateTime = interviewDateTime;
    }

    public boolean isSameInterview(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }
        return otherInterview != null
                && otherInterview.getCandidate().equals(getCandidate())
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

    @Override
    public String toString() {
        return this.candidate.getName() + " " + this.candidate.getStudentID() + " "
                + this.getInterviewDate() + " " + this.getInterviewTime();
    }
}

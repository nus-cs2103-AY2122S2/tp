package seedu.address.model.interview;

import java.time.LocalDateTime;

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

    public Person getCandidate() {
        return this.candidate;
    }

    public LocalDateTime getInterviewDateTime() {
        return this.interviewDateTime;
    }
}

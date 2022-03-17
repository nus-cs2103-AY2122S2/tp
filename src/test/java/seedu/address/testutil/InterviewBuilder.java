package seedu.address.testutil;

import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;

import java.time.LocalDateTime;

/**
 * A utility class to help with building Person objects.
 */
public class InterviewBuilder {

    private Person person;
    private LocalDateTime interviewDateTime;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public InterviewBuilder() {
        person = new PersonBuilder().build();
        interviewDateTime = LocalDateTime.now();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public InterviewBuilder(Interview interviewToCopy) {
        person = interviewToCopy.getCandidate();
        interviewDateTime = interviewToCopy.getInterviewDateTime();
    }

    /**
     * Sets the {@code StudentID} of the {@code Person} that we are building.
     */
    public InterviewBuilder withCandidate(Person p) {
        this.person = p;
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public InterviewBuilder withInterviewDateTime(LocalDateTime interviewDateTime) {
        this.interviewDateTime = interviewDateTime;
        return this;
    }

    public Interview build() {
        return new Interview(person, interviewDateTime);
    }

}

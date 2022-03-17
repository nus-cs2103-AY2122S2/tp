package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.InterviewSchedule;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalInterviews {
    public static final LocalDateTime TYPICAL_INTERVIEW_DATE_TIME = LocalDateTime.of(2022, Month.DECEMBER, 23, 10, 00);
    public static final Interview INTERVIEW_ALICE = new InterviewBuilder().withCandidate(TypicalPersons.ALICE)
            .withInterviewDateTime(TYPICAL_INTERVIEW_DATE_TIME).build();
    public static final Interview INTERVIEW_BENSON = new InterviewBuilder().withCandidate(TypicalPersons.BENSON)
            .withInterviewDateTime(TYPICAL_INTERVIEW_DATE_TIME).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Interview INTERVIEW_AMY_TYPICAL = new InterviewBuilder().withCandidate(TypicalPersons.AMY)
            .withInterviewDateTime(TYPICAL_INTERVIEW_DATE_TIME).build();
    public static final Interview INTERVIEW_BOB_TYPICAL = new InterviewBuilder().withCandidate(TypicalPersons.BOB)
            .withInterviewDateTime(TYPICAL_INTERVIEW_DATE_TIME).build();


    private TypicalInterviews() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static InterviewSchedule getTypicalInterviewSchedule() {
        InterviewSchedule schedule = new InterviewSchedule();
        for (Interview interview : getTypicalInterviews()) {
            schedule.addInterview(interview);
        }
        return schedule;
    }

    public static List<Interview> getTypicalInterviews() {
        return new ArrayList<>(Arrays.asList(INTERVIEW_ALICE, INTERVIEW_BENSON, INTERVIEW_AMY_TYPICAL,
                INTERVIEW_BOB_TYPICAL));
    }
}

package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCandidates.BOB;
import static seedu.address.testutil.TypicalInterviews.INTERVIEW_ALICE;
import static seedu.address.testutil.TypicalInterviews.INTERVIEW_AMY_TYPICAL;
import static seedu.address.testutil.TypicalInterviews.INTERVIEW_BENSON;
import static seedu.address.testutil.TypicalInterviews.INTERVIEW_BOB_TYPICAL;
import static seedu.address.testutil.TypicalInterviews.INTERVIEW_CARL;
import static seedu.address.testutil.TypicalInterviews.VALID_AMY_INTERVIEW_DATE_TIME;
import static seedu.address.testutil.TypicalInterviews.getTypicalInterviewSchedule;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.exceptions.ConflictingInterviewException;
import seedu.address.model.interview.exceptions.DuplicateCandidateException;
import seedu.address.testutil.InterviewBuilder;
import seedu.address.testutil.InterviewScheduleBuilder;

//@@author lzan98
public class InterviewScheduleTest {

    private final InterviewSchedule interviewSchedule = new InterviewSchedule();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), interviewSchedule.getInterviewList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> interviewSchedule.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInterviewSchedule_replacesData() {
        InterviewSchedule newData = getTypicalInterviewSchedule();
        interviewSchedule.resetData(newData);
        assertEquals(newData, interviewSchedule);
    }

    @Test
    public void resetData_withDuplicateInterviewCandidates_throwsDuplicateCandidateException() {
        // Two interviews with the same candidate
        Interview editedAliceInterview =
                new InterviewBuilder(INTERVIEW_ALICE).withInterviewDateTime(VALID_AMY_INTERVIEW_DATE_TIME)
                .build();
        List<Interview> newInterviews = Arrays.asList(INTERVIEW_ALICE, editedAliceInterview);
        InterviewScheduleStub newData = new InterviewScheduleStub(newInterviews);

        assertThrows(DuplicateCandidateException.class, () -> interviewSchedule.resetData(newData));
    }

    @Test
    public void resetData_withConflictingInterviews_throwsConflictingInterviewException() {
        // Two interviews with the same date and time
        Interview editedAliceInterview =
                new InterviewBuilder(INTERVIEW_ALICE).withInterviewDateTime(VALID_AMY_INTERVIEW_DATE_TIME)
                        .build();
        List<Interview> newInterviews = Arrays.asList(INTERVIEW_AMY_TYPICAL, editedAliceInterview);
        InterviewScheduleStub newData = new InterviewScheduleStub(newInterviews);
        assertThrows(ConflictingInterviewException.class, () -> interviewSchedule.resetData(newData));
    }

    @Test
    public void setInterviews_withDuplicateInterviewCandidates_throwsDuplicateCandidateException() {
        // Two interviews with the same candidate
        Interview editedAliceInterview =
                new InterviewBuilder(INTERVIEW_ALICE).withInterviewDateTime(VALID_AMY_INTERVIEW_DATE_TIME)
                        .build();
        List<Interview> newInterviews = Arrays.asList(INTERVIEW_ALICE, editedAliceInterview);
        assertThrows(DuplicateCandidateException.class, () -> new InterviewSchedule().setInterviews(newInterviews));
    }

    @Test
    public void hasCandidate_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> interviewSchedule.hasCandidate(null));
    }

    @Test
    public void hasConflictingInterview_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> interviewSchedule.hasConflictingInterview(null));
    }

    @Test
    public void hasCandidate_interviewNotInInterviewSchedule_returnsFalse() {
        assertFalse(interviewSchedule.hasCandidate(INTERVIEW_ALICE));
    }

    @Test
    public void hasConflictingInterview_interviewNotInInterviewSchedule_returnsFalse() {
        assertFalse(interviewSchedule.hasConflictingInterview(INTERVIEW_ALICE));
    }

    @Test
    public void hasCandidate_interviewInInterviewSchedule_returnsTrue() {
        interviewSchedule.addInterview(INTERVIEW_ALICE);
        assertTrue(interviewSchedule.hasCandidate(INTERVIEW_ALICE));
    }

    @Test
    public void hasConflictingInterview_interviewInInterviewSchedule_returnsTrue() {
        interviewSchedule.addInterview(INTERVIEW_AMY_TYPICAL);
        assertTrue(interviewSchedule.hasConflictingInterview(INTERVIEW_BOB_TYPICAL));
    }

    @Test
    public void hasCandidate_interviewWithSameCandidateInInterviewSchedule_returnsTrue() {
        interviewSchedule.addInterview(INTERVIEW_ALICE);
        Interview editedAliceInterview = new InterviewBuilder(INTERVIEW_ALICE)
                .withInterviewDateTime(VALID_AMY_INTERVIEW_DATE_TIME).build();
        assertTrue(interviewSchedule.hasCandidate(editedAliceInterview));
    }

    @Test
    public void hasConflictingInterview_interviewWithSameInterviewDateTimeInInterviewSchedule_returnsTrue() {
        interviewSchedule.addInterview(INTERVIEW_ALICE);
        Interview editedAliceInterview = new InterviewBuilder(INTERVIEW_ALICE)
                .withCandidate(BOB).build();
        assertTrue(interviewSchedule.hasConflictingInterview(editedAliceInterview));
    }

    @Test
    public void removeInterviewSuccess() {
        Interview interviewAlice = new InterviewBuilder(INTERVIEW_ALICE)
                .withInterviewDateTime(VALID_AMY_INTERVIEW_DATE_TIME).build();
        InterviewSchedule schedule = new InterviewSchedule();
        schedule.addInterview(interviewAlice);
        schedule.removeInterview(interviewAlice);

        assertEquals(schedule, interviewSchedule);
    }

    @Test
    public void deletePastInterviews_withPastInterviews() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Interview pastInterview = new InterviewBuilder(INTERVIEW_BENSON)
                .withInterviewDateTime(currentDateTime).build();
        InterviewSchedule schedule = new InterviewSchedule();
        schedule.addInterview(pastInterview);
        LocalDateTime dateTimeThirtyOneMinutesIntoFuture = currentDateTime.plusMinutes(31);
        schedule.deletePastInterviews(dateTimeThirtyOneMinutesIntoFuture);

        assertEquals(schedule, interviewSchedule);
    }

    @Test
    public void deletePastInterviews_withNoPastInterviews() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        InterviewSchedule schedule = new InterviewScheduleBuilder().withInterview(INTERVIEW_BENSON).build();
        interviewSchedule.addInterview(INTERVIEW_BENSON);
        schedule.deletePastInterviews(currentDateTime);

        assertEquals(schedule, interviewSchedule);
    }

    @Test
    public void deletePastInterviews_withPastAndFutureInterviews() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Interview pastInterview = new InterviewBuilder(INTERVIEW_CARL).withInterviewDateTime(currentDateTime).build();
        InterviewSchedule schedule = new InterviewScheduleBuilder()
                .withInterview(INTERVIEW_BENSON).withInterview(pastInterview).build();
        interviewSchedule.addInterview(INTERVIEW_BENSON);
        LocalDateTime dateTimeThirtyOneMinutesIntoFuture = currentDateTime.plusMinutes(31);
        schedule.deletePastInterviews(dateTimeThirtyOneMinutesIntoFuture);

        assertEquals(schedule, interviewSchedule);
    }

    /**
     * A stub ReadOnlyInterviewSchedule whose candidates list can violate interface constraints.
     */
    private static class InterviewScheduleStub implements ReadOnlyInterviewSchedule {
        private final ObservableList<Interview> interviews = FXCollections.observableArrayList();

        InterviewScheduleStub(Collection<Interview> interviews) {
            this.interviews.setAll(interviews);
        }

        @Override
        public ObservableList<Interview> getInterviewList() {
            return interviews;
        }
    }

}

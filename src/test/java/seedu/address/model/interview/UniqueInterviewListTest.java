package seedu.address.model.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInterviews.INTERVIEW_ALICE;
import static seedu.address.testutil.TypicalInterviews.INTERVIEW_AMY_TYPICAL;
import static seedu.address.testutil.TypicalInterviews.INTERVIEW_BENSON;
import static seedu.address.testutil.TypicalInterviews.INTERVIEW_BOB_TYPICAL;
import static seedu.address.testutil.TypicalInterviews.INTERVIEW_CARL;
import static seedu.address.testutil.TypicalInterviews.INTERVIEW_NO_MATCHING_AVAILABILITY;
import static seedu.address.testutil.TypicalInterviews.TYPICAL_INTERVIEW_DATE_TIME;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.interview.exceptions.ConflictingInterviewException;
import seedu.address.model.interview.exceptions.DuplicateCandidateException;
import seedu.address.model.interview.exceptions.InterviewNotFoundException;
import seedu.address.testutil.InterviewBuilder;


public class UniqueInterviewListTest {

    private final UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();

    @Test
    public void containsCandidate_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueInterviewList.containsCandidate(null));
    }

    @Test
    public void containsConflictingInterview_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueInterviewList.containsConflictingInterview(null));
    }

    @Test
    public void containsCandidate_interviewNotInList_returnsFalse() {
        assertFalse(uniqueInterviewList.containsCandidate(INTERVIEW_ALICE));
    }

    @Test
    public void containsConflictingInterview_interviewNotInList_returnsFalse() {
        assertFalse(uniqueInterviewList.containsConflictingInterview(INTERVIEW_ALICE));
    }

    @Test
    public void containsCandidate_interviewInList_returnsTrue() {
        uniqueInterviewList.add(INTERVIEW_ALICE);
        assertTrue(uniqueInterviewList.containsCandidate(INTERVIEW_ALICE));
    }

    @Test
    public void containsConflictingInterview_interviewInList_returnsTrue() {
        uniqueInterviewList.add(INTERVIEW_AMY_TYPICAL);
        assertTrue(uniqueInterviewList.containsConflictingInterview(INTERVIEW_AMY_TYPICAL));
    }

    @Test
    public void containsCandidate_interviewWithSameCandidateInList_returnsTrue() {
        uniqueInterviewList.add(INTERVIEW_ALICE);
        Interview editedAliceInterview = new InterviewBuilder(INTERVIEW_ALICE)
                .withInterviewDateTime(TYPICAL_INTERVIEW_DATE_TIME).build();
        assertTrue(uniqueInterviewList.containsCandidate(editedAliceInterview));
    }

    @Test
    public void containsConflictingInterview_interviewWithSameDateTimeInList_returnsTrue() {
        uniqueInterviewList.add(INTERVIEW_AMY_TYPICAL);
        assertTrue(uniqueInterviewList.containsConflictingInterview(INTERVIEW_BOB_TYPICAL));
    }

    @Test
    public void add_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.add(null));
    }

    @Test
    public void add_duplicateInterviewCandidate_throwsDuplicateCandidateException() {
        uniqueInterviewList.add(INTERVIEW_ALICE);
        assertThrows(DuplicateCandidateException.class, () -> uniqueInterviewList.add(INTERVIEW_ALICE));
    }

    @Test
    public void add_conflictingInterviewDateTime_throwsConflictingInterviewException() {
        uniqueInterviewList.add(INTERVIEW_ALICE);
        assertThrows(ConflictingInterviewException.class, () -> uniqueInterviewList.add(INTERVIEW_CARL));
    }

    @Test
    public void setInterview_nullTargetInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.setInterview(null, INTERVIEW_ALICE));
    }

    @Test
    public void setInterview_nullEditedInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.setInterview(INTERVIEW_ALICE, null));
    }

    @Test
    public void setInterview_targetInterviewNotInList_throwsInterviewNotFoundException() {
        assertThrows(InterviewNotFoundException.class, () ->
                uniqueInterviewList.setInterview(INTERVIEW_ALICE, INTERVIEW_ALICE));
    }

    @Test
    public void setInterview_hasSameCandidate_throwsDuplicateCandidateException() {
        uniqueInterviewList.add(INTERVIEW_ALICE);
        assertThrows(DuplicateCandidateException.class, () ->
                uniqueInterviewList.setInterview(INTERVIEW_ALICE, INTERVIEW_ALICE));
    }

    @Test
    public void setInterview_hasConflictingInterview_throwsConflictingInterviewException() {
        uniqueInterviewList.add(INTERVIEW_ALICE);
        uniqueInterviewList.add(INTERVIEW_AMY_TYPICAL);
        assertThrows(ConflictingInterviewException.class, () ->
                uniqueInterviewList.setInterview(INTERVIEW_ALICE, INTERVIEW_BOB_TYPICAL));
    }

    @Test
    public void setInterviews_nulluniqueInterviewList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.setInterviews((UniqueInterviewList) null));
    }

    @Test
    public void setInterviews_uniqueInterviewList_replacesOwnListWithProvidedUniqueInterviewList() {
        uniqueInterviewList.add(INTERVIEW_ALICE);
        UniqueInterviewList expectedUniqueInterviewList = new UniqueInterviewList();
        expectedUniqueInterviewList.add(INTERVIEW_BENSON);
        uniqueInterviewList.setInterviews(expectedUniqueInterviewList);
        assertEquals(expectedUniqueInterviewList, uniqueInterviewList);
    }

    @Test
    public void setInterviews_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.setInterviews((List<Interview>) null));
    }

    @Test
    public void setInterviews_list_replacesOwnListWithProvidedList() {
        uniqueInterviewList.add(INTERVIEW_ALICE);
        List<Interview> interviewList = Collections.singletonList(INTERVIEW_BENSON);
        uniqueInterviewList.setInterviews(interviewList);
        UniqueInterviewList expectedUniqueInterviewList = new UniqueInterviewList();
        expectedUniqueInterviewList.add(INTERVIEW_BENSON);
        assertEquals(expectedUniqueInterviewList, uniqueInterviewList);
    }

    @Test
    public void setInterviews_listWithDuplicateInterviewCandidates_throwsDuplicateCandidateException() {
        List<Interview> listWithDuplicateCandidates = Arrays.asList(INTERVIEW_ALICE, INTERVIEW_ALICE);
        assertThrows(DuplicateCandidateException.class, () ->
                uniqueInterviewList.setInterviews(listWithDuplicateCandidates));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueInterviewList.asUnmodifiableObservableList().remove(0));
    }
}

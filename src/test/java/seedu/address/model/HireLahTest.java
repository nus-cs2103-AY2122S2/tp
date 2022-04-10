package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalHireLah.getTypicalHireLah;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.exceptions.DuplicateApplicantException;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;
import seedu.address.testutil.ApplicantBuilder;

public class HireLahTest {

    private final HireLah hireLah = new HireLah();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), hireLah.getApplicantList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hireLah.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyHireLah_replacesData() {
        HireLah newData = getTypicalHireLah();
        hireLah.resetData(newData);
        assertEquals(newData, hireLah);
    }

    @Test
    public void resetData_withDuplicateApplicants_throwsDuplicateApplicantException() {
        // Two applicants with the same identity fields
        Applicant editedAlice = new ApplicantBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Applicant> newApplicants = Arrays.asList(ALICE, editedAlice);
        HireLahStub newData = new HireLahStub(newApplicants);

        assertThrows(DuplicateApplicantException.class, () -> hireLah.resetData(newData));
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hireLah.hasApplicant(null));
    }

    @Test
    public void hasApplicant_applicantNotInHireLah_returnsFalse() {
        assertFalse(hireLah.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantInHireLah_returnsTrue() {
        hireLah.addApplicant(ALICE);
        assertTrue(hireLah.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantWithSameIdentityFieldsInHireLah_returnsTrue() {
        hireLah.addApplicant(ALICE);
        Applicant editedAlice = new ApplicantBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(hireLah.hasApplicant(editedAlice));
    }

    @Test
    public void getApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> hireLah.getApplicantList().remove(0));
    }

    /**
     * A stub ReadOnlyHireLah whose applicants list can violate interface constraints.
     */
    private static class HireLahStub implements ReadOnlyHireLah {
        private final ObservableList<Applicant> applicants = FXCollections.observableArrayList();
        private final ObservableList<Interview> interviews = FXCollections.observableArrayList();
        private final ObservableList<Position> positions = FXCollections.observableArrayList();

        HireLahStub(Collection<Applicant> applicants) {
            this.applicants.setAll(applicants);
            // Add positions to constructor for unit testing
        }

        @Override
        public ObservableList<Applicant> getApplicantList() {
            return applicants;
        }

        @Override
        public ObservableList<Interview> getInterviewList() {
            return interviews;
        }

        @Override
        public ObservableList<Position> getPositionList() {
            return positions;
        }
    }

}

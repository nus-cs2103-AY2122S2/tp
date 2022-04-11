package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GARENA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.GRAB;
import static seedu.address.testutil.TypicalApplications.getTypicalInternApplyMemory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.sort.NameComparator;
import seedu.address.model.application.Application;
import seedu.address.model.application.exceptions.DuplicateApplicationException;
import seedu.address.testutil.ApplicationBuilder;

public class InternApplyMemoryTest {

    private final InternApplyMemory internApplyMemory = new InternApplyMemory();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internApplyMemory.getApplicationList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internApplyMemory.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInternApplyMemory_replacesData() {
        InternApplyMemory newData = getTypicalInternApplyMemory();
        internApplyMemory.resetData(newData);
        assertEquals(newData, internApplyMemory);
    }

    @Test
    public void resetData_withDuplicateApplications_throwsDuplicateApplicationException() {
        // Two applications with the same identity fields
        Application editedGrab = new ApplicationBuilder(GRAB)
                .withAddress(VALID_ADDRESS_GARENA).withPhone(VALID_PHONE_GARENA)
                .build();
        List<Application> newApplications = Arrays.asList(GRAB, editedGrab);
        InternApplyMemoryStub newData = new InternApplyMemoryStub(newApplications);

        assertThrows(DuplicateApplicationException.class, () -> internApplyMemory.resetData(newData));
    }

    @Test
    public void hasApplication_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internApplyMemory.hasApplication(null));
    }

    @Test
    public void hasApplication_applicationNotInInternApplyMemory_returnsFalse() {
        assertFalse(internApplyMemory.hasApplication(GRAB));
    }

    @Test
    public void hasApplication_applicationInInternApplyMemory_returnsTrue() {
        internApplyMemory.addApplication(GRAB);
        assertTrue(internApplyMemory.hasApplication(GRAB));
    }

    @Test
    public void hasApplication_applicationWithSameIdentityFieldsInInternApplyMemory_returnsTrue() {
        internApplyMemory.addApplication(GRAB);
        Application editedGrab = new ApplicationBuilder(GRAB)
                .withAddress(VALID_ADDRESS_GARENA).withPhone(VALID_PHONE_GARENA)
                .build();
        assertTrue(internApplyMemory.hasApplication(editedGrab));
    }

    @Test
    public void getApplicationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internApplyMemory.getApplicationList().remove(0));
    }

    /**
     * A stub ReadOnlyInternApplyMemory whose applications list can violate interface constraints.
     */
    private static class InternApplyMemoryStub implements ReadOnlyInternApplyMemory {
        private final ObservableList<Application> applications = FXCollections.observableArrayList();

        InternApplyMemoryStub(Collection<Application> applications) {
            this.applications.setAll(applications);
        }

        @Override
        public ObservableList<Application> getApplicationList() {
            return applications;
        }
    }

    @Test
    public void sortApplication_nullPointerException() {
        assertThrows(NullPointerException.class, () -> internApplyMemory.sortApplications(new NameComparator(), null));
        assertThrows(NullPointerException.class, () -> internApplyMemory.sortApplications(null,
                ListCommand.COMMAND_ORDER_WORD_ASCENDING));
    }

    @Test
    public void sortApplication_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internApplyMemory.sortApplications(new NameComparator(),
                "abc"));
    }
}

package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;
import static seedu.contax.testutil.TypicalPersons.ALICE;
import static seedu.contax.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.GuiSettings;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.contax.model.person.NameContainsKeywordsPredicate;
import seedu.contax.testutil.AddressBookBuilder;
import seedu.contax.testutil.AppointmentBuilder;
import seedu.contax.testutil.ScheduleBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void setScheduleFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setScheduleFilePath(null));
    }

    @Test
    public void setScheduleFilePath_validPath_setsScheduleFilePath() {
        Path path = Paths.get("address/schedule/file/path");
        modelManager.setScheduleFilePath(path);
        assertEquals(path, modelManager.getScheduleFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void setSchedule_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSchedule(null));
    }

    @Test
    public void getSchedule() {
        modelManager.addAppointment(APPOINTMENT_ALICE);
        assertEquals(1, modelManager.getSchedule().getAppointmentList().size());
    }

    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAppointment(null));
    }

    @Test
    public void hasAppointment_appointmentNotInSchedule_returnsFalse() {
        assertFalse(modelManager.hasAppointment(APPOINTMENT_ALICE));
    }

    @Test
    public void hasAppointment_appointmentInSchedule_returnsTrue() {
        modelManager.addAppointment(APPOINTMENT_ALICE);
        assertTrue(modelManager.hasAppointment(APPOINTMENT_ALICE));
    }

    @Test
    public void hasOverlappingAppointment_duplicatedAppointmentInList_returnsTrue() {
        modelManager.addAppointment(APPOINTMENT_ALICE);
        Appointment duplicate = new AppointmentBuilder(APPOINTMENT_ALICE).build();
        assertTrue(modelManager.hasOverlappingAppointment(duplicate));
    }

    @Test
    public void hasOverlappingAppointment_disjointAppointmentsInList_returnsFalse() {
        modelManager.addAppointment(APPOINTMENT_ALICE);
        assertFalse(modelManager.hasOverlappingAppointment(APPOINTMENT_ALONE));
    }

    @Test
    public void setAppointment_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAppointment(null, APPOINTMENT_ALICE));
        assertThrows(NullPointerException.class, () -> modelManager.setAppointment(APPOINTMENT_ALICE, null));
        assertThrows(NullPointerException.class, () -> modelManager.setAppointment(null, null));
    }

    @Test
    public void setAppointment_targetNotInModel_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, ()
            -> modelManager.setAppointment(APPOINTMENT_ALICE, APPOINTMENT_ALONE));
    }

    @Test
    public void setAppointment_sameAppointment_success() {
        modelManager.addAppointment(APPOINTMENT_ALICE);
        modelManager.setAppointment(APPOINTMENT_ALICE, APPOINTMENT_ALICE);

        Schedule expectedSchedule = new ScheduleBuilder().withAppointment(APPOINTMENT_ALICE).build();
        assertEquals(expectedSchedule, modelManager.getSchedule());
    }

    @Test
    public void setAppointment_differentAppointment_success() {
        modelManager.addAppointment(APPOINTMENT_ALICE);
        modelManager.setAppointment(APPOINTMENT_ALICE, APPOINTMENT_ALONE);

        Schedule expectedSchedule = new ScheduleBuilder().withAppointment(APPOINTMENT_ALONE).build();
        assertEquals(expectedSchedule, modelManager.getSchedule());
    }

    @Test
    public void deleteAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteAppointment(null));
    }

    @Test
    public void deleteAppointment_appointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, ()
            -> modelManager.deleteAppointment(APPOINTMENT_ALICE));
    }

    @Test
    public void deleteAppointment_appointmentInList_success() {
        modelManager.addAppointment(APPOINTMENT_ALONE);
        modelManager.deleteAppointment(APPOINTMENT_ALONE);
        assertEquals(new ModelManager(), modelManager);
    }


    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        Schedule schedule = new ScheduleBuilder().withAppointment(APPOINTMENT_ALICE).build();
        Schedule differentSchedule = new Schedule();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, schedule, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, schedule, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, schedule, userPrefs)));

        // different schedule -> returns false
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentSchedule, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, schedule, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, schedule, differentUserPrefs)));
    }
}

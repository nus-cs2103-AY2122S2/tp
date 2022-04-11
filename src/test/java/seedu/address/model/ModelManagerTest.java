package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalMeetings.WITH_ALICE;
import static seedu.address.testutil.TypicalMeetings.WITH_BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.model.meeting.exceptions.OverlappingMeetingsException;
import seedu.address.testutil.AddressBookBuilder;

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
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInAddressBook_returnsTrue() {
        modelManager.addClient(ALICE);
        assertTrue(modelManager.hasClient(ALICE));
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClientList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withClient(ALICE).withClient(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }

    @Test
    public void addMeeting() {
        // add null meeting
        assertThrows(NullPointerException.class, () -> modelManager.addMeeting(null));

        // add valid meeting
        modelManager.addMeeting(WITH_ALICE);
        assertEquals(modelManager.getFilteredMeetingList().size(), 1);
        assertEquals(modelManager.getFilteredMeetingList().get(0), WITH_ALICE);

        // add overlapping meeting
        assertThrows(OverlappingMeetingsException.class, () -> modelManager.addMeeting(WITH_ALICE));
    }

    @Test
    public void setMeeting() {
        // set null meeting
        assertThrows(NullPointerException.class, () -> modelManager.setMeeting(null, WITH_ALICE));
        assertThrows(NullPointerException.class, () -> modelManager.setMeeting(WITH_ALICE, null));
        assertThrows(NullPointerException.class, () -> modelManager.setMeeting(null, null));

        // set invalid meeting
        assertThrows(MeetingNotFoundException.class, () -> modelManager.setMeeting(WITH_ALICE, WITH_BENSON));

        modelManager.addMeeting(WITH_ALICE);

        // set meeting success
        modelManager.setMeeting(WITH_ALICE, WITH_BENSON);
        assertEquals(modelManager.getFilteredMeetingList().size(), 1);
        assertEquals(modelManager.getFilteredMeetingList().get(0), WITH_BENSON);
    }

    @Test
    public void isOverlapping() {

        modelManager.addMeeting(WITH_ALICE);

        // null meeting
        assertThrows(NullPointerException.class, () -> modelManager.isOverlapping(null));

        // overlapping meeting
        assertTrue(modelManager.isOverlapping(WITH_ALICE));

        // non-overlapping meeting
        assertFalse(modelManager.isOverlapping(WITH_BENSON));
    }

    @Test
    public void isOverlappingExcept() {
        modelManager.addMeeting(WITH_ALICE);

        // null meeting
        assertThrows(NullPointerException.class, () -> modelManager.isOverlappingExcept(null, WITH_ALICE));

        // null excepted meeting
        assertThrows(NullPointerException.class, () -> modelManager.isOverlappingExcept(WITH_ALICE, null));

        // both meetings null
        assertThrows(NullPointerException.class, () -> modelManager.isOverlappingExcept(null, null));

        // except overlapping meeting
        assertFalse(modelManager.isOverlappingExcept(WITH_ALICE, WITH_ALICE));

        // except non-overlapping meeting
        assertTrue(modelManager.isOverlappingExcept(WITH_ALICE, WITH_BENSON));
    }
}

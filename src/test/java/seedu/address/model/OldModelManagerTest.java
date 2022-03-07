package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.OldModel.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
/*
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

 */

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;

public class OldModelManagerTest {
/*
    private OldModelManager oldModelManager = new OldModelManager();

    @Test
    public void constructor() {
        assertEquals(new OldUserPrefs(), oldModelManager.getUserPrefs());
        assertEquals(new GuiSettings(), oldModelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(oldModelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> oldModelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        OldUserPrefs userPrefs = new OldUserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        oldModelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, oldModelManager.getUserPrefs());

        // Modifying userPrefs should not modify oldModelManager's userPrefs
        OldUserPrefs oldUserPrefs = new OldUserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, oldModelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> oldModelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        oldModelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, oldModelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> oldModelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        oldModelManager.setAddressBookFilePath(path);
        assertEquals(path, oldModelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> oldModelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(oldModelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        oldModelManager.addPerson(ALICE);
        assertTrue(oldModelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> oldModelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        OldUserPrefs userPrefs = new OldUserPrefs();

        // same values -> returns true
        oldModelManager = new OldModelManager(addressBook, userPrefs);
        OldModelManager oldModelManagerCopy = new OldModelManager(addressBook, userPrefs);
        assertTrue(oldModelManager.equals(oldModelManagerCopy));

        // same object -> returns true
        assertTrue(oldModelManager.equals(oldModelManager));

        // null -> returns false
        assertFalse(oldModelManager.equals(null));

        // different types -> returns false
        assertFalse(oldModelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(oldModelManager.equals(new OldModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        oldModelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(oldModelManager.equals(new OldModelManager(addressBook, userPrefs)));

        // resets oldModelManager to initial state for upcoming tests
        oldModelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        OldUserPrefs differentUserPrefs = new OldUserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(oldModelManager.equals(new OldModelManager(addressBook, differentUserPrefs)));
    }

 */
}

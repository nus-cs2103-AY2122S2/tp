package manageezpz.model;

import static manageezpz.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static manageezpz.testutil.Assert.assertThrows;
import static manageezpz.testutil.TypicalPersons.ALICE;
import static manageezpz.testutil.TypicalPersons.BENSON;
import static manageezpz.testutil.TypicalTasks.GET_DRINK;
import static manageezpz.testutil.TypicalTasks.HOUSE_VISTING;
import static manageezpz.testutil.TypicalTasks.READ_BOOK;
import static manageezpz.testutil.TypicalTasks.RETURN_BOOK;
import static manageezpz.testutil.TypicalTasks.WEEKLY_QUIZ;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import manageezpz.commons.core.GuiSettings;
import manageezpz.model.person.NameContainsKeywordsPredicate;
import manageezpz.testutil.AddressBookBuilder;

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
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
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
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }

    //==================== Test for ManageEZPZ (Tasks)=============================

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasTask(READ_BOOK));
    }

    @Test
    public void hasTask_taskInAddressBook_returnsTrue() {
        modelManager.addTask(RETURN_BOOK);
        assertTrue(modelManager.hasTask(RETURN_BOOK));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void hasDeadline_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasDeadline(null));
    }

    @Test
    public void hasEvent_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEvent(null));
    }

    @Test
    public void hasTodo_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTodo(null));
    }

    @Test
    public void hasDeadline_taskInAddressBook_returnsTrue() {
        modelManager.addDeadline(GET_DRINK);
        assertTrue(modelManager.hasDeadline(GET_DRINK));
    }

    @Test
    public void hasEvent_taskInAddressBook_returnsTrue() {
        modelManager.addEvent(HOUSE_VISTING);
        assertTrue(modelManager.hasEvent(HOUSE_VISTING));
    }

    @Test
    public void hasTodo_taskInAddressBook_returnsTrue() {
        modelManager.addTodo(WEEKLY_QUIZ);
        assertTrue(modelManager.hasTodo(WEEKLY_QUIZ));
    }

    @Test
    public void deleteTask_taskInAddressBook_returnsTrue() {
        modelManager.addTask(READ_BOOK);
        modelManager.deleteTask(READ_BOOK);
        assertTrue(!modelManager.hasTask(READ_BOOK));
    }

    @Test
    public void markTask_taskInaddressBook_returnsTrue() {
        modelManager.addTask(RETURN_BOOK);
        modelManager.markTask(RETURN_BOOK);
        assertTrue(RETURN_BOOK.isDone());
    }

    @Test
    public void unmarkTask_taskInaddressBook_returnsTrue() {
        modelManager.addTask(RETURN_BOOK);
        modelManager.unmarkTask(READ_BOOK);
        assertTrue(!READ_BOOK.isDone());
    }
}

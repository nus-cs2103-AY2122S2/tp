package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.UniqueModuleList;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.tasks.PriorityTaskList;
import seedu.address.testutil.ContactListBuilder;
import seedu.address.testutil.ContentBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ContactList(), new ContactList(modelManager.getContactList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setContactListFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setContactListFilePath(Paths.get("new/address/book/file/path"));
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
    public void setContactListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setContactListFilePath(null));
    }

    @Test
    public void setContactListFilePath_validPath_setsContactListFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setContactListFilePath(path);
        assertEquals(path, modelManager.getContactListFilePath());
    }

    @Test
    public void setTaskListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTaskListFilePath(null));
    }

    @Test
    public void setTaskListFilePath_validPath_setsContactListFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setContactListFilePath(path);
        assertEquals(path, modelManager.getContactListFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInContactList_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInContactList_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    //=========== VersionedContent tests ==============================================================

    @Test
    public void undoContents() {
        modelManager.addPerson(ALICE);
        modelManager.commitContent();
        modelManager.undoContents();

        assertEquals(modelManager.getCurrentContent(), new Content(new ContactList(), new PriorityTaskList(),
                new UniqueModuleList()));
    }

    @Test
    public void redoContents() {
        modelManager.addPerson(ALICE);
        modelManager.commitContent();
        modelManager.undoContents();
        modelManager.redoContents();

        ContactList contactListWithAlice = new ContactListBuilder().withPerson(ALICE).build();
        assertEquals(modelManager.getCurrentContent(), new Content(contactListWithAlice, new PriorityTaskList(),
                new UniqueModuleList()));
    }

    @Test
    public void getVersionedContents() {
        Content initialContents = new ContentBuilder().build();
        assertEquals(modelManager.getVersionedContents(), new VersionedContents(initialContents));
    }

    @Test
    public void getCurrentContent() {
        assertEquals(modelManager.getCurrentContent(), new Content(new ContactList(), new PriorityTaskList(),
                new UniqueModuleList()));
    }

    @Test
    public void commitContent() {
        VersionedContents initVersionedContents = new VersionedContents(modelManager.getVersionedContents());
        modelManager.addPerson(ALICE);
        modelManager.commitContent();
        VersionedContents newVersionedContents = new VersionedContents(modelManager.getVersionedContents());

        assertNotEquals(initVersionedContents, newVersionedContents);
        Content newContent = newVersionedContents.getCurrentContent();
        initVersionedContents.addContentVersion(newContent);
        assertEquals(initVersionedContents, newVersionedContents);
    }

    @Test
    public void canUndo() {
        assertFalse(modelManager.canUndo());

        modelManager.addPerson(ALICE);
        modelManager.commitContent();
        assertTrue(modelManager.canUndo());
    }

    @Test
    public void canRedo() {
        assertFalse(modelManager.canRedo());

        modelManager.addPerson(ALICE);
        modelManager.commitContent();
        assertFalse(modelManager.canRedo());

        modelManager.undoContents();
        assertTrue(modelManager.canRedo());
    }


    //=========== Other tests ================================================================================

    @Test
    public void equals() {
        ContactList contactList = new ContactListBuilder().withPerson(ALICE).withPerson(BENSON).build();
        ContactList differentContactList = new ContactList();
        UserPrefs userPrefs = new UserPrefs();
        PriorityTaskList taskList = new PriorityTaskList();

        // same values -> returns true
        modelManager = new ModelManager(contactList, userPrefs, taskList);
        ModelManager modelManagerCopy = new ModelManager(contactList, userPrefs, taskList);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different contactList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentContactList, userPrefs, taskList)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(contactList, userPrefs, taskList)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setContactListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(contactList, differentUserPrefs, taskList)));

        // different task lists -> returns false
        PriorityTaskList diffTaskList = new PriorityTaskList();
        diffTaskList.add(ASSIGNMENT);
        assertFalse(modelManager.equals(new ModelManager(contactList, userPrefs, diffTaskList)));
    }

}

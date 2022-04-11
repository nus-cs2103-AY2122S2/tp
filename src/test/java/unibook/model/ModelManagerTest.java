package unibook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unibook.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import unibook.commons.core.GuiSettings;
import unibook.model.person.NameContainsKeywordsPredicate;
import unibook.testutil.Assert;
import unibook.testutil.UniBookBuilder;
import unibook.testutil.typicalclasses.TypicalStudents;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new UniBook(), new UniBook(modelManager.getUniBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setUniBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setUniBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setUniBookFilePath_nullPath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setUniBookFilePath(null));
    }

    @Test
    public void setUniBookFilePath_validPath_setsUniBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setUniBookFilePath(path);
        assertEquals(path, modelManager.getUniBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInUniBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(TypicalStudents.ALICE));
    }

    @Test
    public void hasPerson_personInUniBook_returnsTrue() {
        modelManager.addPerson(TypicalStudents.ALICE);
        assertTrue(modelManager.hasPerson(TypicalStudents.ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        UniBook uniBook =
            new UniBookBuilder().withPerson(TypicalStudents.ALICE).withPerson(TypicalStudents.BENSON).build();
        UniBook differentUniBook = new UniBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(uniBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(uniBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different uniBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentUniBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = TypicalStudents.ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(uniBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setUniBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(uniBook, differentUserPrefs)));
    }
}

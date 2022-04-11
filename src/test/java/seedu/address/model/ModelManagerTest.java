package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.testutil.TAssistBuilder;
import seedu.address.testutil.TypicalClassGroups;
import seedu.address.testutil.TypicalModules;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TAssist(), new TAssist(modelManager.getTAssist()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTAssistFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTAssistFilePath(Paths.get("new/address/book/file/path"));
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
    public void setTAssistFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTAssistFilePath(null));
    }

    @Test
    public void setTAssistFilePath_validPath_setsTAssistFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTAssistFilePath(path);
        assertEquals(path, modelManager.getTAssistFilePath());
    }

    @Test
    public void hasEntity_nullEntity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEntity(null));
    }

    @Test
    public void hasStudent_studentNotInTAssist_returnsFalse() {
        assertFalse(modelManager.hasEntity(ALICE));
    }

    @Test
    public void hasStudent_studentInTAssist_returnsTrue() {
        modelManager.addEntity(ALICE);
        assertTrue(modelManager.hasEntity(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void hasClassGroup_classGroupNotInTAssist_returnsFalse() {
        assertFalse(modelManager.hasEntity(TypicalClassGroups.CS2101G09));
    }

    @Test
    public void hasClassGroup_classGroupInTAssist_returnsTrue() {
        modelManager.addEntity(TypicalClassGroups.CS2101G09);
        assertTrue(modelManager.hasEntity(TypicalClassGroups.CS2101G09));
    }

    @Test
    public void getFilteredClassGroupList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClassGroupList().remove(0));
    }

    @Test
    public void getFilteredModulesList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void hasModule_moduleNotInTAssist_returnsFalse() {
        assertFalse(modelManager.hasEntity(TypicalModules.CS2101));
    }

    @Test
    public void hasModule_moduleInTAssist_returnsTrue() {
        modelManager.addEntity(TypicalModules.CS2101);
        assertTrue(modelManager.hasEntity(TypicalModules.CS2101));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void equals() {
        TAssist tassist = new TAssistBuilder().withStudent(ALICE).withStudent(BENSON).build();
        TAssist differentTAssist = new TAssist();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(tassist, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(tassist, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different tassist -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTAssist, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(tassist, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL);

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredClassGroupList(PREDICATE_SHOW_ALL);

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ALL);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTAssistFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(tassist, differentUserPrefs)));
    }
}

package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.GRAB;
import static seedu.address.testutil.TypicalApplications.LAZADA;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.sort.NameComparator;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.testutil.InternApplyMemoryBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new InternApplyMemory(), new InternApplyMemory(modelManager.getInternApplyMemory()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setInternApplyMemoryFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setInternApplyMemoryFilePath(Paths.get("new/address/book/file/path"));
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
    public void setInternApplyMemoryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setInternApplyMemoryFilePath(null));
    }

    @Test
    public void setInternApplyMemoryFilePath_validPath_setsInternApplyMemoryFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setInternApplyMemoryFilePath(path);
        assertEquals(path, modelManager.getInternApplyMemoryFilePath());
    }

    @Test
    public void hasApplication_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasApplication(null));
    }

    @Test
    public void hasApplication_applicationNotInInternApplyMemory_returnsFalse() {
        assertFalse(modelManager.hasApplication(GRAB));
    }

    @Test
    public void hasApplication_applicationInInternApplyMemory_returnsTrue() {
        modelManager.addApplication(GRAB);
        assertTrue(modelManager.hasApplication(GRAB));
    }

    @Test
    public void getFilteredApplicationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredApplicationList().remove(0));
    }

    @Test
    public void getUpcomingApplicationsList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getUpcomingApplicationList().remove(0));
    }

    @Test
    public void sortApplication_nullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.sortApplications(new NameComparator(), null));
        assertThrows(NullPointerException.class, () -> modelManager.sortApplications(null,
                ListCommand.COMMAND_ORDER_WORD_ASCENDING));
    }

    @Test
    public void sortApplication_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.sortApplications(new NameComparator(),
                "abc"));
    }

    @Test
    public void equals() {
        InternApplyMemory internApplyMemory = new InternApplyMemoryBuilder().withApplication(GRAB)
                .withApplication(LAZADA).build();
        InternApplyMemory differentInternApplyMemory = new InternApplyMemory();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(internApplyMemory, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(internApplyMemory, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different internApplyMemory -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentInternApplyMemory, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = GRAB.getName().fullName.split("\\s+");
        modelManager.updateFilteredApplicationList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(internApplyMemory, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setInternApplyMemoryFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(internApplyMemory, differentUserPrefs)));
    }
}

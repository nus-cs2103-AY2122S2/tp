package seedu.trackermon.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.model.Model.PREDICATE_SHOW_ALL_SHOWS;
import static seedu.trackermon.testutil.Assert.assertThrows;
import static seedu.trackermon.testutil.TypicalShows.ALICE_IN_WONDERLAND;
import static seedu.trackermon.testutil.TypicalShows.HIMYM;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.trackermon.commons.core.GuiSettings;
import seedu.trackermon.model.show.NameContainsKeywordsPredicate;
import seedu.trackermon.testutil.ShowListBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code ModelManager}.
 */
public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    /**
     * Tests the constructor of {@code ModelManager}.
     */
    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ShowList(), new ShowList(modelManager.getShowList()));
    }

    /**
     * Tests exception thrown when setting a null {@code UserPrefs}.
     */
    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    /**
     * Tests setting of a valid {@code UserPrefs}.
     */
    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setShowListFilePath(Paths.get("show/list/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setShowListFilePath(Paths.get("new/show/list/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    /**
     * Tests exception thrown when setting a null {@code UserPrefs}.
     */
    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    /**
     * Tests setting of a valid {@code GuiSettings}.
     */
    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    /**
     * Tests exception thrown when setting a null showListFilePath.
     */
    @Test
    public void setShowListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setShowListFilePath(null));
    }

    /**
     * Tests setting of a valid showListFilePath.
     */
    @Test
    public void setShowListFilePath_validPath_setsShowListFilePath() {
        Path path = Paths.get("show/list/file/path");
        modelManager.setShowListFilePath(path);
        assertEquals(path, modelManager.getShowListFilePath());
    }

    /**
     * Tests exception thrown when checking for a null {@code Show}.
     */
    @Test
    public void hasShow_nullShow_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasShow(null));
    }

    /**
     * Tests the {@code Show} is not in the show list.
     */
    @Test
    public void hasShow_showNotInShowList_returnsFalse() {
        assertFalse(modelManager.hasShow(ALICE_IN_WONDERLAND));
    }

    /**
     * Tests the {@code Show} is in the show list.
     */
    @Test
    public void hasShow_showInShowList_returnsTrue() {
        modelManager.addShow(ALICE_IN_WONDERLAND);
        assertTrue(modelManager.hasShow(ALICE_IN_WONDERLAND));
    }

    /**
     * Tests exception thrown when removing in the FilterShowList.
     */
    @Test
    public void getFilteredShowList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredShowList().remove(0));
    }

    /**
     * Test methods to test out the interaction of {@code ModelManager} objects.
     */
    @Test
    public void equals() {
        ShowList showList = new ShowListBuilder().withShow(ALICE_IN_WONDERLAND)
                .withShow(HIMYM).build();
        ShowList differentShowList = new ShowList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(showList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(showList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different showList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentShowList, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE_IN_WONDERLAND.getName().name.split("\\s+");
        modelManager.updateFilteredShowList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(showList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredShowList(PREDICATE_SHOW_ALL_SHOWS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setShowListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(showList, differentUserPrefs)));
    }
}

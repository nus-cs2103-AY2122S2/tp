package seedu.trackermon.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.model.Model.PREDICATE_SHOW_ALL_SHOWS;
import static seedu.trackermon.testutil.Assert.assertThrows;
import static seedu.trackermon.testutil.TypicalShows.ALICE_IN_WONDERLAND;
import static seedu.trackermon.testutil.TypicalShows.HIMYM;
import static seedu.trackermon.testutil.TypicalShows.WEATHERING_WITH_YOU;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.trackermon.commons.core.GuiSettings;
import seedu.trackermon.model.show.NameContainsKeywordsPredicate;
import seedu.trackermon.testutil.ShowListBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ShowList(), new ShowList(modelManager.getShowList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

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
    public void setShowListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setShowListFilePath(null));
    }

    @Test
    public void setShowListFilePath_validPath_setsShowListFilePath() {
        Path path = Paths.get("show/list/file/path");
        modelManager.setShowListFilePath(path);
        assertEquals(path, modelManager.getShowListFilePath());
    }

    @Test
    public void hasShow_nullShow_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasShow(null));
    }

    @Test
    public void hasPerson_showNotInShowList_returnsFalse() {
        assertFalse(modelManager.hasShow(ALICE_IN_WONDERLAND));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addShow(ALICE_IN_WONDERLAND);
        assertTrue(modelManager.hasShow(ALICE_IN_WONDERLAND));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredShowList().remove(0));
    }

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
        String[] keywords = ALICE_IN_WONDERLAND.getName().fullName.split("\\s+");
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

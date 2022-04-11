package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typical.TypicalMeetings.CS2105;
import static seedu.address.testutil.typical.TypicalMeetings.CS2106;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.meeting.MeetingContainsKeywordsPredicate;
import seedu.address.testutil.LinkyTimeBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new LinkyTime(), new LinkyTime(modelManager.getLinkyTime()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        final UserPrefs userPrefs = new UserPrefs();
        userPrefs.setLinkyTimeFilePath(Paths.get("linkyTime/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        final UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setLinkyTimeFilePath(Paths.get("new/linkyTime/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        final GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setLinkyTimeFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setLinkyTimeFilePath(null));
    }

    @Test
    public void setLinkyTimeFilePath_validPath_setsLinkyTimeFilePath() {
        final Path path = Paths.get("linkyTime/file/path");
        modelManager.setLinkyTimeFilePath(path);
        assertEquals(path, modelManager.getLinkyTimeFilePath());
    }

    @Test
    public void hasMeeting_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasMeeting(null));
    }

    @Test
    public void hasMeeting_meetingNotInLinkyTime_returnsFalse() {
        assertFalse(modelManager.hasMeeting(CS2105));
    }

    @Test
    public void hasMeeting_meetingInLinkyTime_returnsTrue() {
        modelManager.addMeeting(CS2105);
        assertTrue(modelManager.hasMeeting(CS2105));
    }

    @Test
    public void getFilteredMeetingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredMeetingList().remove(0));
    }

    @Test
    public void equals() {
        final LinkyTime linkyTime = new LinkyTimeBuilder().withMeeting(CS2105).withMeeting(CS2106).build();
        final LinkyTime differentLinkyTime = new LinkyTime();
        final UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(linkyTime, userPrefs);
        final ModelManager modelManagerCopy = new ModelManager(linkyTime, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different linkyTime -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentLinkyTime, userPrefs)));

        // different filteredList -> returns false
        final ArrayList<String> keywords = new ArrayList<>();
        keywords.add(CS2105.getModule().toString());
        modelManager.updateFilteredMeetingList(new MeetingContainsKeywordsPredicate(keywords));
        assertFalse(modelManager.equals(new ModelManager(linkyTime, userPrefs)));

        // TODO: Check if this is still needed after the implementation of invariant filtering for meetings.
        // resets modelManager to initial state for upcoming tests
        // modelManager.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);

        // different userPrefs -> returns false
        final UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setLinkyTimeFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(linkyTime, differentUserPrefs)));
    }
}

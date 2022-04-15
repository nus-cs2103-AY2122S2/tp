package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.CS2102_MIDTERM;
import static seedu.address.testutil.TypicalTasks.CS2105_MIDTERM;
import static seedu.address.testutil.TypicalTasks.CS2107_ASSIGNMENT;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.keyword.Keyword;
import seedu.address.model.task.NameContainsKeywordsPredicate;
import seedu.address.testutil.TaskListBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TaskList(), new TaskList(modelManager.getTaskList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTaskListFilePath(Paths.get("task/list/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTaskListFilePath(Paths.get("new/task/list/file/path"));
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
    public void setTaskListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTaskListFilePath(null));
    }

    @Test
    public void setTaskListFilePath_validPath_setsTaskListFilePath() {
        Path path = Paths.get("task/list/file/path");
        modelManager.setTaskListFilePath(path);
        assertEquals(path, modelManager.getTaskListFilePath());
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskList_returnsFalse() {
        assertFalse(modelManager.hasTask(CS2105_MIDTERM));
    }

    @Test
    public void hasTask_taskInTaskList_returnsTrue() {
        modelManager.addTask(CS2105_MIDTERM);
        assertTrue(modelManager.hasTask(CS2105_MIDTERM));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void equals() {
        TaskList taskList = new TaskListBuilder().withTask(CS2105_MIDTERM).withTask(CS2102_MIDTERM).build();
        TaskList differentTaskList = new TaskList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(taskList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(taskList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different taskList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTaskList, userPrefs)));

        // different filteredList -> returns false
        String[] splitWords = CS2107_ASSIGNMENT.getName().fullName.split("\\s+");
        Set<Keyword> keywordSet = new HashSet<>();
        for (String s : splitWords) {
            keywordSet.add(new Keyword(s));
        }
        modelManager.updateFilteredTaskList(new NameContainsKeywordsPredicate(keywordSet));
        assertFalse(modelManager.equals(new ModelManager(taskList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTaskListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(taskList, differentUserPrefs)));
    }
}

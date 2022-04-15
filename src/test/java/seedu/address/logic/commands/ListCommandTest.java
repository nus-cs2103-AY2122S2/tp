package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTagList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskListWithNoTags;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TagList;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaskList(), new UserPrefs());
        expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
    }

    @Test
    public void execute_listTasksNonFilteredTaskList_showsSameTaskList() {
        assertCommandSuccess(new ListCommand(false), model,
                ListCommand.LIST_TASKS_MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listTasksFilteredTaskList_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        assertCommandSuccess(new ListCommand(false), model,
                ListCommand.LIST_TASKS_MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listTagsNonEmptyTaskList_showsTagSameTaskList() {
        TagList tagList = getTypicalTagList();
        assertCommandSuccess(new ListCommand(true), model,
                String.format(ListCommand.LIST_TAGS_MESSAGE_SUCCESS, tagList), expectedModel);
    }

    @Test
    public void execute_noTagsEmptyTaskList_showsNoTagsEmptyTaskList() {
        model = new ModelManager(new TaskList(), new UserPrefs());
        expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        assertCommandSuccess(new ListCommand(true), model,
                ListCommand.LIST_TAGS_NO_TAGS_FOUND_MESSAGE, expectedModel);
    }

    @Test
    public void execute_noTagsNonEmptyTaskList_showsNoTagsSameTaskList() {
        model = new ModelManager(getTypicalTaskListWithNoTags(), new UserPrefs());
        expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        assertCommandSuccess(new ListCommand(true), model,
                ListCommand.LIST_TAGS_NO_TAGS_FOUND_MESSAGE, expectedModel);
    }
}

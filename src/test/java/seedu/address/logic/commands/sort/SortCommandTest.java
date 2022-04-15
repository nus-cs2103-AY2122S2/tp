package seedu.address.logic.commands.sort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskWithKeywords;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskListForSort;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {

    private Model model = new ModelManager(getTypicalTaskListForSort(), new UserPrefs());

    @Test
    public void equals() {

        // Sort Key: Deadline and Sort Order: Ascending
        SortCommand sortFirstCommand = new SortCommand(SortKey.DEADLINE, SortOrder.ASCENDING);
        // Sort Key: Priority and Sort Order: Descending
        SortCommand sortSecondCommand = new SortCommand(SortKey.PRIORITY, SortOrder.DESCENDING);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy =
                new SortCommand(SortKey.DEADLINE, SortOrder.ASCENDING);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different sort command -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }

    @Test
    public void execute_unfilteredList_success() {
        // Sort Key: Deadline and Sort Order: Ascending
        SortKey sortKey = SortKey.DEADLINE;
        SortOrder sortOrder = SortOrder.ASCENDING;

        SortCommand sortCommand = new SortCommand(sortKey, sortOrder);
        String expectedMessage = String.format(String.format(Messages.MESSAGE_TASKS_SORTED,
                model.getSortedTaskList().size(), sortKey, sortOrder));
        Model expectedModel = new ModelManager(new TaskList(model.getTaskList()), new UserPrefs());

        Comparator<Task> comparatorDeadlineAscending =
                Comparator.comparing(Task::getDeadline).thenComparing(Task::getName);
        expectedModel.updateSortedTaskList(comparatorDeadlineAscending);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unfilteredListMultipleSort_success() {
        // Sort Key: Deadline and Sort Order: Ascending
        SortKey firstSortKey = SortKey.DEADLINE;
        SortOrder firstSortOrder = SortOrder.ASCENDING;

        SortCommand firstSortCommand = new SortCommand(firstSortKey, firstSortOrder);
        Model expectedModel = new ModelManager(new TaskList(model.getTaskList()), new UserPrefs());

        Comparator<Task> firstComparator =
                Comparator.comparing(Task::getDeadline).thenComparing(Task::getName);
        expectedModel.updateSortedTaskList(firstComparator);

        // Sort Key: Name and Sort Order: Descending
        SortKey secondSortKey = SortKey.NAME;
        SortOrder secondSortOrder = SortOrder.DESCENDING;
        SortCommand secondSortCommand = new SortCommand(secondSortKey, secondSortOrder);

        String expectedMessage = String.format(String.format(Messages.MESSAGE_TASKS_SORTED,
                model.getSortedTaskList().size(), secondSortKey, secondSortOrder));

        Comparator<Task> secondComparator =
                Comparator.comparing(Task::getName).thenComparing(Task::getDeadline).reversed();
        expectedModel.updateSortedTaskList(secondComparator);
        assertCommandSuccess(secondSortCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_filteredList_success() {
        Model expectedModel = new ModelManager(new TaskList(model.getTaskList()), new UserPrefs());

        String filterKeyword = "KEYWORD";
        showTaskWithKeywords(model, filterKeyword);
        showTaskWithKeywords(expectedModel, filterKeyword);

        // Assertion to check whether is more than one task
        assertTrue(1 < model.getTaskList().getTaskList().size());
        assertTrue(1 < expectedModel.getTaskList().getTaskList().size());

        // Sort Key: Deadline and Sort Order: Ascending
        SortKey sortKey = SortKey.DEADLINE;
        SortOrder sortOrder = SortOrder.ASCENDING;

        SortCommand sortCommand = new SortCommand(sortKey, sortOrder);
        String expectedMessage = String.format(String.format(Messages.MESSAGE_TASKS_SORTED,
                model.getSortedTaskList().size(), sortKey, sortOrder));

        Comparator<Task> comparatorDeadlineAscending =
                Comparator.comparing(Task::getDeadline).thenComparing(Task::getName);
        expectedModel.updateSortedTaskList(comparatorDeadlineAscending);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredListMultipleSort_success() {
        Model expectedModel = new ModelManager(new TaskList(model.getTaskList()), new UserPrefs());

        String filterKeyword = "KEYWORD";
        showTaskWithKeywords(model, filterKeyword);
        showTaskWithKeywords(expectedModel, filterKeyword);

        // Assertion to check whether is more than one task
        assertTrue(1 < model.getTaskList().getTaskList().size());
        assertTrue(1 < expectedModel.getTaskList().getTaskList().size());

        // Sort Key: Deadline and Sort Order: Ascending
        SortKey firstSortKey = SortKey.DEADLINE;
        SortOrder firstSortOrder = SortOrder.ASCENDING;

        SortCommand firstSortCommand = new SortCommand(firstSortKey, firstSortOrder);

        Comparator<Task> firstComparator =
                Comparator.comparing(Task::getDeadline).thenComparing(Task::getName);
        expectedModel.updateSortedTaskList(firstComparator);

        // Sort Key: Name and Sort Order: Descending
        SortKey secondSortKey = SortKey.NAME;
        SortOrder secondSortOrder = SortOrder.DESCENDING;
        SortCommand secondSortCommand = new SortCommand(secondSortKey, secondSortOrder);

        String expectedMessage = String.format(String.format(Messages.MESSAGE_TASKS_SORTED,
                model.getSortedTaskList().size(), secondSortKey, secondSortOrder));

        Comparator<Task> secondComparator =
                Comparator.comparing(Task::getName).thenComparing(Task::getDeadline).reversed();
        expectedModel.updateSortedTaskList(secondComparator);
        assertCommandSuccess(secondSortCommand, model, expectedMessage, expectedModel);
    }
}

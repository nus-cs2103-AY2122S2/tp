package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TASK;
import static manageezpz.testutil.TypicalTasks.READ_BOOK;
import static manageezpz.testutil.TypicalTasks.RETURN_BOOK;
import static manageezpz.testutil.TypicalTasks.getTypicalAddressBook;
import static manageezpz.testutil.TypicalTasks.getTypicalTask;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.task.Task;
import manageezpz.model.task.TaskMultiplePredicate;

class FindTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void equals() {
        TaskMultiplePredicate firstPredicate =
                new TaskMultiplePredicate(PREFIX_TASK, Collections.singletonList("Genshin"),
                        null, null, null, null);
        TaskMultiplePredicate secondPredicate =
                new TaskMultiplePredicate(PREFIX_TASK, Collections.singletonList("Impact"),
                        null, null, null, null);

        FindTaskCommand firstFindTaskCommand = new FindTaskCommand(firstPredicate);
        FindTaskCommand secondFindTaskCommand = new FindTaskCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstFindTaskCommand.equals(firstFindTaskCommand));

        // same predicate -> returns true
        FindTaskCommand copyFirstFindTaskCommand = new FindTaskCommand(firstPredicate);
        assertTrue(firstFindTaskCommand.equals(copyFirstFindTaskCommand));

        // Different types -> returns false
        assertFalse(firstFindTaskCommand.equals("predicate"));

        // null -> returns false
        assertFalse(firstFindTaskCommand.equals(null));

        // Different description -> return false
        assertFalse(firstFindTaskCommand.equals(secondFindTaskCommand));
    }

    @Test
    void findAllTasks() {
        TaskMultiplePredicate predicate = new TaskMultiplePredicate(PREFIX_TASK, null, null,
                null, null, null);
        expectedModel.updateFilteredTaskList(predicate);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW,
                expectedModel.getFilteredTaskList().size());
        FindTaskCommand command = new FindTaskCommand(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalTask(), model.getFilteredTaskList());
    }

    @Test
    void findTaskWithDescription() {
        List<String> keywords = List.of("Book");
        TaskMultiplePredicate predicate = new TaskMultiplePredicate(PREFIX_TASK, keywords, null, null,
                null, null);
        expectedModel.updateFilteredTaskList(predicate);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW,
                expectedModel.getFilteredTaskList().size());
        List<Task> expectedTasks = List.of(READ_BOOK, RETURN_BOOK);
        FindTaskCommand command = new FindTaskCommand(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedTasks, model.getFilteredTaskList());
    }

    // TODO: More testing needed
}

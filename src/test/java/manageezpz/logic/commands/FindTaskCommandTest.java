package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;
import static manageezpz.testutil.TypicalPersons.ALICE;
import static manageezpz.testutil.TypicalTasks.FYP_REPORT;
import static manageezpz.testutil.TypicalTasks.GET_A_DRINK;
import static manageezpz.testutil.TypicalTasks.GET_HAIRCUT;
import static manageezpz.testutil.TypicalTasks.GO_FOR_RUN;
import static manageezpz.testutil.TypicalTasks.HOUSE_VISTING;
import static manageezpz.testutil.TypicalTasks.MALAYSIA_BORDERS_OPEN;
import static manageezpz.testutil.TypicalTasks.PROJECT_CAPSTONE;
import static manageezpz.testutil.TypicalTasks.READ_BOOK;
import static manageezpz.testutil.TypicalTasks.RETURN_BOOK;
import static manageezpz.testutil.TypicalTasks.WEEKLY_QUIZ;
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
import manageezpz.model.task.Date;
import manageezpz.model.task.Priority;
import manageezpz.model.task.Task;
import manageezpz.model.task.TaskMultiplePredicate;

class FindTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void equals() {
        TaskMultiplePredicate firstPredicate =
                new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, Collections.singletonList("Genshin"),
                        null, null, null, null);
        TaskMultiplePredicate secondPredicate =
                new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, Collections.singletonList("Impact"),
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
    void findCommand_findAllTasks_showAllTasks() {
        TaskMultiplePredicate predicate = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, null, null);
        expectedModel.updateFilteredTaskList(predicate);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW,
                expectedModel.getFilteredTaskList().size());
        FindTaskCommand command = new FindTaskCommand(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalTask(), model.getFilteredTaskList());
    }

    @Test
    void findCommand_findSpecificTaskType_showTaskOfSpecificType() {
        TaskMultiplePredicate predicate = new TaskMultiplePredicate(List.of(PREFIX_TODO), null, null,
                null, null, null);
        expectedModel.updateFilteredTaskList(predicate);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 6);
        FindTaskCommand command = new FindTaskCommand(predicate);
        List<Task> expectedTasks = List.of(WEEKLY_QUIZ, READ_BOOK, RETURN_BOOK, GO_FOR_RUN, GET_HAIRCUT, GET_A_DRINK);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedTasks, model.getFilteredTaskList());
    }

    @Test
    void findCommand_findTaskWithDescription_showTasksWithGivenDescrription() {
        List<String> keywords = List.of("Book");
        TaskMultiplePredicate predicate = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                keywords, null, null, null, null);
        expectedModel.updateFilteredTaskList(predicate);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        List<Task> expectedTasks = List.of(READ_BOOK, RETURN_BOOK);
        FindTaskCommand command = new FindTaskCommand(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedTasks, model.getFilteredTaskList());
    }

    @Test
    void findCommand_findTaskWithDate_showTasksWithGivenDate() {
        Date date = new Date("2022-04-01");
        TaskMultiplePredicate predicate = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, date, null, null, null);
        expectedModel.updateFilteredTaskList(predicate);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        List<Task> expectedTasks = List.of(PROJECT_CAPSTONE, MALAYSIA_BORDERS_OPEN);
        FindTaskCommand command = new FindTaskCommand(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedTasks, model.getFilteredTaskList());
    }

    @Test
    void findCommand_findTaskWithPriority_showTasksWithGivenPriority() {
        TaskMultiplePredicate predicate = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, Priority.HIGH, null, null);
        expectedModel.updateFilteredTaskList(predicate);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        List<Task> expectedTasks = List.of(WEEKLY_QUIZ, PROJECT_CAPSTONE, FYP_REPORT);
        FindTaskCommand command = new FindTaskCommand(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedTasks, model.getFilteredTaskList());
    }

    @Test
    void findCommand_findTaskWithAssignee_showTasksWithGivenPriority() {
        TaskMultiplePredicate predicate = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, ALICE.getName().fullName, null);
        expectedModel.updateFilteredTaskList(predicate);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 4);
        List<Task> expectedTasks = List.of(PROJECT_CAPSTONE, FYP_REPORT, RETURN_BOOK, HOUSE_VISTING);
        FindTaskCommand command = new FindTaskCommand(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedTasks, model.getFilteredTaskList());
    }

    @Test
    void findCommand_findTaskWithIsMarked_showsTasksWithGivenIsMarked() {
        TaskMultiplePredicate predicate = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, null, Boolean.TRUE);
        expectedModel.updateFilteredTaskList(predicate);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        List<Task> expectedTasks = List.of(PROJECT_CAPSTONE, RETURN_BOOK);
        FindTaskCommand command = new FindTaskCommand(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedTasks, model.getFilteredTaskList());
    }

    @Test
    void findCommand_findTaskWithMultipleProperties_showTasksWithGivenProperties() {
        List<String> keywords = List.of("Capstone");
        Date date = new Date("2022-04-01");
        TaskMultiplePredicate predicate = new TaskMultiplePredicate(List.of(PREFIX_DEADLINE), keywords, date,
                Priority.HIGH, ALICE.getName().toString(), Boolean.TRUE);
        expectedModel.updateFilteredTaskList(predicate);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        List<Task> expectedTasks = List.of(PROJECT_CAPSTONE);
        FindTaskCommand command = new FindTaskCommand(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedTasks, model.getFilteredTaskList());
    }
}

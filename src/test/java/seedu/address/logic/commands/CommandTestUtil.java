package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_KEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.keyword.Keyword;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskList;
import seedu.address.model.task.NameContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_TUTORIAL = "Complete Tutorial";
    public static final String VALID_NAME_MIDTERM = "Midterm Test";
    public static final String VALID_DESCRIPTION_TUTORIAL = "Week 9 Tutorial, draw OODM & activity diagram";
    public static final String VALID_DESCRIPTION_MIDTERM = "Chapter 1-3";
    public static final String VALID_DEADLINE_TUTORIAL = "2022-03-17";
    public static final String VALID_DEADLINE_MIDTERM = "2022-03-21";
    public static final String VALID_COMPLETION_STATUS_TRUE = "true";
    public static final String VALID_COMPLETION_STATUS_FALSE = "false";
    public static final String VALID_PRIORITY_LOW = "low";
    public static final String VALID_PRIORITY_MEDIUM = "medium";
    public static final String VALID_TAG_CS2103T = "CS2103T";
    public static final String VALID_TAG_CS2102 = "CS2102";
    public static final String VALID_TAG_TEST = "Test";
    public static final String VALID_SORT_ORDER_ASCENDING = "asc";
    public static final String VALID_SORT_ORDER_DESCENDING = "desc";
    public static final String VALID_SORT_KEY_DEADLINE = "deadline";
    public static final String VALID_SORT_KEY_PRIORITY = "priority";

    public static final String NAME_DESC_TUTORIAL = " " + PREFIX_NAME + VALID_NAME_TUTORIAL;
    public static final String NAME_DESC_MIDTERM = " " + PREFIX_NAME + VALID_NAME_MIDTERM;
    public static final String DESCRIPTION_DESC_TUTORIAL = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_TUTORIAL;
    public static final String DESCRIPTION_DESC_MIDTERM = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MIDTERM;
    public static final String DEADLINE_DESC_TUTORIAL = " " + PREFIX_DEADLINE + VALID_DEADLINE_TUTORIAL;
    public static final String DEADLINE_DESC_MIDTERM = " " + PREFIX_DEADLINE + VALID_DEADLINE_MIDTERM;
    public static final String PRIORITY_LOW = " " + PREFIX_PRIORITY + VALID_PRIORITY_LOW;
    public static final String PRIORITY_MEDIUM = " " + PREFIX_PRIORITY + VALID_PRIORITY_MEDIUM;
    public static final String TAG_DESC_CS2103T = " " + PREFIX_TAG + VALID_TAG_CS2103T;
    public static final String TAG_DESC_CS2102 = " " + PREFIX_TAG + VALID_TAG_CS2102;
    public static final String TAG_DESC_TEST = " " + PREFIX_TAG + VALID_TAG_TEST;
    public static final String SORT_ORDER_DESC_ASCENDING = " " + PREFIX_SORT_ORDER + VALID_SORT_ORDER_ASCENDING;
    public static final String SORT_ORDER_DESC_DESCENDING = " " + PREFIX_SORT_ORDER + VALID_SORT_ORDER_DESCENDING;
    public static final String SORT_KEY_DESC_DEADLINE = " " + PREFIX_SORT_KEY + VALID_SORT_KEY_DEADLINE;
    public static final String SORT_KEY_DESC_PRIORITY = " " + PREFIX_SORT_KEY + VALID_SORT_KEY_PRIORITY;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME; // empty string not allowed for names
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + "Lorem ipsum dolor sit amet, "
            + "consectetur adipiscing elit. Sed metus diam, egestas sed ante pulvinar, fermentum dignissim arcu. "
            + "Donec pellentesque massa vel dolor blandit imperdiet. In bibendum justo urna, vitae venenatis "
            + "magna dictum quis. Proin erat curae."; // description should not be more than 255 characters
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "2022/01/01"; // wrong date format
    public static final String INVALID_PRIORITY = " " + PREFIX_PRIORITY + "highest"; // invalid priority enum
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG
            + "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbn"; // tag should not be more than 50 characters
    public static final String INVALID_SORT_ORDER_DESC = " " + PREFIX_SORT_ORDER + "lowest"; // invalid sort order enum
    public static final String INVALID_SORT_KEY_DESC = " " + PREFIX_SORT_KEY + "description"; // invalid sort key enum

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_TUTORIAL;
    public static final EditCommand.EditTaskDescriptor DESC_MIDTERM;

    static {
        DESC_TUTORIAL = new EditTaskDescriptorBuilder().withName(VALID_NAME_TUTORIAL)
                .withDescription(VALID_DESCRIPTION_TUTORIAL).withDeadline(VALID_DEADLINE_TUTORIAL)
                .withPriority(VALID_PRIORITY_LOW).withTags(VALID_TAG_CS2103T).build();
        DESC_MIDTERM = new EditTaskDescriptorBuilder().withName(VALID_NAME_MIDTERM)
                .withDescription(VALID_DESCRIPTION_MIDTERM).withDeadline(VALID_DEADLINE_MIDTERM)
                .withPriority(VALID_PRIORITY_MEDIUM).withTags(VALID_TAG_CS2102, VALID_TAG_TEST).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the task list, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TaskList expectedTaskList = new TaskList(actualModel.getTaskList());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTaskList, actualModel.getTaskList());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s task list.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().fullName.split("\\s+");
        model.updateFilteredTaskList(new NameContainsKeywordsPredicate(Collections.singleton(
                new Keyword(splitName[0]))));

        assertEquals(1, model.getFilteredTaskList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task with the matching {@code keywords} with task name
     * in the {@code model}'s task list.
     */
    public static void showTaskWithKeywords(Model model, String... keywords) {
        Set<Keyword> set = new HashSet<>();
        for (String keyword : keywords) {
            set.add(new Keyword(keyword));
        }
        model.updateFilteredTaskList(new NameContainsKeywordsPredicate(set));
        assertTrue(0 < model.getFilteredTaskList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show the tasks at the 2 given {@code targetIndexes} in the
     * {@code model}'s task list.
     */
    public static void showTasksAtIndexes(Model model, Index targetIndex1, Index targetIndex2) {
        assertTrue(targetIndex1.getZeroBased() < model.getFilteredTaskList().size());
        assertTrue(targetIndex2.getZeroBased() < model.getFilteredTaskList().size());

        Task task1 = model.getFilteredTaskList().get(targetIndex1.getZeroBased());
        String[] splitName1 = task1.getName().fullName.split("\\s+");
        NameContainsKeywordsPredicate p1 = new NameContainsKeywordsPredicate(Collections.singleton(
                new Keyword(splitName1[0])));

        Task task2 = model.getFilteredTaskList().get(targetIndex2.getZeroBased());
        String[] splitName2 = task2.getName().fullName.split("\\s+");
        NameContainsKeywordsPredicate p2 = new NameContainsKeywordsPredicate(Collections.singleton(
                new Keyword(splitName2[0])));

        model.updateFilteredTaskList(p1.or(p2));
        assertEquals(2, model.getFilteredTaskList().size());
    }
}

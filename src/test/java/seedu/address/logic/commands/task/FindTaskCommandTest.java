package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.UserPrefs;
import seedu.address.model.name.TaskNameContainsKeywordsPredicate;
import seedu.address.model.tag.TaskTagContainsKeywordsPredicate;
/**
 * Contains integration tests (interaction with the Model) for {@code FindTaskCommand}.
 */
public class FindTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void equals() {

        TaskNameContainsKeywordsPredicate firstPredicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("first"));
        TaskNameContainsKeywordsPredicate secondPredicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("second"));
        TaskTagContainsKeywordsPredicate thirdPredicate =
                new TaskTagContainsKeywordsPredicate(Collections.singletonList("third"));
        TaskTagContainsKeywordsPredicate fourthPredicate =
                new TaskTagContainsKeywordsPredicate(Collections.singletonList("fourth"));

        FindTaskCommand findFirstCommand = new FindTaskCommand(firstPredicate, thirdPredicate);
        FindTaskCommand findSecondCommand = new FindTaskCommand(secondPredicate, fourthPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTaskCommand findFirstCommandCopy = new FindTaskCommand(firstPredicate, thirdPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonAndTagFound() {
        List<String> nameList = Arrays.asList();
        List<String> tagList = Arrays.asList();
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskNameContainsKeywordsPredicate namePredicate = prepareNamePredicate(nameList);
        TaskTagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(tagList);
        FindTaskCommand command = new FindTaskCommand(namePredicate, tagPredicate);
        expectedModel.updateFilteredTaskList(namePredicate.or(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsAndTagsFound() {
        List<String> nameList = Arrays.asList("Meeting", "Dinner");
        List<String> tagList = Arrays.asList("friends", "neighbours");
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        TaskNameContainsKeywordsPredicate namePredicate = prepareNamePredicate(nameList);
        TaskTagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(tagList);
        FindTaskCommand command = new FindTaskCommand(namePredicate, tagPredicate);
        expectedModel.updateFilteredTaskList(namePredicate.or(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIRST_TASK), model.getFilteredTaskList());
    }

    /**
     * Creates a {@code TaskNameContainsKeywordsPredicate} using {@code nameList}.
     */
    private TaskNameContainsKeywordsPredicate prepareNamePredicate(List<String> nameList) {
        return new TaskNameContainsKeywordsPredicate(nameList);
    }
    /**
     * Creates a {@code TaskTagContainsKeywordsPredicate} using {@code tagSet}.
     */
    private TaskTagContainsKeywordsPredicate prepareTagPredicate(List<String> tagList) {
        return new TaskTagContainsKeywordsPredicate(tagList);
    }
}

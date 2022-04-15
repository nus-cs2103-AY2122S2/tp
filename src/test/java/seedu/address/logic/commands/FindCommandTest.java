package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.CS2103T_PROJECT;
import static seedu.address.testutil.TypicalTasks.CS2105_MIDTERM;
import static seedu.address.testutil.TypicalTasks.CS2106_FINALS;
import static seedu.address.testutil.TypicalTasks.MEET_ALICE;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.keyword.Keyword;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TagContainsKeywordsPredicate;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.DeadlineInRangePredicate;
import seedu.address.model.task.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalTaskList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTaskList(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singleton(new Keyword("firstName")));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singleton(new Keyword("secondName")));
        TagContainsKeywordsPredicate firstTagPredicate =
                new TagContainsKeywordsPredicate(new HashSet<>(Collections.singletonList(new Keyword("firstTag"))));
        TagContainsKeywordsPredicate secondTagPredicate =
                new TagContainsKeywordsPredicate(new HashSet<>(Collections.singletonList(new Keyword("secondTag"))));
        DeadlineInRangePredicate firstDeadlinePredicate =
                new DeadlineInRangePredicate(new Deadline("2022-03-04"), new Deadline("2022-03-05"));
        DeadlineInRangePredicate secondDeadlinePredicate =
                new DeadlineInRangePredicate(new Deadline("2022-03-09"), new Deadline("2022-03-22"));

        FindCommand findFirstCommand =
                new FindCommand(firstNamePredicate, firstTagPredicate, firstDeadlinePredicate);
        FindCommand findSecondCommand =
                new FindCommand(secondNamePredicate, secondTagPredicate, secondDeadlinePredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy =
                new FindCommand(firstNamePredicate, firstTagPredicate, firstDeadlinePredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different find command -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywordsAndVeryLargeDateRange_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 6);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(" ");
        DeadlineInRangePredicate deadlinePredicate =
                new DeadlineInRangePredicate(null, null);
        FindCommand command = new FindCommand(namePredicate, tagPredicate, deadlinePredicate);
        expectedModel.updateFilteredTaskList(namePredicate.and(tagPredicate).and(deadlinePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredTaskList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleNameKeywordsAndZeroTagKeywordsAndVeryLargeDateRange_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Alice Project");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(" ");
        DeadlineInRangePredicate deadlinePredicate =
                new DeadlineInRangePredicate(null, null);
        FindCommand command = new FindCommand(namePredicate, tagPredicate, deadlinePredicate);
        expectedModel.updateFilteredTaskList(namePredicate.and(tagPredicate).and(deadlinePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103T_PROJECT, MEET_ALICE), model.getFilteredTaskList());
    }

    @Test
    public void execute_zeroNameKeywordsAndMultipleTagKeywordsAndVeryLargeDateRange_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate("exam CS2103T");
        DeadlineInRangePredicate deadlinePredicate =
                new DeadlineInRangePredicate(null, null);
        FindCommand command = new FindCommand(namePredicate, tagPredicate, deadlinePredicate);
        expectedModel.updateFilteredTaskList(namePredicate.and(tagPredicate).and(deadlinePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2105_MIDTERM, CS2103T_PROJECT, CS2106_FINALS), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleNameKeywordsAndMultipleTagKeywordsAndVeryLargeDateRange_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Alice Project");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate("exam cs2103t");
        DeadlineInRangePredicate deadlinePredicate =
                new DeadlineInRangePredicate(null, null);
        FindCommand command = new FindCommand(namePredicate, tagPredicate, deadlinePredicate);
        expectedModel.updateFilteredTaskList(namePredicate.and(tagPredicate).and(deadlinePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredTaskList(),
                model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {

        String[] splitInputs = userInput.split("\\s+");
        Set<Keyword> keywordSet = new HashSet<>();

        for (String s : splitInputs) {
            keywordSet.add(new Keyword(s));
        }
        return new NameContainsKeywordsPredicate(keywordSet);
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        String[] splitInputs = userInput.split("\\s+");
        Set<Keyword> keywordSet = new HashSet<>();

        for (String s : splitInputs) {
            keywordSet.add(new Keyword(s));
        }
        return new TagContainsKeywordsPredicate(keywordSet);
    }
}

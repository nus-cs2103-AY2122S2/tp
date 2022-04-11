package seedu.address.logic.commands.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_JOBS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalJobs.ENGINEER;
import static seedu.address.testutil.TypicalJobs.SOFTWARE_ENGINEER;
import static seedu.address.testutil.TypicalJobs.WAITER;
import static seedu.address.testutil.TypicalJobs.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.job.JobIdSamePredicate;
import seedu.address.model.job.NameJobContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindJobTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameJobContainsKeywordsPredicate firstPredicate =
                new NameJobContainsKeywordsPredicate(Collections.singletonList("first"));
        NameJobContainsKeywordsPredicate secondPredicate =
                new NameJobContainsKeywordsPredicate(Collections.singletonList("second"));

        FindJob findFirstJobCommand = new FindJob(firstPredicate);
        FindJob findSecondJobCommand = new FindJob(secondPredicate);

        // same job -> returns true
        assertTrue(findFirstJobCommand.equals(findFirstJobCommand));

        // same values -> returns true
        FindJob findFirstCommandCopy = new FindJob(firstPredicate);
        assertTrue(findFirstJobCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstJobCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstJobCommand.equals(null));

        // different job -> returns false
        assertFalse(findFirstJobCommand.equals(findSecondJobCommand));
    }

    @Test
    public void execute_zeroKeywords_noJobFound() {
        String expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 0);
        NameJobContainsKeywordsPredicate predicate = preparePredicateName(" ");
        FindJob command = new FindJob(predicate);
        expectedModel.updateFilteredJobList(predicate);
        assertCommandSuccess(command, model, expectedMessage, false, true, false, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredJobList());
    }

    @Test
    public void execute_multipleKeywords_multipleJobsFound() {
        String expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 3);
        NameJobContainsKeywordsPredicate predicate = preparePredicateName("Waiter Software Engineer");
        FindJob command = new FindJob(predicate);
        expectedModel.updateFilteredJobList(predicate);
        assertCommandSuccess(command, model, expectedMessage, false, true, false, expectedModel);
        assertEquals(Arrays.asList(SOFTWARE_ENGINEER, ENGINEER, WAITER), model.getFilteredJobList());
    }

    @Test
    public void execute_zeroID_noJobFound() {
        String expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 0);
        JobIdSamePredicate predicate = preparePredicateID(" 1");
        FindJob command = new FindJob(predicate);
        expectedModel.updateFilteredJobList(predicate);
        assertCommandSuccess(command, model, expectedMessage, false, true, false, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredJobList());
    }

    @Test
    public void execute_jobIdFound() {
        String expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 1);
        JobIdSamePredicate predicate = preparePredicateID("3");
        FindJob command = new FindJob(predicate);
        expectedModel.updateFilteredJobList(predicate);
        assertCommandSuccess(command, model, expectedMessage, false, true, false, expectedModel);
        assertEquals(Arrays.asList(SOFTWARE_ENGINEER), model.getFilteredJobList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private JobIdSamePredicate preparePredicateID(String userInput) {
        return new JobIdSamePredicate(userInput.trim());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameJobContainsKeywordsPredicate preparePredicateName(String userInput) {
        return new NameJobContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

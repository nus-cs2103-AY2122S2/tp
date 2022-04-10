package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INSURANCES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInsurances.INSURANCE1;
import static seedu.address.testutil.TypicalInsurances.INSURANCE2;
import static seedu.address.testutil.TypicalInsurances.INSURANCE3;
import static seedu.address.testutil.TypicalInsurances.getTypicalInsuranceBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.insurance.TitleContainsKeywordsPredicate;

public class FindInsuranceCommandTest {

    private Model model = new ModelManager(getTypicalInsuranceBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInsuranceBook(), new UserPrefs());

    @Test
    public void equals() {
        TitleContainsKeywordsPredicate firstPredicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("first"));
        TitleContainsKeywordsPredicate secondPredicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("second"));

        FindInsuranceCommand findFirstCommand = new FindInsuranceCommand(firstPredicate);
        FindInsuranceCommand findSecondCommand = new FindInsuranceCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindInsuranceCommand findFirstCommandCopy = new FindInsuranceCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noInsuranceFound() {
        String expectedMessage = String.format(MESSAGE_INSURANCES_LISTED_OVERVIEW, 0);
        TitleContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindInsuranceCommand command = new FindInsuranceCommand(predicate);
        expectedModel.updateFilteredInsuranceList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredInsuranceList());
    }

    @Test
    public void execute_multipleKeywords_multipleInsurancesFound() {
        String expectedMessage = String.format(MESSAGE_INSURANCES_LISTED_OVERVIEW, 3);
        TitleContainsKeywordsPredicate predicate = preparePredicate("Health Car House");
        FindInsuranceCommand command = new FindInsuranceCommand(predicate);
        expectedModel.updateFilteredInsuranceList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(INSURANCE1, INSURANCE2, INSURANCE3), model.getFilteredInsuranceList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TitleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TitleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}

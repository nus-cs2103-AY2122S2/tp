package seedu.address.logic.commands.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplicants.CARL;
import static seedu.address.testutil.TypicalApplicants.ELLE;
import static seedu.address.testutil.TypicalApplicants.FIONA;
import static seedu.address.testutil.TypicalApplicants.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.NameApplicantContainsKeywordsPredicate;

class FindApplicantTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameApplicantContainsKeywordsPredicate firstPredicate =
                new NameApplicantContainsKeywordsPredicate(Collections.singletonList("first"));
        NameApplicantContainsKeywordsPredicate secondPredicate =
                new NameApplicantContainsKeywordsPredicate(Collections.singletonList("second"));

        FindApplicant findFirstApplicantCommand = new FindApplicant(firstPredicate);
        FindApplicant findSecondApplicantCommand = new FindApplicant(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstApplicantCommand.equals(findFirstApplicantCommand));

        // same values -> returns true
        FindApplicant findFirstCommandCopy = new FindApplicant(firstPredicate);
        assertTrue(findFirstApplicantCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstApplicantCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstApplicantCommand.equals(null));

        // different Applicant -> returns false
        assertFalse(findFirstApplicantCommand.equals(findSecondApplicantCommand));
    }

    @Test
    public void execute_zeroKeywords_noApplicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
        NameApplicantContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindApplicant command = new FindApplicant(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, true, false, false, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleKeywords_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        NameApplicantContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindApplicant command = new FindApplicant(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, true, false, false, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredApplicantList());
    }

    /**
     * Parses {@code userInput} into a {@code NameApplicantContainsKeywordsPredicate}.
     */
    private NameApplicantContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameApplicantContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}

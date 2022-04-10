package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_RECORDS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRecords.RECORD1;
import static seedu.address.testutil.TypicalRecords.RECORD2;
import static seedu.address.testutil.TypicalRecords.RECORD3;
import static seedu.address.testutil.TypicalRecords.getTypicalRecordBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.record.RecordContainsKeywordsPredicate;

public class FindRecordCommandTest {

    private Model model = new ModelManager(getTypicalRecordBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalRecordBook(), new UserPrefs());

    @Test
    public void equals() {
        RecordContainsKeywordsPredicate firstPredicate =
                new RecordContainsKeywordsPredicate(Collections.singletonList("first"));
        RecordContainsKeywordsPredicate secondPredicate =
                new RecordContainsKeywordsPredicate(Collections.singletonList("second"));

        FindRecordCommand findFirstCommand = new FindRecordCommand(firstPredicate);
        FindRecordCommand findSecondCommand = new FindRecordCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindRecordCommand findFirstCommandCopy = new FindRecordCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noRecordFound() {
        String expectedMessage = String.format(MESSAGE_RECORDS_LISTED_OVERVIEW, 0);
        RecordContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindRecordCommand command = new FindRecordCommand(predicate);
        expectedModel.updateFilteredRecordList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRecordList());
    }

    @Test
    public void execute_multipleKeywords_multipleRecordsFound() {
        String expectedMessage = String.format(MESSAGE_RECORDS_LISTED_OVERVIEW, 3);
        RecordContainsKeywordsPredicate predicate = preparePredicate("1 2 3");
        FindRecordCommand command = new FindRecordCommand(predicate);
        expectedModel.updateFilteredRecordList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(RECORD1, RECORD2, RECORD3), model.getFilteredRecordList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private RecordContainsKeywordsPredicate preparePredicate(String userInput) {
        return new RecordContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

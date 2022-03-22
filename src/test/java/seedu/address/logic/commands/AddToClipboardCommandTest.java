package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.awt.HeadlessException;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.predicates.NameExistsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code AddToClipboardCommand}.
 */
class AddToClipboardCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /**
     * Test for the equals() command in AddToClipboardCommand.java file.
     */
    @Test
    public void equals() {
        NameExistsPredicate firstPredicate =
                new NameExistsPredicate(new Name("first"));
        NameExistsPredicate secondPredicate =
                new NameExistsPredicate(new Name("second"));

        AddToClipboardCommand clipFirstCommand = new AddToClipboardCommand(firstPredicate);
        AddToClipboardCommand clipSecondCommand = new AddToClipboardCommand(secondPredicate);

        // same object -> returns true
        assertTrue(clipFirstCommand.equals(clipFirstCommand));

        // same values -> returns true
        AddToClipboardCommand clipFirstCommandCopy = new AddToClipboardCommand(firstPredicate);
        assertTrue(clipFirstCommand.equals(clipFirstCommandCopy));

        // different types -> returns false
        assertFalse(clipFirstCommand.equals(1));

        // null -> returns false
        assertFalse(clipFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(clipFirstCommand.equals(clipSecondCommand));
    }

    /**
     * Test for when an empty name (n/) field is entered.
     * Should return the error message regarding naming constraints from the Name.java class.
     */
    @Test
    public void execute_emptyNameInput() {
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;

        assertThrows(IllegalArgumentException.class, expectedMessage, () -> preparePredicate(" "));
    }

    /**
     * Test for when an invalid name (n/) field is entered.
     * Should return the error message regarding naming constraints from the Name.java class.
     */
    @Test
    public void execute_invalidNameInput() {
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, () -> preparePredicate("##"));
    }

    /**
     * Test for when a person is found matching the given keyword.
     * Should return the success message from AddToClipboardCommand.java class.
     */
    @Test
    void execute_personFound() throws HeadlessException {
        String expectedMessage = AddToClipboardCommand.MESSAGE_SUCCESS;
        NameExistsPredicate predicate = preparePredicate("Elle Meyer");
        AddToClipboardCommand command = new AddToClipboardCommand(predicate);
        try {
            //This has to be implemented as automated testing on github does not have a "clipboard" to copy to,
            //so this test will be skipped.
            expectedModel.updateFilteredPersonList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            assertEquals(Arrays.asList(ELLE), model.getFilteredPersonList());
        } catch (HeadlessException e) {
            return;
        }
    }

    /**
     * Test for when no person is found matching the given keyword.
     * Should return the failure message from AddToClipboardCommand.java class.
     */
    @Test
    void execute_personNotFound() throws HeadlessException {
        String expectedMessage = AddToClipboardCommand.MESSAGE_FAILURE;
        NameExistsPredicate predicate = preparePredicate("Not Elle Meyer");
        AddToClipboardCommand command = new AddToClipboardCommand(predicate);
        try {
            //This has to be implemented as automated testing on github does not have a "clipboard" to copy to,
            //so this test will be skipped.
            expectedModel.updateFilteredPersonList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            assertEquals(Collections.emptyList(), model.getFilteredPersonList());
        } catch (HeadlessException e) {
            return;
        }
    }

    /**
     * Parses {@code userInput} into a {@code NameExistsPredicate}.
     */
    private NameExistsPredicate preparePredicate(String userInput) {
        return new NameExistsPredicate(new Name(userInput));
    }
}

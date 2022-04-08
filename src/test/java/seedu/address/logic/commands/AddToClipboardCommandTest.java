package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FALSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.InsurancePackagesSet;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.NameExistsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code AddToClipboardCommand}.
 */
class AddToClipboardCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new InsurancePackagesSet());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            new InsurancePackagesSet());

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
     * Test for when a person is found matching the given name keyword.
     * Should return the success message from AddToClipboardCommand.java class.
     */
    @Test
    void execute_personFound() throws HeadlessException {
        try {
            //Checks if the environment has a clipboard to copy to. If not, return immediately.
            //This has to be implemented as automated testing on github does not have a "clipboard" to copy to,
            //so this test will be skipped.
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        } catch (HeadlessException e) {
            return;
        }

        String expectedMessage = AddToClipboardCommand.MESSAGE_SUCCESS;
        NameExistsPredicate predicate = preparePredicate("Elle Meyer");
        AddToClipboardCommand command = new AddToClipboardCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredPersonList());
    }

    /**
     * Test for when no person is found matching the given name keyword.
     * Should return the failure message from AddToClipboardCommand.java class.
     */
    @Test
    void execute_personNotFound() throws HeadlessException {
        try {
            //Checks if the environment has a clipboard to copy to. If not, return immediately.
            //This has to be implemented as automated testing on github does not have a "clipboard" to copy to,
            //so this test will be skipped.
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        } catch (HeadlessException e) {
            return;
        }

        String expectedMessage = AddToClipboardCommand.MESSAGE_FAILURE;
        NameExistsPredicate predicate = preparePredicate("Not Elle Meyer");
        AddToClipboardCommand command = new AddToClipboardCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /**
     * Test for when a person is found at the given index.
     * Should return the success message from AddToClipboardCommand.java class.
     */
    @Test
    void execute_indexFound() throws HeadlessException {
        try {
            //Checks if the environment has a clipboard to copy to. If not, return immediately.
            //This has to be implemented as automated testing on github does not have a "clipboard" to copy to,
            //so this test will be skipped.
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        } catch (HeadlessException e) {
            return;
        }

        String expectedMessage = AddToClipboardCommand.MESSAGE_SUCCESS;
        Person personToClip = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddToClipboardCommand command = new AddToClipboardCommand(INDEX_FIRST_PERSON);
        expectedModel.updateFilteredPersonList(new NameExistsPredicate(personToClip.getName()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(personToClip), model.getFilteredPersonList());
    }

    /**
     * Test for when a person is not found at the given index.
     * Should return the failure message from AddToClipboardCommand.java class.
     */
    @Test
    void execute_indexNotFound() throws HeadlessException {
        try {
            //Checks if the environment has a clipboard to copy to. If not, return immediately.
            //This has to be implemented as automated testing on github does not have a "clipboard" to copy to,
            //so this test will be skipped.
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        } catch (HeadlessException e) {
            return;
        }

        String expectedMessage = AddToClipboardCommand.MESSAGE_FAILURE;
        AddToClipboardCommand command = new AddToClipboardCommand(INDEX_FALSE);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Parses {@code userInput} into a {@code NameExistsPredicate}.
     */
    private NameExistsPredicate preparePredicate(String userInput) {
        return new NameExistsPredicate(new Name(userInput));
    }
}

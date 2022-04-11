package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_CLIENT_LIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FilterCommand.MESSAGE_INVALID_FILTER_ATTRIBUTE;
import static seedu.address.logic.commands.FilterCommand.MESSAGE_INVALID_FILTER_OPERATOR;
import static seedu.address.logic.commands.FilterCommand.MESSAGE_INVALID_FILTER_VALUE;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private FilterCommand validTestCommand = new FilterCommand("birthMonth", "equal", "february");

    @Test
    public void execute_nullModel_failure() {
        assertThrows(NullPointerException.class, () -> validTestCommand.execute(null));
    }

    @Test
    public void execute_emptyAddressBook_throwCommandException() {
        Model model = new ModelManager();

        assertCommandFailure(validTestCommand, model, String.format(MESSAGE_EMPTY_CLIENT_LIST, "filter"));
    }

    @Test
    public void execute_invalidAttribute_throwCommandException() {
        FilterCommand invalidAttributeCommand = new FilterCommand("fake", "equal", "25");
        assertCommandFailure(invalidAttributeCommand, model, String.format(MESSAGE_INVALID_FILTER_ATTRIBUTE, "fake"));
    }

    @Test
    public void execute_validParameters_success() {

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        FilterCommand validCommand = new FilterCommand("birthMonth", "greater", "february");
        String expectedMessage = String.format(FilterCommand.MESSAGE_SUCCESS, "birthMonth", "greater", "february");
        assertCommandSuccess(validCommand, model, expectedMessage, model);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        validCommand = new FilterCommand("company", "equal", "Test");
        expectedMessage = String.format(FilterCommand.MESSAGE_SUCCESS, "company", "equal", "Test");
        assertCommandSuccess(validCommand, model, expectedMessage, model);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        validCommand = new FilterCommand("premium", "lesserorequal", "200");
        expectedMessage = String.format(FilterCommand.MESSAGE_SUCCESS, "premium", "lesserorequal", "200");
        assertCommandSuccess(validCommand, model, expectedMessage, model);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        validCommand = new FilterCommand("age", "lesser", "25");
        expectedMessage = String.format(FilterCommand.MESSAGE_SUCCESS, "age", "lesser", "25");
        assertCommandSuccess(validCommand, model, expectedMessage, model);

        validCommand = new FilterCommand("age", "greaterorequal", "25");
        expectedMessage = String.format(FilterCommand.MESSAGE_SUCCESS, "age", "greaterorequal", "25");
        assertCommandSuccess(validCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidValue_throwCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        FilterCommand invalidCommand = new FilterCommand("birthMonth", "greater", "none");
        assertCommandFailure(invalidCommand, model, String.format(MESSAGE_INVALID_FILTER_VALUE, "none",
                "month (e.g. february)", "birthMonth"));

        invalidCommand = new FilterCommand("company", "equal", "");
        assertCommandFailure(invalidCommand, model,
                "Names should only contain alphanumeric characters and spaces,"
                        + " and it should not be blank");

        invalidCommand = new FilterCommand("age", "equal", "none");
        assertCommandFailure(invalidCommand, model, String.format(MESSAGE_INVALID_FILTER_VALUE, "none",
                "integer", "age"));


        invalidCommand = new FilterCommand("premium", "equal", "none");
        assertCommandFailure(invalidCommand, model, String.format(MESSAGE_INVALID_FILTER_VALUE, "none",
                "integer", "premium"));
    }

    @Test
    public void execute_invalidOperator_throwCommandException() {
        FilterCommand invalidOperatorCommand = new FilterCommand("age", "same", "25");
        assertCommandFailure(invalidOperatorCommand, model, String.format(MESSAGE_INVALID_FILTER_OPERATOR, "same"));
    }

    @Test
    public void equals() {
        FilterCommand filterFirstCommand = new FilterCommand("birthMonth", "equals", "february");
        FilterCommand filterSecondCommand = new FilterCommand("age", "equal", "25");

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterSecondCommandCopy = new FilterCommand("age", "equal", "25");
        assertTrue(filterSecondCommand.equals(filterSecondCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(5));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different attribute -> returns false
        FilterCommand filterSecondDifferentAttribute = new FilterCommand("year", "equal", "25");
        assertFalse(filterSecondCommand.equals(filterSecondDifferentAttribute));

        // different operator -> returns false
        FilterCommand filterSecondDifferentOp = new FilterCommand("age", "greater", "25");
        assertFalse(filterSecondCommand.equals(filterSecondDifferentOp));

        // different value -> returns false
        FilterCommand filterSecondDifferentVal = new FilterCommand("age", "equal", "23");
        assertFalse(filterSecondCommand.equals(filterSecondDifferentVal));
    }
}

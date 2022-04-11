package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private String invalidCriteria = "invalidCriteria";

    @Test
    public void equals() {
        SortsCommand sortFirstCommand = new SortsCommand("name");
        SortsCommand sortSecondCommand = new SortsCommand("phone");

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortsCommand sortFirstCommandCopy = new SortsCommand("name");
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }

    @Test
    public void execute_sortName_sortedListUpdated() {
        String expectedMessage = String.format(Messages.MESSAGE_SORTED, "name");
        SortsCommand command = new SortsCommand("name");
        expectedModel.sortFilteredPersonListByName();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getSortedPersonList());
    }

    @Test
    public void execute_sortDeadline_sortedListUpdated() {
        String expectedMessage = String.format(Messages.MESSAGE_SORTED, "deadline");
        SortsCommand command = new SortsCommand("deadline");
        expectedModel.sortFilteredPersonListByDeadlineList();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ALICE, FIONA, BENSON, DANIEL, ELLE, GEORGE), model.getSortedPersonList());
    }

    @Test
    public void execute_sortAddress_sortedListUpdated() {
        String expectedMessage = String.format(Messages.MESSAGE_SORTED, "address");
        SortsCommand command = new SortsCommand("address");
        expectedModel.sortFilteredPersonListByAddress();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ALICE, BENSON, GEORGE, FIONA, ELLE, CARL), model.getSortedPersonList());
    }

    @Test
    public void execute_sortEmail_sortedListUpdated() {
        String expectedMessage = String.format(Messages.MESSAGE_SORTED, "email");
        SortsCommand command = new SortsCommand("email");
        expectedModel.sortFilteredPersonListByEmail();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, GEORGE, DANIEL, CARL, BENSON, FIONA, ELLE), model.getSortedPersonList());
    }

    @Test
    public void execute_sortFavourite_sortedListUpdated() {
        String expectedMessage = String.format(Messages.MESSAGE_SORTED, "fav");
        SortsCommand command = new SortsCommand("fav");
        expectedModel.sortFilteredPersonListByFavourite();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, FIONA, ALICE, CARL, DANIEL, ELLE, GEORGE), model.getSortedPersonList());
    }

    @Test
    public void execute_sortHighImportance_sortedListUpdated() {
        String expectedMessage = String.format(Messages.MESSAGE_SORTED, "impt");
        SortsCommand command = new SortsCommand("impt");
        expectedModel.sortFilteredPersonListByHighImportance();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, ELLE, ALICE, CARL, DANIEL, FIONA, GEORGE), model.getSortedPersonList());
    }

    @Test
    public void execute_sortPhone_sortedListUpdated() {
        String expectedMessage = String.format(Messages.MESSAGE_SORTED, "phone");
        SortsCommand command = new SortsCommand("phone");
        expectedModel.sortFilteredPersonListByPhone();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ALICE, ELLE, FIONA, GEORGE, CARL, BENSON), model.getSortedPersonList());
    }

    @Test
    public void execute_sortInvalidCriteria_errorThrown() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_CRITERIA, invalidCriteria);
        SortsCommand command = new SortsCommand(invalidCriteria);
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(model));
    }
}

package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FilterCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.CovidStatus;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private CovidStatus positive = new CovidStatus("positive");
    private CovidStatus negative = new CovidStatus("negative");
    private CovidStatus hrw = new CovidStatus("hrw");
    private CovidStatus hrn = new CovidStatus("hrn");

    @Test
    public void isStatus_correctOutput() {
        // correctly matches CovidStatus input -> returns true
        assertTrue(CARL.isStatus(hrn));
        assertTrue(ELLE.isStatus(hrw));
        assertTrue(FIONA.isStatus(negative));
        assertTrue(GEORGE.isStatus(positive));

        // wrongly matches CovidStatus input -> returns false
        assertFalse(CARL.isStatus(positive));
        assertFalse(ELLE.isStatus(negative));
    }

    @Test
    public void equals() {

        FilterCommand filterFirstCommand = new FilterCommand("positive");
        FilterCommand filterSecondCommand = new FilterCommand("hrw");

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand("positive");
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_positive_covidPositivePersonsFound() {
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        FilterCommand command = new FilterCommand(CovidStatus.CovidStatusTier.POSITIVE.toString());
        expectedModel.updateFilteredPersonList(person -> person.isStatus(positive));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, GEORGE), model.getFilteredPersonList());
    }
}

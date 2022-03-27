package seedu.trackbeau.logic.commands;

import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackbeau.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.trackbeau.testutil.TypicalCustomers.getTypicalTrackBeau;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.commands.customer.ListCustomersCommand;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.ModelManager;
import seedu.trackbeau.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCustomersCommand.
 */
public class ListCustomersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTrackBeau(), new UserPrefs());
        expectedModel = new ModelManager(model.getTrackBeau(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCustomersCommand(), model, ListCustomersCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);
        assertCommandSuccess(new ListCustomersCommand(), model, ListCustomersCommand.MESSAGE_SUCCESS, expectedModel);
    }
}

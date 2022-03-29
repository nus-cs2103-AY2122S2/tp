package seedu.trackbeau.logic.commands;

import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackbeau.testutil.TypicalCustomers.getTypicalTrackBeau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.commands.customer.AddCustomerCommand;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.ModelManager;
import seedu.trackbeau.model.UserPrefs;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.testutil.CustomerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCustomerCommand}.
 */
public class AddCustomerCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTrackBeau(), new UserPrefs());
    }

    @Test
    public void execute_newCustomer_success() {
        Customer validCustomer = new CustomerBuilder().build();

        Model expectedModel = new ModelManager(model.getTrackBeau(), new UserPrefs());
        expectedModel.addCustomer(validCustomer);

        assertCommandSuccess(new AddCustomerCommand(validCustomer), model,
            String.format(AddCustomerCommand.MESSAGE_SUCCESS, validCustomer), expectedModel);
    }

    @Test
    public void execute_duplicateCustomer_throwsCommanldException() {
        Customer customerInList = model.getTrackBeau().getCustomerList().get(0);
        assertCommandFailure(new AddCustomerCommand(customerInList), model,
            AddCustomerCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

}

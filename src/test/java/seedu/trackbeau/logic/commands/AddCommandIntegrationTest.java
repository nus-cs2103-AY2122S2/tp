package seedu.trackbeau.logic.commands;

import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackbeau.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.ModelManager;
import seedu.trackbeau.model.UserPrefs;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Customer validCustomer = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getTrackBeau(), new UserPrefs());
        expectedModel.addCustomer(validCustomer);

        assertCommandSuccess(new AddCommand(validCustomer), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validCustomer), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Customer customerInList = model.getTrackBeau().getCustomerList().get(0);
        assertCommandFailure(new AddCommand(customerInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}

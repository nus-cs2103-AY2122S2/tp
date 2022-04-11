package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.AddressCustomer;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameCustomer;
import seedu.address.model.customer.PhoneCustomer;
import seedu.address.model.driver.Driver;
import seedu.address.testutil.DriverBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCustomerCommand}.
 */
public class AddDriverCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newDriver_success() {
        Driver validDriver = new DriverBuilder().build();
        Customer dummyCustomer = new Customer(new NameCustomer("dummy"), new PhoneCustomer("99999993"),
                new AddressCustomer("dummy address"));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addDriver(validDriver);

        assertCommandSuccess(new AddDriverCommand(validDriver, dummyCustomer), model,
                String.format(AddDriverCommand.MESSAGE_SUCCESS, validDriver), expectedModel);
    }

    @Test
    public void execute_duplicateDriver_throwsCommandException() {
        Driver driverInList = model.getAddressBook().getDriverList().get(0);
        Customer dummyCustomer = null;

        assertCommandFailure(new AddDriverCommand(driverInList, dummyCustomer), model,
                AddDriverCommand.MESSAGE_DUPLICATE_DRIVER);
    }

}

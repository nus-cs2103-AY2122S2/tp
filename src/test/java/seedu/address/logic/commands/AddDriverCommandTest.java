package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.customer.Customer;
import seedu.address.model.driver.Driver;
import seedu.address.testutil.DriverBuilder;
import seedu.address.testutil.ModelStub;

public class AddDriverCommandTest {

    @Test
    public void constructor_nullDriver_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDriverCommand(null, null));
    }


    @Test
    public void execute_duplicateDriver_throwsCommandException() {
        Driver validDriver = new DriverBuilder().build();
        Customer dummmyCustomer = null;

        AddDriverCommand addDriverCommand = new AddDriverCommand(validDriver, dummmyCustomer);
        ModelStub modelStub = new ModelStubWithDriver(validDriver);
        assertThrows(CommandException.class, AddDriverCommand.MESSAGE_DUPLICATE_DRIVER, () ->
                addDriverCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Driver alice = new DriverBuilder().withName("Alice").build();
        Driver bob = new DriverBuilder().withName("Bob").build();
        Customer dummmyCustomer = null;

        AddDriverCommand addAliceCommand = new AddDriverCommand(alice, dummmyCustomer);
        AddDriverCommand addBobCommand = new AddDriverCommand(bob, dummmyCustomer);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddDriverCommand addAliceCommandCopy = new AddDriverCommand(alice, dummmyCustomer);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different customer -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single driver.
     */
    private class ModelStubWithDriver extends ModelStub {
        private final Driver driver;

        ModelStubWithDriver(Driver driver) {
            requireNonNull(driver);
            this.driver = driver;
        }

        @Override
        public boolean hasDriver(Driver driver) {
            requireNonNull(driver);
            return this.driver.isSameDriver(driver);
        }
    }

    /**
     * A Model stub that always accept the customer being added.
     */
    private class ModelStubAcceptingDriverAdded extends ModelStub {
        final ArrayList<Driver> driversAdded = new ArrayList<>();

        @Override
        public boolean hasDriver(Driver driver) {
            requireNonNull(driver);
            return driversAdded.stream().anyMatch(driver::isSameDriver);
        }

        @Override
        public void addDriver(Driver driver) {
            requireNonNull(driver);
            driversAdded.add(driver);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}


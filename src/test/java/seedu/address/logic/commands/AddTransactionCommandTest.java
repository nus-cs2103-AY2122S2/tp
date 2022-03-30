package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.PersonUtil.AMY;
import static seedu.address.testutil.TransactionUtil.INVALID_TRANSACTION;
import static seedu.address.testutil.TransactionUtil.TRANSACTION_ONE;
import static seedu.address.testutil.TransactionUtil.TRANSACTION_TWO;
import static seedu.address.testutil.TransactionUtil.VALID_INDEX;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.util.TransactionBuilder;

public class AddTransactionCommandTest {

    @Test
    public void constructor_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTransactionCommand(null, x -> null));
        assertThrows(NullPointerException.class, () -> new AddTransactionCommand(Index.fromZeroBased(0),
                null));
    }

    @Test
    public void execute_transactionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTransactionAdded modelStub = new ModelStubAcceptingTransactionAdded();
        Transaction validTransaction = TRANSACTION_ONE;
        Index validIndex = VALID_INDEX;

        CommandResult commandResult = new AddTransactionCommand(validIndex, x -> validTransaction).execute(modelStub);

        assertEquals(String.format(AddTransactionCommand.MESSAGE_SUCCESS, AMY, validTransaction),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(validTransaction), modelStub.transactionAdded);
    }

    @Test
    public void execute_transactionInvalid_throwsCommandException() {
        ModelStubAcceptingTransactionAdded modelStub = new ModelStubAcceptingTransactionAdded();
        Transaction invalidTransaction = INVALID_TRANSACTION;
        Index validIndex = VALID_INDEX;

        assertThrows(CommandException.class, () -> new AddTransactionCommand(validIndex, x -> invalidTransaction)
                .execute(modelStub));
    }

    @Test
    public void equals() {
        TransactionBuilder tb = x -> TRANSACTION_ONE;

        AddTransactionCommand command = new AddTransactionCommand(VALID_INDEX, tb);

        // same object -> returns true
        assertTrue(command.equals(command));

        // same values -> returns true
        assertTrue(command.equals(new AddTransactionCommand(VALID_INDEX, tb)));

        // different types -> returns false
        assertFalse(command.equals(1));

        // null -> returns false
        assertFalse(command.equals(null));

        // different person -> returns false
        assertFalse(command.equals(new AddTransactionCommand(Index.fromZeroBased(2), x -> TRANSACTION_TWO)));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortPersonList(Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTransaction(Transaction target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTransactionWithId(long personId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTransaction(Transaction transaction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTransaction(Transaction target, Transaction editedPerson) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public ObservableList<Transaction> getFilteredTransactionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTransactionList(Predicate<Transaction> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingTransactionAdded extends ModelStub {
        final ArrayList<Transaction> transactionAdded = new ArrayList<>();

        @Override
        public void addTransaction(Transaction transaction) {
            requireNonNull(transaction);
            transactionAdded.add(transaction);
        }

        @Override
        public FilteredList<Person> getFilteredPersonList() {
            ObservableList<Person> obs = FXCollections.observableArrayList(AMY);
            return new FilteredList<Person>(obs);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}

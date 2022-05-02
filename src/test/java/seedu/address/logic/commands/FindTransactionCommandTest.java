package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TransactionUtil.INDEX_FIRST_TRANSACTION;
import static seedu.address.testutil.TransactionUtil.INDEX_SECOND_TRANSACTION;
import static seedu.address.testutil.TransactionUtil.VALID_ID;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalTransactions.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.util.TransactionPredicateBuilder;
import seedu.address.model.transaction.util.TransactionWithIdentifierPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class FindTransactionCommandTest {

    private Model model;
    private TransactionWithIdentifierPredicate dummyPredicate;
    private TransactionPredicateBuilder dummyPredicateBuilder;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        dummyPredicate = new TransactionWithIdentifierPredicate(VALID_ID);
        dummyPredicateBuilder = x -> dummyPredicate;
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person selectedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        TransactionPredicateBuilder b = TransactionWithIdentifierPredicate::new;
        String expectedMessage = String.format(Messages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW,
                selectedPerson.getName());

        FindTransactionCommand findCommand = new FindTransactionCommand(INDEX_FIRST_PERSON, b);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredTransactionList(b.createTransactionPredicate(selectedPerson.getUniqueId()));

        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        FindTransactionCommand command = new FindTransactionCommand(outOfBoundIndex, x -> dummyPredicate);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person selectedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        TransactionWithIdentifierPredicate predicate =
                new TransactionWithIdentifierPredicate(selectedPerson.getUniqueId());
        String expectedMessage = String.format(Messages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW,
                selectedPerson.getName());

        FindTransactionCommand findCommand = new FindTransactionCommand(INDEX_FIRST_PERSON, x -> predicate);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.updateFilteredTransactionList(predicate);

        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTransactionList().size());

        FindTransactionCommand command = new FindTransactionCommand(outOfBoundIndex, x -> dummyPredicate);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        FindTransactionCommand findFirstCommand = new FindTransactionCommand(INDEX_FIRST_TRANSACTION,
                dummyPredicateBuilder);
        FindTransactionCommand findSecondCommand = new FindTransactionCommand(INDEX_SECOND_TRANSACTION,
                dummyPredicateBuilder);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTransactionCommand findFirstCommandCopy = new FindTransactionCommand(INDEX_FIRST_TRANSACTION,
                dummyPredicateBuilder);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTransaction(Model model) {
        model.updateFilteredTransactionList(p -> false);

        assertTrue(model.getFilteredTransactionList().isEmpty());
    }
}

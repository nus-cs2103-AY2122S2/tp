package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ALEX;
import static seedu.address.logic.commands.CommandTestUtil.BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_1_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseExpert;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ExpenseExpert;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Expense;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class EditPersonCommandTest {
    private Model model = new ModelManager(getTypicalExpenseExpert(), new UserPrefs());


    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList
                .withPersonAmount(VALID_PERSON_1_AMOUNT)
                .build();

        EditPersonCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPersonAmount(VALID_PERSON_1_AMOUNT)
                .build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new ExpenseExpert(model.getExpenseExpert()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPersonCommand editPersonCommand =
                new EditPersonCommand(INDEX_FIRST_EXPENSE, new EditPersonCommand.EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_EXPENSE.getZeroBased());

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new ExpenseExpert(model.getExpenseExpert()), new UserPrefs());

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_EXPENSE);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList)
                .withPersonAmount(VALID_PERSON_1_AMOUNT).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(INDEX_FIRST_EXPENSE,
                new EditPersonDescriptorBuilder().withPersonAmount(VALID_PERSON_1_AMOUNT).build());

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new ExpenseExpert(model.getExpenseExpert()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);


        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidExpenseIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPersonAmount(VALID_PERSON_1_AMOUNT).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editPersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of expense expert
     */
    @Test
    public void execute_invalidExpenseIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_EXPENSE);
        Index outOfBoundIndex = INDEX_SECOND_EXPENSE;
        // ensures that outOfBoundIndex is still in bounds of expense expert list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExpenseExpert().getPersonList().size());

        EditPersonCommand editPersonCommand = new EditPersonCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withPersonAmount(VALID_PERSON_1_AMOUNT).build());

        assertCommandFailure(editPersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPersonCommand standardCommand = new EditPersonCommand(INDEX_FIRST_EXPENSE, ALEX);

        // same values -> returns true
        EditPersonCommand.EditPersonDescriptor copyDescriptor =
                new EditPersonCommand.EditPersonDescriptor(ALEX);
        EditPersonCommand commandWithSameValues = new EditPersonCommand(INDEX_FIRST_EXPENSE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPersonCommand(INDEX_SECOND_EXPENSE, ALEX)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPersonCommand(INDEX_FIRST_EXPENSE, BOB)));
    }

    /**
     * Update's model's budget according to the new transaction recorded.
     */
    private void setNewBudget(Model model, Expense expenseToEdit, Expense editedExpense) {
        double difference = expenseToEdit.getAmount().amount - editedExpense.getAmount().amount;
        String amt = String.valueOf((model.getBudget().getBudgetAmount().amount + difference));
        Amount newAmount = new Amount(amt);
        Budget budget = new Budget(newAmount);
        model.setBudget(budget);
    }

    /**
     * Update's model's budget according to the new transaction recorded.
     * @param model model whose budget is to be updated.
     * @param editedExpense the edited transaction
     */
    private void noNewBudget(Model model, Expense editedExpense) {
        double difference = editedExpense.getAmount().amount - editedExpense.getAmount().amount;
        String amt = String.valueOf((model.getBudget().getBudgetAmount().amount + difference));
        Amount newAmount = new Amount(amt);
        Budget budget = new Budget(newAmount);
        model.setBudget(budget);
    }
}

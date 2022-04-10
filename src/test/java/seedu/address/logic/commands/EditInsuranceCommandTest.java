package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_HEART;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LIFE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_HEART;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_HEART;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInsuranceAtIndex;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.address.testutil.TypicalInsurances.getTypicalInsuranceBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRecords.getTypicalRecordBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditInsuranceCommand.EditInsuranceDescriptor;
import seedu.address.model.InsuranceBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.insurance.Insurance;
import seedu.address.testutil.EditInsuranceDescriptorBuilder;
import seedu.address.testutil.InsuranceBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditInsuranceCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalInsuranceBook(),
            getTypicalAppointmentBook(), getTypicalRecordBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Insurance editedInsurance = new InsuranceBuilder().build();
        EditInsuranceDescriptor descriptor = new EditInsuranceDescriptorBuilder(editedInsurance).build();
        EditInsuranceCommand editInsuranceCommand = new EditInsuranceCommand(INDEX_FIRST_OBJECT, descriptor);

        String expectedMessage =
                String.format(EditInsuranceCommand.MESSAGE_EDIT_INSURANCE_SUCCESS, editedInsurance);

        Model expectedModel = new ModelManager(new InsuranceBook(model.getInsuranceBook()), new UserPrefs());
        expectedModel.setInsurance(model.getFilteredInsuranceList().get(0), editedInsurance);

        assertCommandSuccess(editInsuranceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastInsurance = Index.fromOneBased(model.getFilteredInsuranceList().size());
        Insurance lastInsurance = model.getFilteredInsuranceList().get(indexLastInsurance.getZeroBased());

        InsuranceBuilder insuranceInList = new InsuranceBuilder(lastInsurance);
        Insurance editedInsurance = insuranceInList.withTitle(VALID_TITLE_HEART)
                .withPrice(VALID_PRICE_HEART)
                .build();

        EditInsuranceDescriptor descriptor = new EditInsuranceDescriptorBuilder()
                .withTitle(VALID_TITLE_HEART).withPrice(VALID_PRICE_HEART).build();
        EditInsuranceCommand editInsuranceCommand = new EditInsuranceCommand(indexLastInsurance, descriptor);

        String expectedMessage =
                String.format(EditInsuranceCommand.MESSAGE_EDIT_INSURANCE_SUCCESS, editedInsurance);

        Model expectedModel = new ModelManager(new InsuranceBook(model.getInsuranceBook()), new UserPrefs());
        expectedModel.setInsurance(lastInsurance, editedInsurance);

        assertCommandSuccess(editInsuranceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditInsuranceCommand editInsuranceCommand =
                new EditInsuranceCommand(INDEX_FIRST_OBJECT, new EditInsuranceDescriptor());
        Insurance editedInsurance = model.getFilteredInsuranceList().get(INDEX_FIRST_OBJECT.getZeroBased());

        String expectedMessage =
                String.format(EditInsuranceCommand.MESSAGE_EDIT_INSURANCE_SUCCESS, editedInsurance);

        Model expectedModel = new ModelManager(new InsuranceBook(model.getInsuranceBook()), new UserPrefs());

        assertCommandSuccess(editInsuranceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInsuranceAtIndex(model, INDEX_FIRST_OBJECT);

        Insurance insuranceInFilteredList = model.getFilteredInsuranceList()
                .get(INDEX_FIRST_OBJECT.getZeroBased());
        Insurance editedInsurance = new InsuranceBuilder(insuranceInFilteredList)
                .withTitle(VALID_TITLE_HEART).build();
        EditInsuranceCommand editInsuranceCommand = new EditInsuranceCommand(INDEX_FIRST_OBJECT,
                new EditInsuranceDescriptorBuilder().withTitle(VALID_TITLE_HEART).build());

        String expectedMessage =
                String.format(EditInsuranceCommand.MESSAGE_EDIT_INSURANCE_SUCCESS, editedInsurance);

        Model expectedModel = new ModelManager(new InsuranceBook(model.getInsuranceBook()), new UserPrefs());
        expectedModel.setInsurance(model.getFilteredInsuranceList().get(0), editedInsurance);

        assertCommandSuccess(editInsuranceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateInsuranceUnfilteredList_failure() {
        Insurance firstInsurance = model.getFilteredInsuranceList().get(INDEX_FIRST_OBJECT.getZeroBased());
        EditInsuranceDescriptor descriptor = new EditInsuranceDescriptorBuilder(firstInsurance).build();
        EditInsuranceCommand editInsuranceCommand = new EditInsuranceCommand(INDEX_SECOND_OBJECT, descriptor);

        assertCommandFailure(editInsuranceCommand, model, EditInsuranceCommand.MESSAGE_DUPLICATE_INSURANCE);
    }

    @Test
    public void execute_duplicateInsuranceFilteredList_failure() {
        showInsuranceAtIndex(model, INDEX_FIRST_OBJECT);

        // edit insurance in filtered list into a duplicate in address book
        Insurance insuranceInList = model.getInsuranceBook().getInsuranceList()
                .get(INDEX_SECOND_OBJECT.getZeroBased());
        EditInsuranceCommand editInsuranceCommand = new EditInsuranceCommand(INDEX_FIRST_OBJECT,
                new EditInsuranceDescriptorBuilder(insuranceInList).build());

        assertCommandFailure(editInsuranceCommand, model, EditInsuranceCommand.MESSAGE_DUPLICATE_INSURANCE);
    }

    @Test
    public void execute_invalidInsuranceIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInsuranceList().size() + 1);
        EditInsuranceDescriptor descriptor = new EditInsuranceDescriptorBuilder()
                .withTitle(VALID_TITLE_HEART).build();
        EditInsuranceCommand editInsuranceCommand = new EditInsuranceCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editInsuranceCommand, model, Messages.MESSAGE_INVALID_INSURANCE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidInsuranceIndexFilteredList_failure() {
        showInsuranceAtIndex(model, INDEX_FIRST_OBJECT);
        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInsuranceBook().getInsuranceList().size());

        EditInsuranceCommand editInsuranceCommand = new EditInsuranceCommand(outOfBoundIndex,
                new EditInsuranceDescriptorBuilder().withTitle(VALID_TITLE_HEART).build());

        assertCommandFailure(editInsuranceCommand, model, Messages.MESSAGE_INVALID_INSURANCE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditInsuranceCommand standardCommand = new EditInsuranceCommand(INDEX_FIRST_OBJECT, DESC_LIFE);

        // same values -> returns true
        EditInsuranceDescriptor copyDescriptor = new EditInsuranceDescriptor(DESC_LIFE);
        EditInsuranceCommand commandWithSameValues = new EditInsuranceCommand(INDEX_FIRST_OBJECT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditInsuranceCommand(INDEX_SECOND_OBJECT, DESC_LIFE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditInsuranceCommand(INDEX_FIRST_OBJECT, DESC_HEART)));
    }

}

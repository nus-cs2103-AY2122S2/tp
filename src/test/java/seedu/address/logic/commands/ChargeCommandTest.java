package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPetAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PET;
import static seedu.address.testutil.TypicalPets.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.charge.Charge;
import seedu.address.model.charge.ChargeDate;
import seedu.address.model.pet.Pet;
import seedu.address.testutil.PetBuilder;



class ChargeCommandTest {

    private static final String CHARGE_AMOUNT_STUB = "200.50";
    private static final String CHARGE_AMOUNT_STUB_ZERO = "0";
    private static final String CHARGE_DATE_STUB = "03-2022";
    private static final String CHARGE_DATE_STUB_TWO = "04-2022";
    private static final String DATE_STUB = "2022-03-22";
    private static final String DATE_STUB_TWO = "2022-03-26";
    private static final String DATE_STUB_DIFF_MONTH = "2022-02-26";
    private static final String DATE_STUB_DIFF_YEAR = "2021-03-26";



    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_computeChargeNoChargeUnfilteredList_success() {
        ChargeDate chargeDate = new ChargeDate(CHARGE_DATE_STUB);
        Charge charge = new Charge(CHARGE_AMOUNT_STUB);

        ChargeCommand chargeCommand = new ChargeCommand(INDEX_FIRST_PET, chargeDate, charge);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ChargeCommand.MESSAGE_COMPUTE_CHARGE_SUCCESS, 0.0));

        assertCommandSuccess(chargeCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_computeChargeWithChargeUnfilteredList_success() {
        ChargeDate chargeDate = new ChargeDate(CHARGE_DATE_STUB);
        Charge charge = new Charge(CHARGE_AMOUNT_STUB);

        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkPresent = new PetBuilder(firstPet)
                .withPresentAttendanceEntry(DATE_STUB)
                .build();
        ChargeCommand chargeCommand = new ChargeCommand(INDEX_FIRST_PET, chargeDate, charge);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ChargeCommand.MESSAGE_COMPUTE_CHARGE_SUCCESS, 200.50));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(firstPet, petToMarkPresent);

        assertCommandSuccess(chargeCommand, expectedModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_computeChargeWithMoreChargeUnfilteredList_success() {
        ChargeDate chargeDate = new ChargeDate(CHARGE_DATE_STUB);
        Charge charge = new Charge(CHARGE_AMOUNT_STUB);

        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkPresent = new PetBuilder(firstPet)
                .withPresentAttendanceEntry(DATE_STUB)
                .withPresentAttendanceEntry(DATE_STUB_TWO)
                .withPresentAttendanceEntry(DATE_STUB_DIFF_MONTH)
                .withPresentAttendanceEntry(DATE_STUB_DIFF_YEAR)
                .build();

        ChargeCommand chargeCommand = new ChargeCommand(INDEX_FIRST_PET, chargeDate, charge);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ChargeCommand.MESSAGE_COMPUTE_CHARGE_SUCCESS, 401.00));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(firstPet, petToMarkPresent);

        assertCommandSuccess(chargeCommand, expectedModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_computeChargeWithInvalidIndex_failure() {
        ChargeDate chargeDate = new ChargeDate(CHARGE_DATE_STUB);
        Charge charge = new Charge(CHARGE_AMOUNT_STUB);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        ChargeCommand chargeCommand = new ChargeCommand(outOfBoundIndex, chargeDate, charge);

        assertCommandFailure(chargeCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void execute_computeChargeNoChargefilteredList_success() throws CommandException {
        showPetAtIndex(model, INDEX_THIRD_PET);

        ChargeDate chargeDate = new ChargeDate(CHARGE_DATE_STUB);
        Charge charge = new Charge(CHARGE_AMOUNT_STUB);

        ChargeCommand chargeCommand = new ChargeCommand(INDEX_FIRST_PET, chargeDate, charge);
        CommandResult expectedCommandResult = new CommandResult(
                String.format(ChargeCommand.MESSAGE_COMPUTE_CHARGE_SUCCESS, 0.0));

        assertCommandSuccess(chargeCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_computeChargeWithChargefilteredList_success() throws CommandException {
        showPetAtIndex(model, INDEX_THIRD_PET);

        ChargeDate chargeDate = new ChargeDate(CHARGE_DATE_STUB);
        Charge charge = new Charge(CHARGE_AMOUNT_STUB);

        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkPresent = new PetBuilder(firstPet)
                .withPresentAttendanceEntry(DATE_STUB)
                .withPresentAttendanceEntry(DATE_STUB_TWO)
                .withPresentAttendanceEntry(DATE_STUB_DIFF_MONTH)
                .withPresentAttendanceEntry(DATE_STUB_DIFF_YEAR)
                .build();
        model.setPet(firstPet, petToMarkPresent);

        ChargeCommand chargeCommand = new ChargeCommand(INDEX_FIRST_PET, chargeDate, charge);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ChargeCommand.MESSAGE_COMPUTE_CHARGE_SUCCESS, 401.00));

        assertCommandSuccess(chargeCommand, model, expectedCommandResult, model);
    }


    @Test
    public void equals() {
        final ChargeCommand standardCommand = new ChargeCommand(INDEX_FIRST_PET,
                    new ChargeDate(CHARGE_DATE_STUB), new Charge(CHARGE_AMOUNT_STUB));
        // same values -> returns true
        ChargeCommand commandWithSameValues = new ChargeCommand(INDEX_FIRST_PET,
                new ChargeDate(CHARGE_DATE_STUB), new Charge(CHARGE_AMOUNT_STUB));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new ChargeCommand(INDEX_SECOND_PET,
                new ChargeDate(CHARGE_DATE_STUB), new Charge(CHARGE_AMOUNT_STUB))));
        // different charge date, same charge amount and total chargeable -> returns false
        assertFalse(standardCommand.equals(new ChargeCommand(INDEX_SECOND_PET,
                new ChargeDate(CHARGE_DATE_STUB_TWO), new Charge(CHARGE_AMOUNT_STUB_ZERO))));
        // different charge amount, same charge amount and total chargeable -> returns false
        assertFalse(standardCommand.equals(new ChargeCommand(INDEX_SECOND_PET,
                new ChargeDate(CHARGE_DATE_STUB_TWO), new Charge(CHARGE_AMOUNT_STUB_ZERO))));
    }

}

package woofareyou.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.logic.commands.CommandTestUtil.assertCommandFailure;
import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;
import static woofareyou.logic.commands.CommandTestUtil.showPetAtIndex;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static woofareyou.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static woofareyou.testutil.TypicalIndexes.INDEX_THIRD_PET;
import static woofareyou.testutil.TypicalPets.getTypicalPetBook;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import woofareyou.commons.core.Messages;
import woofareyou.commons.core.index.Index;
import woofareyou.logic.commands.exceptions.CommandException;
import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.PetBook;
import woofareyou.model.UserPrefs;
import woofareyou.model.charge.Charge;
import woofareyou.model.pet.Pet;
import woofareyou.testutil.PetBuilder;


class ChargeCommandTest {

    private static final String CHARGE_AMOUNT_STUB = "200.50";
    private static final String CHARGE_AMOUNT_STUB_ZERO = "0";
    private static final String CHARGE_DATE_STUB = "03-2022";
    private static final String CHARGE_DATE_STUB_TWO = "04-2022";
    private static final String DATE_STUB = "2022-03-22";
    private static final String DATE_STUB_TWO = "2022-03-26";
    private static final String DATE_STUB_DIFF_MONTH = "2022-02-26";
    private static final String DATE_STUB_DIFF_YEAR = "2021-03-26";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");

    private Model model = new ModelManager(getTypicalPetBook(), new UserPrefs());

    @Test
    public void execute_computeChargeNoChargeUnfilteredList_success() {
        YearMonth chargeDate = YearMonth.parse(CHARGE_DATE_STUB, formatter);
        Charge charge = new Charge(CHARGE_AMOUNT_STUB);
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        String petName = firstPet.getName().toString();

        ChargeCommand chargeCommand = new ChargeCommand(INDEX_FIRST_PET, chargeDate, charge);
        String attendance = chargeCommand.getAttendance(firstPet);
        String month = chargeCommand.getMonthName();
        int year = chargeDate.getYear();

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ChargeCommand.MESSAGE_COMPUTE_CHARGE_SUCCESS, petName, 0.0, month, year)
                        + attendance);

        assertCommandSuccess(chargeCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_computeChargeWithChargeUnfilteredList_success() {
        YearMonth chargeDate = YearMonth.parse(CHARGE_DATE_STUB, formatter);
        Charge charge = new Charge(CHARGE_AMOUNT_STUB);

        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkPresent = new PetBuilder(firstPet)
                .withPresentAttendanceEntry(DATE_STUB)
                .build();
        String petName = petToMarkPresent.getName().toString();
        ChargeCommand chargeCommand = new ChargeCommand(INDEX_FIRST_PET, chargeDate, charge);
        String attendance = chargeCommand.getAttendance(petToMarkPresent);
        String month = chargeCommand.getMonthName();
        int year = chargeDate.getYear();

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ChargeCommand.MESSAGE_COMPUTE_CHARGE_SUCCESS, petName, 200.50, month, year)
                        + attendance);
        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());
        expectedModel.setPet(firstPet, petToMarkPresent);

        assertCommandSuccess(chargeCommand, expectedModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_computeChargeWithMoreChargeUnfilteredList_success() {
        YearMonth chargeDate = YearMonth.parse(CHARGE_DATE_STUB, formatter);
        Charge charge = new Charge(CHARGE_AMOUNT_STUB);

        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkPresent = new PetBuilder(firstPet)
                .withPresentAttendanceEntry(DATE_STUB)
                .withPresentAttendanceEntry(DATE_STUB_TWO)
                .withPresentAttendanceEntry(DATE_STUB_DIFF_MONTH)
                .withPresentAttendanceEntry(DATE_STUB_DIFF_YEAR)
                .build();
        String petName = petToMarkPresent.getName().toString();
        ChargeCommand chargeCommand = new ChargeCommand(INDEX_FIRST_PET, chargeDate, charge);
        String attendance = chargeCommand.getAttendance(petToMarkPresent);
        String month = chargeCommand.getMonthName();
        int year = chargeDate.getYear();

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ChargeCommand.MESSAGE_COMPUTE_CHARGE_SUCCESS, petName, 401.00, month, year)
                        + attendance);
        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());
        expectedModel.setPet(firstPet, petToMarkPresent);

        assertCommandSuccess(chargeCommand, expectedModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_computeChargeWithInvalidIndex_failure() {
        YearMonth chargeDate = YearMonth.parse(CHARGE_DATE_STUB, formatter);
        Charge charge = new Charge(CHARGE_AMOUNT_STUB);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        ChargeCommand chargeCommand = new ChargeCommand(outOfBoundIndex, chargeDate, charge);

        assertCommandFailure(chargeCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void execute_computeChargeNoChargefilteredList_success() throws CommandException {
        showPetAtIndex(model, INDEX_THIRD_PET);

        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        String petName = firstPet.getName().toString();
        YearMonth chargeDate = YearMonth.parse(CHARGE_DATE_STUB, formatter);
        Charge charge = new Charge(CHARGE_AMOUNT_STUB);

        ChargeCommand chargeCommand = new ChargeCommand(INDEX_FIRST_PET, chargeDate, charge);
        String attendance = chargeCommand.getAttendance(firstPet);
        String month = chargeCommand.getMonthName();
        int year = chargeDate.getYear();

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ChargeCommand.MESSAGE_COMPUTE_CHARGE_SUCCESS, petName, 0.0, month, year)
                        + attendance);

        assertCommandSuccess(chargeCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_computeChargeWithChargefilteredList_success() {
        showPetAtIndex(model, INDEX_THIRD_PET);

        YearMonth chargeDate = YearMonth.parse(CHARGE_DATE_STUB, formatter);
        Charge charge = new Charge(CHARGE_AMOUNT_STUB);

        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkPresent = new PetBuilder(firstPet)
                .withPresentAttendanceEntry(DATE_STUB)
                .withPresentAttendanceEntry(DATE_STUB_TWO)
                .withPresentAttendanceEntry(DATE_STUB_DIFF_MONTH)
                .withPresentAttendanceEntry(DATE_STUB_DIFF_YEAR)
                .build();
        model.setPet(firstPet, petToMarkPresent);
        String petName = petToMarkPresent.getName().toString();
        ChargeCommand chargeCommand = new ChargeCommand(INDEX_FIRST_PET, chargeDate, charge);
        String attendance = chargeCommand.getAttendance(petToMarkPresent);
        String month = chargeCommand.getMonthName();
        int year = chargeDate.getYear();

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ChargeCommand.MESSAGE_COMPUTE_CHARGE_SUCCESS, petName, 401.00, month, year)
                        + attendance);

        assertCommandSuccess(chargeCommand, model, expectedCommandResult, model);
    }


    @Test
    public void equals() {
        YearMonth chargeDate = YearMonth.parse(CHARGE_DATE_STUB, formatter);
        YearMonth chargeDateTwo = YearMonth.parse(CHARGE_DATE_STUB_TWO, formatter);

        final ChargeCommand standardCommand = new ChargeCommand(INDEX_FIRST_PET,
                chargeDate, new Charge(CHARGE_AMOUNT_STUB));
        // same values -> returns true
        ChargeCommand commandWithSameValues = new ChargeCommand(INDEX_FIRST_PET,
                chargeDate, new Charge(CHARGE_AMOUNT_STUB));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new ChargeCommand(INDEX_SECOND_PET,
                chargeDate, new Charge(CHARGE_AMOUNT_STUB))));
        // different charge date, same charge amount and total chargeable -> returns false
        assertFalse(standardCommand.equals(new ChargeCommand(INDEX_SECOND_PET,
                chargeDateTwo, new Charge(CHARGE_AMOUNT_STUB_ZERO))));
        // different charge amount, same charge amount and total chargeable -> returns false
        assertFalse(standardCommand.equals(new ChargeCommand(INDEX_SECOND_PET,
                chargeDateTwo, new Charge(CHARGE_AMOUNT_STUB_ZERO))));
    }

}

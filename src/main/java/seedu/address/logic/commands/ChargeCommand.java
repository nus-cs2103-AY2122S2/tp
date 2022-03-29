package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.AttendanceEntry;
import seedu.address.model.charge.Charge;
import seedu.address.model.charge.ChargeDate;
import seedu.address.model.pet.Pet;


/**
 * Computes a month's charge of a pet identified using it's displayed index from the address book.
 */
public class ChargeCommand extends Command {

    public static final String COMMAND_WORD = "charge";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Computes a month's charge of the pet identified "
            + "by the index number used in the last pet listing.\n"
            + "Parameters: INDEX (must be a positive integer)"
            + "m/[MM-yyyy] c/[cost]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "m/03-2022 c/200";

    public static final String MESSAGE_COMPUTE_CHARGE_SUCCESS = "Computed charge of Pet: $%.2f";
    private final Index index;
    private final ChargeDate chargeDate;
    private final Charge charge;

    /**
     * @param index of the pet in the filtered pets list to compute the charges of.
     * @param chargeDate the month in the specified year to calculate amount chargeable.
     */
    public ChargeCommand(Index index, ChargeDate chargeDate, Charge charge) {
        requireAllNonNull(index, chargeDate);

        this.index = index;
        this.chargeDate = chargeDate;
        this.charge = charge;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Pet> lastShownList = model.getFilteredPetList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet petToCharge = lastShownList.get(index.getZeroBased());
        double amountChargeable = 0.0 ;
        // check current charge
        if (this.charge.getCharge() == null) {
            throw new CommandException(String.format(Messages.MESSAGE_NO_CHARGE_SET, ChargeCommand.MESSAGE_USAGE));
        }
        // get attendance
        Collection<AttendanceEntry> entries = petToCharge.getAttendanceHashMap().toCollection();
        // calculate charge based on number of days in month
        for (AttendanceEntry entry : entries) {
            if (entry.getAttendanceDate().getMonthValue() == chargeDate.getMonth()
                && entry.getAttendanceDate().getYear() == chargeDate.getYear()
                && entry.getIsPresent().get() == true) {
                amountChargeable += this.charge.getCharge();
            }
        }

        return new CommandResult(generateSuccessMessage(amountChargeable));
    }

    /**
     * Generates a command execution success message
     * {@code petToEdit}.
     */
    private String generateSuccessMessage(double amountChargeable) {
        return String.format(MESSAGE_COMPUTE_CHARGE_SUCCESS, amountChargeable);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ChargeCommand)) {
            return false;
        }

        // state check
        ChargeCommand e = (ChargeCommand) other;
        return index.equals(e.index)
                && chargeDate.equals(e.chargeDate)
                && charge.equals(e.charge);
    }

}

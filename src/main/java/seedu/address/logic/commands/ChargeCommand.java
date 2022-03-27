package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.charge.Charge;
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
            + "m/ [MM]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "m/03";

    public static final String MESSAGE_COMPUTE_CHARGE_SUCCESS = "Computed charge of Pet: %1$s";
    private final Index index;
    private final Charge dailyCharge;
    private final Integer month;

    /**
     * @param index of the pet in the filtered pets list to compute the charges of.
     * @param dailyCharge of pet stay.
     */
    public ChargeCommand(Index index, Charge dailyCharge, Integer month) {
        requireAllNonNull(index, dailyCharge, month);

        this.index = index;
        this.dailyCharge = dailyCharge;
        this.month = month;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Pet> lastShownList = model.getFilteredPetList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet petToCharge = lastShownList.get(index.getZeroBased());
        Integer amountChargeable = 0;
        // get attendance
        // calculate number of days in month

        return new CommandResult(generateSuccessMessage(amountChargeable));
    }

    /**
     * Generates a command execution success message
     * {@code petToEdit}.
     */
    private String generateSuccessMessage(Integer amountChargeable) {
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
                && dailyCharge.equals(e.dailyCharge);
    }

}

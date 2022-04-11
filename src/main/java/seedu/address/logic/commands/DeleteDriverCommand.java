package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.driver.Driver;

/**
 * Deletes a person identified using it's displayed index from the database.
 */
public class DeleteDriverCommand extends Command {
    public static final String COMMAND_WORD = "deletedriver";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the driver identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DRIVER_SUCCESS = "Deleted Driver: %1$s";
    public static final String MESSAGE_DELETE_DRIVER_FAIL_BUSY = "Delete Driver: %1$s "
            + "failed because driver is not free";

    private final Index targetIndex;

    public DeleteDriverCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Driver> lastShownList = model.getFilteredDriverList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DRIVER_DISPLAYED_INDEX);
        }

        Driver driverToDelete = lastShownList.get(targetIndex.getZeroBased());
        if (driverToDelete.isBusy()) {
            throw new CommandException(String.format(MESSAGE_DELETE_DRIVER_FAIL_BUSY, driverToDelete));
        }

        model.deleteDriver(driverToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_DRIVER_SUCCESS, driverToDelete), false,
                false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDriverCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteDriverCommand) other).targetIndex)); // state check
    }

}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteDishCommand extends Command {

    public static final String COMMAND_WORD = "deletedish";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the dish identified by the index number used in the displayed dish list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DISH_SUCCESS = "Deleted Dish: %1$s";

    private final Index targetIndex;

    public DeleteDishCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Dish> lastShownList = model.getFilteredDishList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISH_DISPLAYED_INDEX);
        }

        Dish dishToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteDish(dishToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_DISH_SUCCESS, dishToDelete),
                false, false, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDishCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteDishCommand) other).targetIndex)); // state check
    }
}


package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.buyer.Buyer;

public class DeleteBuyerCommand extends Command {

    public static final String COMMAND_WORD = "delete-b";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the buyer identified by the index number used in the displayed buyer list.\n"
            + "Parameters: INDEX (must be a positive integer less than or equal to size of displayed buyer list)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CLIENT_SUCCESS = "Deleted buyer: %1$s";

    private final Index targetIndex;

    public DeleteBuyerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Buyer> lastShownList = model.getFilteredBuyerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
        }

        Buyer buyerToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteBuyer(buyerToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CLIENT_SUCCESS, buyerToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBuyerCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteBuyerCommand) other).targetIndex)); // state check
    }
}

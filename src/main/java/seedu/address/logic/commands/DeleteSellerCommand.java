package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.seller.Seller;

public class DeleteSellerCommand extends Command {

    public static final String COMMAND_WORD = "delete-s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the seller identified by the index number used in the displayed seller list.\n"
            + "Parameters: INDEX (must be a positive integer less than or equal to size of displayed seller list)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_SELLER_SUCCESS = "Deleted seller: %1$s";

    private final Index targetIndex;

    public DeleteSellerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Seller> lastShownList = model.getFilteredSellerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
        }

        Seller sellerToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteSeller(sellerToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_SELLER_SUCCESS, sellerToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteSellerCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteSellerCommand) other).targetIndex)); // state check
    }
}

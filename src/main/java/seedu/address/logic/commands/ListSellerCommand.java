package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SELLERS;

import seedu.address.model.Model;

/**
 * Lists all clients in the address book to the user.
 */
public class ListSellerCommand extends Command {

    public static final String COMMAND_WORD = "list-s";

    public static final String MESSAGE_SUCCESS = "Listed all sellers";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredSellerList(PREDICATE_SHOW_ALL_SELLERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BUYERS;

import seedu.address.model.Model;

/**
 * Lists all clients in the address book to the user.
 */
public class ListBuyerCommand extends Command {

    public static final String COMMAND_WORD = "list-b";

    public static final String MESSAGE_SUCCESS = "Listed all buyers";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(PREDICATE_SHOW_ALL_BUYERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

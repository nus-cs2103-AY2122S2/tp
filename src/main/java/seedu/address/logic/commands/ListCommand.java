package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.address.model.Model;

/**
 * Lists all clients in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all clients";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all the clients in the address book. \n"
            + "Example: list";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}

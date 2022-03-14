package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Requests for confirmation from the user to clear the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_REQUEST_USER_CONFIRMATION = "Requesting \"clear\" confirmation from user ...";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_REQUEST_USER_CONFIRMATION, true);
    }
}

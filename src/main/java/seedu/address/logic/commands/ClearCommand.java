package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasNoClients()) {
            throw new CommandException(String.format(Messages.MESSAGE_EMPTY_CLIENT_LIST, "clear"));
        }

        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS, false, false, false,
                false, false, null, null, model.getDisplayedClient());
    }
}

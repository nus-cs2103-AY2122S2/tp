package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears TAPA.
 */
public class ConfirmClearCommand extends Command {

    public static final String COMMAND_WORD = "confirm";
    public static final String MESSAGE_CLEAR_ACKNOWLEDGEMENT = "TAPA has been completely cleared, as requested!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());

        // reset command history and address book history
        model.clearCommandHistory();
        model.clearAddressBookHistory();

        return new CommandResult(MESSAGE_CLEAR_ACKNOWLEDGEMENT);
    }
}

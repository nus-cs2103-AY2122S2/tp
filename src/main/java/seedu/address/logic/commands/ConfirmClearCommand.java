package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ConfirmClearCommand extends Command {

    public static final String COMMAND_WORD = "confirm";
    public static final String MESSAGE_CLEAR_ACKNOWLEDGEMENT = "Address book completely cleared, as requested!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_CLEAR_ACKNOWLEDGEMENT);
    }
}
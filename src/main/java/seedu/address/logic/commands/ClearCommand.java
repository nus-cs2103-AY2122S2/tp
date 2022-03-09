package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AB3Model;
import seedu.address.model.AddressBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends AB3Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(AB3Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

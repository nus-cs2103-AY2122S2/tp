package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.SellerAddressBook;

/**
 * Clears the address book.
 */
public class ClearSellerCommand extends Command {

    public static final String COMMAND_WORD = "clear-s";
    public static final String MESSAGE_SUCCESS = "Seller Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSellerAddressBook(new SellerAddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

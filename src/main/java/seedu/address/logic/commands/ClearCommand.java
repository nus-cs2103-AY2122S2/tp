package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.SellerAddressBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear-all";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setBuyerAddressBook(new BuyerAddressBook());
        model.setSellerAddressBook(new SellerAddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

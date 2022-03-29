package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearBuyerCommand extends Command {

    public static final String COMMAND_WORD = "clear-b";
    public static final String MESSAGE_SUCCESS = "Buyer Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setBuyerAddressBook(new BuyerAddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

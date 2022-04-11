package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.Schedule;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book and Schedule have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSchedule(new Schedule());
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

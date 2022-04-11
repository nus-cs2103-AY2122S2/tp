package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.HustleBook;
import seedu.address.model.Model;

/**
 * Clears the hustle book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all clients in the program.";

    public static final String MESSAGE_SUCCESS = "Hustle book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setHustleBook(new HustleBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

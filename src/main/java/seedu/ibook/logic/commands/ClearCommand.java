package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ibook.model.IBook;
import seedu.ibook.model.Model;

/**
 * Clears all the products in the iBook.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "IBook has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setIBook(new IBook());

        return new CommandResult(MESSAGE_SUCCESS);
    }
}

package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ibook.model.IBook;
import seedu.ibook.model.Model;

/**
 * Represents a command that when executed clears all the products in the iBook.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "IBook has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.prepareIBookForChanges();
        model.setIBook(new IBook());
        model.saveIBookChanges();

        return new CommandResult(MESSAGE_SUCCESS);
    }
}

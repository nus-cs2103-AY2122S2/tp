package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Favourites an existing client in the address book by their index number
 */
public class FavouritesWindowCommand extends Command {
    public static final String COMMAND_WORD = "fw";
    public static final String MESSAGE_FAVOURITE_WINDOW_SUCCESS =
            "Successfully opened Favourites window!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens up the Favourites window "
            + "that lists favourited persons.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_FAVOURITE_WINDOW_SUCCESS, false, false, true, false);
    }
}

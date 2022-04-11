package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.RecipeBook;

/**
 * Clears the recipe book after user confirms or the user specified a forced clear.
 */
public class ConfirmedClearCommand extends ClearCommand {
    public static final String COMMAND_WORD = "yes";
    public static final String COMMAND_NAME = "Confirmed Clear";
    public static final String MESSAGE_SUCCESS = "Recipe book has been cleared!";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command! Type in the clear command again if you wish "
            + "to clear\n";


    @Override
    public boolean equals(Object o) {
        // instanceof handles nulls
        if (!(o instanceof ConfirmedClearCommand)) {
            return false;
        }

        // short circuit if same object
        if (this == o) {
            return true;
        }

        ConfirmedClearCommand other = (ConfirmedClearCommand) o;
        return this.toString().equals(other.toString()); // state check
    }

    @Override
    public String toString() {
        return COMMAND_NAME;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setRecipeBook(new RecipeBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

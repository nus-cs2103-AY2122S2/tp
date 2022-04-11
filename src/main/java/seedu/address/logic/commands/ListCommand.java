package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all the recipes in the recipe book to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all the recipes that has been stored locally.\n\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredRecipeList(model.PREDICATE_SHOW_ALL_RECIPES);
        return new CommandResult(
                String.format(Messages.MESSAGE_RECIPES_LISTED_OVERVIEW, model.getFilteredRecipeList().size())
        );
    }

    @Override
    public boolean equals(Object o) {
        // instanceof handles nulls
        if (!(o instanceof ListCommand)) {
            return false;
        }

        // short circuit if same object
        if (this == o) {
            return true;
        }

        ListCommand other = (ListCommand) o;
        return this.toString().equals(other.toString()); // state check
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}

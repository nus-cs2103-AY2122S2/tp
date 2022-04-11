package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.RecipeContainsKeywordPredicate;

/**
 * Finds and lists all recipes in recipe book whose name, ingredient, or tag contains any of the argument keywords. <br>
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Searches all recipes whose name, ingredient(s), or tag(s) contain any of the specified "
            + "keywords (case-insensitive) and displays them as a list with index numbers.\n\n"
            + "Parameters: KEYWORD [, MORE_KEYWORDS]... \n\n"
            + "Example: " + COMMAND_WORD + " chicken cutlet, garlic, western";

    private final RecipeContainsKeywordPredicate recipePredicate;

    public FindCommand(RecipeContainsKeywordPredicate recipePredicate) {
        this.recipePredicate = recipePredicate;
    }

    public RecipeContainsKeywordPredicate getRecipePredicate() {
        return recipePredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredRecipeList(recipePredicate);

        if (model.getFilteredRecipeList().size() == 0) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_FIND_RETURNS_NO_RECIPES_FOUND, recipePredicate.toString()));
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_RECIPES_LISTED_OVERVIEW, model.getFilteredRecipeList().size())
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        // instanceof handles nulls
        if (!(o instanceof FindCommand)) {
            return false;
        }

        // short circuit if same object
        if (this == o) {
            return true;
        }

        FindCommand other = (FindCommand) o;
        return recipePredicate.equals(other.recipePredicate); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getRecipePredicate());
    }
}

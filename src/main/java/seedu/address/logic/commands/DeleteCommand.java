package seedu.address.logic.commands;

import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.RecipeBookParserUtil;
import seedu.address.model.Model;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;

/**
 * Deletes a recipe identified using it's displayed recipe name from the Recipe Book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the recipe identified by the name used in the displayed list of recipes.\n\n"
            + "Parameters:\n1. name (must be a valid name, not case-sensitive)\n"
            + "2. index (must be a valid, non-zero positive number)\n\n"
            + "Example: " + COMMAND_WORD + " aglio olio\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_INDEX + "1";

    public static final String MESSAGE_DELETE_RECIPE_SUCCESS = "Deleted Recipe: %1$s";

    private Name toDeleteName;
    private Index toDeleteIndex;

    /**
     * Creates a DeleteCommand to delete the specified {@code Recipe}
     * @param toDeleteName
     */
    public DeleteCommand(Name toDeleteName) {
        this.toDeleteName = toDeleteName;
    }

    public DeleteCommand(Index toDeleteIndex) {
        this.toDeleteIndex = toDeleteIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null;

        List<Recipe> lastShownList = model.getFilteredRecipeList();

        // Guaranteed that either toDeleteIndex or toDeleteName is non-null.
        Recipe recipeToDelete = (toDeleteIndex != null)
                ? getRecipe(lastShownList, toDeleteIndex)
                : getRecipe(lastShownList, toDeleteName);

        model.deleteRecipe(recipeToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_RECIPE_SUCCESS, recipeToDelete.getName()));
    }

    @Override
    public boolean equals(Object o) {
        // instanceof handles nulls
        if (!(o instanceof DeleteCommand)) {
            return false;
        }

        // short circuit if same object
        if (this == o) {
            return true;
        }

        DeleteCommand other = (DeleteCommand) o;
        if (toDeleteName != null && other.toDeleteName != null) {
            return toDeleteName.equals(other.toDeleteName);
        } else if (toDeleteIndex != null && other.toDeleteIndex != null) {
            return toDeleteIndex.equals(other.toDeleteIndex);
        } else {
            return false;
        }
    }

    /**
     * Retrieves the {@code Recipe} with the same name as the specified name
     * from a given list of recipes.
     * Throws a CommandException if a recipe with the same name cannot be found.
     *
     * @param lastShownList the list of recipes to search from.
     * @param recipeName the name of the recipe to view.
     * @return the recipe from the list matching the specified name.
     * @throws CommandException displays recipe name not found error message.
     */
    private Recipe getRecipe(List<Recipe> lastShownList, Name recipeName) throws CommandException {
        for (Recipe recipe : lastShownList) {
            if (RecipeBookParserUtil.isRecipeNamesEqual(recipeName, recipe.getName())) {
                return recipe;
            }
        }
        throw new CommandException(Messages.MESSAGE_DELETE_RECIPE_NOT_EXIST);
    }

    /**
     * Retrieves the {@code Recipe} at the specified index from the given list of recipes. <br>
     * Returns null if a recipe with the same name cannot be found.
     *
     * @param lastShownList the list of recipes to search from.
     * @param recipeIndex the index (zero-based) of the recipe to view.
     * @return the recipe from the list matching the specified index.
     * @throws CommandException if the index specified is out of bounds.
     */
    private Recipe getRecipe(List<Recipe> lastShownList, Index recipeIndex) throws CommandException {
        int zeroBasedIndex = recipeIndex.getZeroBased();

        if (zeroBasedIndex < lastShownList.size()) {
            return lastShownList.get(zeroBasedIndex);
        }

        throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_INDEX);
    }

    public Index getToDeleteIndex() {
        return toDeleteIndex;
    }

    public Name getToDeleteName() {
        return toDeleteName;

    }
}

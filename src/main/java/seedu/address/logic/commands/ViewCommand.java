package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.RecipeBookParserUtil;
import seedu.address.model.Model;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;

/**
 * Displays the contents of a recipe to the result display.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the details of a recipe identified by the name of "
            + "the recipe in the recipe list.\n\n"
            + "Parameters:\n1. name (must be a valid name, not case-sensitive)\n"
            + "2. index (must be a valid non-zero positive number)\n\n"
            + "Example: " + COMMAND_WORD + " Mac and cheese\n"
            + "Example: " + COMMAND_WORD + " -x 3";

    private Name targetName;
    private Index targetIndex;

    /**
     * Create a ViewCommand that displays the contents of stored recipe
     * with the same {@code Name} as the specified name.
     *
     * @param targetName Name of recipe to be viewed.
     */
    public ViewCommand(Name targetName) {
        requireNonNull(targetName);
        this.targetName = targetName;
    }

    /**
     * Create a ViewCommand that displays the contents of stored recipe
     * at the specified {@code Index} of the list.
     *
     * @param targetIndex Index (one-based) of recipe to be viewed.
     */
    public ViewCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        // Guaranteed that either targetIndex or targetName is non-null.
        Recipe recipe = (targetIndex != null)
                ? getRecipe(lastShownList, targetIndex)
                : getRecipe(lastShownList, targetName);

        if (recipe == null) { // Recipe not found in LastShownList
            throw new CommandException(String.format(Messages.MESSAGE_RECIPE_NOT_FOUND, targetName.fullName));
        }

        return new CommandResult(recipe);
    }

    @Override
    public boolean equals(Object o) {
        // instanceof handles nulls
        if (!(o instanceof ViewCommand)) {
            return false;
        }

        // short circuit if same object
        if (this == o) {
            return true;
        }

        ViewCommand other = (ViewCommand) o;
        return (targetName != null && targetName.equals(other.targetName))
                || (targetIndex != null && targetIndex.equals(other.targetIndex)); // state check
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
        throw new CommandException(String.format(Messages.MESSAGE_RECIPE_NOT_FOUND, recipeName));
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

        if (zeroBasedIndex >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INDEX_NOT_EXIST);
        }

        if (zeroBasedIndex < lastShownList.size()) {
            return lastShownList.get(zeroBasedIndex);
        }

        throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_INDEX);
    }
}

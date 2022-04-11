package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_COMPLETION_TIME;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_SERVING_SIZE;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_STEP;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECIPES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.RecipeBookParserUtil;
import seedu.address.model.Model;
import seedu.address.model.recipe.CompletionTime;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.ServingSize;
import seedu.address.model.recipe.Step;
import seedu.address.model.tag.Tag;

/**
 * Edits a recipe to the recipe book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the recipe"
            + " identified by the index number used in the displayed recipe list. "
            + "Existing values will be overwritten by the input values.\n\n"
            + "Compulsory parameters (only name OR index is necessary):\n"
            + "NAME (name of recipe to be edited)\n"
            + PREFIX_INDEX + "INDEX (index of recipe to be edited)\n\n"
            + "Optional Parameters:\n"
            + PREFIX_NAME + "NAME\n"
            + PREFIX_COMPLETION_TIME + "COMPLETION TIME\n"
            + PREFIX_SERVING_SIZE + "SERVING SIZE\n"
            + "[" + PREFIX_INGREDIENT + "INGREDIENT]...\n"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "[" + PREFIX_STEP + "STEP]...\n\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INDEX + "1 "
            + PREFIX_NAME + "Aglio Olio "
            + PREFIX_COMPLETION_TIME + "10 "
            + PREFIX_SERVING_SIZE + "1 "
            + PREFIX_INGREDIENT + "Spaghetti "
            + PREFIX_INGREDIENT + "Garlic "
            + PREFIX_INGREDIENT + "Sauce "
            + PREFIX_INGREDIENT + "Bacon "
            + PREFIX_STEP + "Cook the pasta "
            + PREFIX_STEP + "Saute the garlic "
            + PREFIX_STEP + "Toss pasta in the sauce "
            + PREFIX_STEP + "Add bacon in pasta "
            + PREFIX_STEP + "Taste and season "
            + PREFIX_TAG + "Italian "
            + PREFIX_TAG + "Western";

    public static final String MESSAGE_EDIT_RECIPE_SUCCESS = "Edited Recipe: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the recipe book.";

    private final Optional<Name> targetName;
    private final Optional<Index> targetIndex;
    private final EditRecipeDescriptor editRecipeDescriptor;

    /**
    * Constructs an {@code EditCommand} given a recipe name.
    *
     * @param name of the recipe in the filtered recipe list to edit
     * @param editRecipeDescriptor details to edit the recipe with
     */
    public EditCommand(Name name, EditRecipeDescriptor editRecipeDescriptor) {
        requireNonNull(name);
        requireNonNull(editRecipeDescriptor);

        this.targetName = Optional.of(name);
        this.targetIndex = Optional.empty();
        this.editRecipeDescriptor = new EditRecipeDescriptor(editRecipeDescriptor);
    }

    /**
    * Constructs an {@code EditCommand} given a recipe index.
    *
     * @param index of the recipe in the filtered recipe list to edit
     * @param editRecipeDescriptor details to edit the recipe with
     */
    public EditCommand(Index index, EditRecipeDescriptor editRecipeDescriptor) {
        requireNonNull(index);
        requireNonNull(editRecipeDescriptor);

        this.targetIndex = Optional.of(index);
        this.targetName = Optional.empty();
        this.editRecipeDescriptor = new EditRecipeDescriptor(editRecipeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        // Guaranteed that either targetIndex or targetName is non-null.
        Recipe recipeToEdit = (targetIndex.isPresent())
                ? getRecipe(lastShownList, targetIndex.get())
                : getRecipe(lastShownList, targetName.orElseThrow());

        Recipe editedRecipe = createEditedRecipe(recipeToEdit, editRecipeDescriptor);

        if (!recipeToEdit.isSameRecipe(editedRecipe) && model.hasRecipe(editedRecipe)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.setRecipe(recipeToEdit, editedRecipe);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);
        return new CommandResult(String.format(MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe.getName()));
    }

    /**
     * Creates and returns a {@code Recipe} with the details of {@code recipeToEdit}
     * edited with {@code editRecipeDescriptor}.
     */
    private static Recipe createEditedRecipe(Recipe recipeToEdit, EditRecipeDescriptor editRecipeDescriptor) {
        assert recipeToEdit != null;

        Name updatedName = editRecipeDescriptor.getName().orElse(recipeToEdit.getName());
        CompletionTime updatedCompletionTime =
                editRecipeDescriptor.getCompletionTime().orElse(recipeToEdit.getCompletionTime());
        ServingSize updatedServingSize =
                editRecipeDescriptor.getServingSize().orElse(recipeToEdit.getServingSize());
        List<Ingredient> updatedIngredients =
                editRecipeDescriptor.getIngredients().orElse(recipeToEdit.getIngredients());
        List<Step> updatedSteps =
                editRecipeDescriptor.getSteps().orElse(recipeToEdit.getSteps());
        Set<Tag> updatedTagList =
                editRecipeDescriptor.getTags().orElse(recipeToEdit.getTags());

        return new Recipe(updatedName, updatedCompletionTime, updatedServingSize,
                updatedIngredients, updatedSteps, updatedTagList);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return targetIndex.equals(e.targetIndex)
                && targetName.equals(e.targetName)
                && editRecipeDescriptor.equals(e.editRecipeDescriptor);
    }

    @Override
    public String toString() {
        return "EditCommand{"
                + "targetName=" + targetName
                + ", targetIndex=" + targetIndex
                + ", editRecipeDescriptor=" + editRecipeDescriptor
                + '}';
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
     * @throws CommandException displays invalid recipe index error message.
     */
    private Recipe getRecipe(List<Recipe> lastShownList, Index recipeIndex) throws CommandException {
        int zeroBasedIndex = recipeIndex.getZeroBased();

        if (zeroBasedIndex >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_INDEX);
        }

        if (zeroBasedIndex < lastShownList.size()) {
            return lastShownList.get(zeroBasedIndex);
        }

        throw new CommandException(String.format(Messages.MESSAGE_INVALID_RECIPE_INDEX));
    }

    /**
     * Stores the details to edit the recipe with. Each non-empty field value will replace the
     * corresponding field value of the recipe.
     */
    public static class EditRecipeDescriptor {
        private Name name;
        private CompletionTime completionTime;
        private ServingSize servingSize;
        private List<Ingredient> ingredients;
        private List<Step> steps;
        private Set<Tag> tags;

        public EditRecipeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRecipeDescriptor(EditRecipeDescriptor toCopy) {
            setName(toCopy.name);
            setCompletionTime(toCopy.completionTime);
            setServingSize(toCopy.servingSize);
            setIngredients(toCopy.ingredients);
            setSteps(toCopy.steps);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, completionTime, servingSize, ingredients, steps, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setCompletionTime(CompletionTime completionTime) {
            this.completionTime = completionTime;
        }

        public Optional<CompletionTime> getCompletionTime() {
            return Optional.ofNullable(completionTime);
        }

        public void setServingSize(ServingSize servingSize) {
            this.servingSize = servingSize;
        }

        public Optional<ServingSize> getServingSize() {
            return Optional.ofNullable(servingSize);
        }

        public void setIngredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
        }

        public Optional<List<Ingredient>> getIngredients() {
            return Optional.ofNullable(ingredients);
        }

        public void setSteps(List<Step> steps) {
            this.steps = steps;
        }

        public Optional<List<Step>> getSteps() {
            return Optional.ofNullable(steps);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public String toString() {
            return "EditRecipeDescriptor{"
                    + "name=" + name
                    + ", completionTime=" + completionTime
                    + ", servingSize=" + servingSize
                    + ", ingredients=" + ingredients
                    + ", steps=" + steps
                    + ", tags=" + tags
                    + '}';
        }

        @Override
        public boolean equals(Object other) {
            // instanceof handles nulls
            if (!(other instanceof EditRecipeDescriptor)) {
                return false;
            }

            // short circuit if same object
            if (other == this) {
                return true;
            }

            // state check
            EditRecipeDescriptor e = (EditRecipeDescriptor) other;

            return getName().equals(e.getName())
                    && getCompletionTime().equals(e.getCompletionTime())
                    && getServingSize().equals(e.getServingSize())
                    && getIngredients().equals(e.getIngredients())
                    && getSteps().equals(e.getSteps())
                    && getTags().equals(e.getTags());
        }
    }
}

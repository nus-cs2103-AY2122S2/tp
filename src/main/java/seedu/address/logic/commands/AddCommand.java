package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_COMPLETION_TIME;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_SERVING_SIZE;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_STEP;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;

/**
 * Adds a recipe to the recipe book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a recipe to the recipe book.\n\n"
            + "Parameters:\n"
            + PREFIX_NAME + "NAME\n"
            + PREFIX_COMPLETION_TIME + "COMPLETION TIME\n"
            + PREFIX_SERVING_SIZE + "SERVING SIZE\n"
            + "[" + PREFIX_INGREDIENT + "INGREDIENT]...\n"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "[" + PREFIX_STEP + "STEP]...\n\n"
            + "Example: " + COMMAND_WORD + " "
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

    public static final String MESSAGE_SUCCESS = "New recipe added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the recipe book";

    private final Recipe toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Recipe}
     */
    public AddCommand(Recipe recipe) {
        requireNonNull(recipe);
        toAdd = recipe;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasRecipe(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.addRecipe(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object o) {
        // instanceof handles nulls
        if (!(o instanceof AddCommand)) {
            return false;
        }

        // short circuit if same object
        if (this == o) {
            return true;
        }

        AddCommand other = (AddCommand) o;
        return toAdd.equals(other.toAdd); // state check
    }

    @Override
    public String toString() {
        return "AddCommand{"
                + "toAdd="
                + toAdd + '}';
    }
}

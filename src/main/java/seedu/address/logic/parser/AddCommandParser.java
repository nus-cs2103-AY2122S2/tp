package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_COMPLETION_TIME;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_SERVING_SIZE;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_STEP;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.CompletionTime;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.ServingSize;
import seedu.address.model.recipe.Step;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of user input in the context of the AddCommand
     * and returns a AddCommand object for execution.
     *
     * @param userInput the string name of the recipe to be viewed.
     * @return the AddCommand object that adds a new {@code Recipe} into the RecipeBook.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public AddCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_COMPLETION_TIME, PREFIX_SERVING_SIZE,
                        PREFIX_INGREDIENT, PREFIX_TAG, PREFIX_STEP);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_COMPLETION_TIME, PREFIX_SERVING_SIZE,
                PREFIX_INGREDIENT, PREFIX_STEP)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = RecipeBookParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        CompletionTime completionTime =
                RecipeBookParserUtil.parseCompletionTime(argMultimap.getValue(PREFIX_COMPLETION_TIME).get());
        ServingSize servingSize =
                RecipeBookParserUtil.parseServingSize(argMultimap.getValue(PREFIX_SERVING_SIZE).get());
        List<Ingredient> ingredients =
                RecipeBookParserUtil.parseIngredients(argMultimap.getAllValues(PREFIX_INGREDIENT));
        List<Step> steps =
                RecipeBookParserUtil.parseSteps(argMultimap.getAllValues(PREFIX_STEP));
        Set<Tag> tagList = RecipeBookParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));


        Recipe recipe = new Recipe(name, completionTime, servingSize, ingredients, steps, tagList);

        return new AddCommand(recipe);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

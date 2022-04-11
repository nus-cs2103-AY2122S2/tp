package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_RECIPE_INDEX_OR_NAME;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_COMPLETION_TIME;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_SERVING_SIZE;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_STEP;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.CompletionTime;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.ServingSize;
import seedu.address.model.recipe.Step;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        ArgumentMultimap argMultimap;
        Prefix prefix = userInput.contains(PREFIX_INDEX.getPrefix())
                ? PREFIX_INDEX
                : PREFIX_NAME;

        argMultimap = getArgumentMultiMap(userInput);

        try {
            return getEditCommand(prefix, argMultimap);
        } catch (ParseException pe) {
            throw pe;
        }
    }

    /**
     * Extract the correct set of tokens/prefixes from the userInput
     * based on whether user input is searching for recipe by index or name
     *
     * @param userInput input from user
     * @return {@code ArgumentMultiMap} with the correct tokens/prefixes extracted
     */
    private ArgumentMultimap getArgumentMultiMap(String userInput) {
        if (userInput.contains(PREFIX_INDEX.getPrefix())) {
            return ArgumentTokenizer.tokenize(userInput, PREFIX_INDEX, PREFIX_NAME, PREFIX_COMPLETION_TIME,
                    PREFIX_SERVING_SIZE, PREFIX_INGREDIENT, PREFIX_TAG, PREFIX_STEP);
        } else {
            return ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_COMPLETION_TIME,
                    PREFIX_SERVING_SIZE, PREFIX_INGREDIENT, PREFIX_TAG, PREFIX_STEP);
        }
    }

    /**
     * Returns EditCommand with the correct constructor based on whether
     * user input is search for recipe by index or name
     *
     * @param prefix prefix used by user to determine if searching by index or name
     * @param argMultimap {@code ArgumentMultiMap} used to extract tokens/prefixes from user input
     * @return {@code EditCommand} instantiated with the correct constructor
     * @throws ParseException if the user input does not conform the expected format
     */
    private EditCommand getEditCommand(Prefix prefix, ArgumentMultimap argMultimap) throws ParseException {
        if (prefix.equals(PREFIX_INDEX)) {
            Index index = RecipeBookParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).orElseThrow());
            EditRecipeDescriptor editRecipeDescriptor = parseEditArgumentValues(argMultimap);
            return new EditCommand(index, editRecipeDescriptor);
        } else {
            Name name = parseNameOrThrow(argMultimap.getPreamble());
            EditRecipeDescriptor editRecipeDescriptor = parseEditArgumentValues(argMultimap);
            return new EditCommand(name, editRecipeDescriptor);
        }
    }

    private Name parseNameOrThrow(String preamble) throws ParseException {
        try {
            Name name = RecipeBookParserUtil.parseName(preamble);
            return name;
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_MISSING_RECIPE_INDEX_OR_NAME);
        }
    }

    private EditRecipeDescriptor parseEditArgumentValues(ArgumentMultimap argMultimap) throws ParseException {
        EditRecipeDescriptor editRecipeDescriptor = new EditRecipeDescriptor();

        parseAndSetName(editRecipeDescriptor, argMultimap);
        parseAndSetCompletionTime(editRecipeDescriptor, argMultimap);
        parseAndSetServingSize(editRecipeDescriptor, argMultimap);
        parseAndSetIngredients(editRecipeDescriptor, argMultimap);
        parseAndSetSteps(editRecipeDescriptor, argMultimap);
        parseAndSetTags(editRecipeDescriptor, argMultimap);

        if (!editRecipeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return editRecipeDescriptor;
    }

    private void parseAndSetName(EditRecipeDescriptor editRecipeDescriptor,
                                                 ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Name parsedName = RecipeBookParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            editRecipeDescriptor.setName(parsedName);
        }
    }

    private void parseAndSetServingSize(EditRecipeDescriptor editRecipeDescriptor,
                                 ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_SERVING_SIZE).isPresent()) {
            ServingSize parsedServingSize =
                    RecipeBookParserUtil.parseServingSize(argMultimap.getValue(PREFIX_SERVING_SIZE).get());
            editRecipeDescriptor.setServingSize(parsedServingSize);
        }
    }

    private void parseAndSetCompletionTime(EditRecipeDescriptor editRecipeDescriptor,
                                           ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_COMPLETION_TIME).isPresent()) {
            CompletionTime parsedCompletionTime =
                    RecipeBookParserUtil.parseCompletionTime(argMultimap.getValue(PREFIX_COMPLETION_TIME).get());
            editRecipeDescriptor.setCompletionTime(parsedCompletionTime);
        }
    }

    private void parseAndSetIngredients(EditRecipeDescriptor editRecipeDescriptor,
                                           ArgumentMultimap argMultimap) throws ParseException {
        if (!argMultimap.getAllValues(PREFIX_INGREDIENT).isEmpty()) {
            List<Ingredient> parsedIngredients =
                    RecipeBookParserUtil.parseIngredients(argMultimap.getAllValues(PREFIX_INGREDIENT));
            editRecipeDescriptor.setIngredients(parsedIngredients);
        }
    }

    private void parseAndSetSteps(EditRecipeDescriptor editRecipeDescriptor,
                                        ArgumentMultimap argMultimap) throws ParseException {
        if (!argMultimap.getAllValues(PREFIX_STEP).isEmpty()) {
            List<Step> parsedSteps =
                    RecipeBookParserUtil.parseSteps(argMultimap.getAllValues(PREFIX_STEP));
            editRecipeDescriptor.setSteps(parsedSteps);
        }
    }

    private void parseAndSetTags(EditRecipeDescriptor editRecipeDescriptor,
                                  ArgumentMultimap argMultimap) throws ParseException {
        if (!argMultimap.getAllValues(PREFIX_TAG).isEmpty()) {
            Set<Tag> parsedTags = RecipeBookParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            editRecipeDescriptor.setTags(parsedTags);
        }
    }
}

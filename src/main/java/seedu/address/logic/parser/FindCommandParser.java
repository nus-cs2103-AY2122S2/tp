package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.RecipeContainsKeywordPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of user input in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @param userInput the string name of the recipe to be viewed.
     * @return the FindCommand object that filters the list of recipes based on the given user input(s).
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public FindCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split(RecipeBookSyntax.SEPARATOR_SYMBOL);
        List<String> trimmedKeywords = Arrays.stream(keywords)
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());

        return new FindCommand(new RecipeContainsKeywordPredicate(trimmedKeywords));
    }
}

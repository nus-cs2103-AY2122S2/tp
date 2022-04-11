package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Name;


/**
 * Parses input arguments and creates a new ViewCommand object.
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of user input in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     *
     * @param userInput the string name of the recipe to be viewed.
     * @return the ViewCommand object that displays the contents of the appropriate recipe.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public ViewCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        // Prefix defaults to name if indexFlag is not specified
        Prefix prefix = userInput.contains(PREFIX_INDEX.getPrefix())
                ? PREFIX_INDEX
                : PREFIX_NAME;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, prefix);

        try {
            if (prefix.equals(PREFIX_INDEX)) {
                Index index = RecipeBookParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
                return new ViewCommand(index);
            } else {
                Name name = RecipeBookParserUtil.parseName(argMultimap.getPreamble());
                return new ViewCommand(name);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        }
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MORE_TAGS_THAN_EXPECTED;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        String[] tagKeywords = trimmedArgs.split("\\s+");

        if (tagKeywords.length > 1) {
            throw new ParseException(
                    String.format(MESSAGE_MORE_TAGS_THAN_EXPECTED, FilterCommand.MESSAGE_USAGE));
        }

        if (!Tag.isValidTagName(tagKeywords[0])) {
            throw new ParseException(
                    String.format(FilterCommand.MESSAGE_CONSTRAINTS)
            );
        }

        // because we only allow ONE tag.
        String tagToFind = tagKeywords[0];
        return new FilterCommand(new NameContainsTagPredicate(tagToFind));
    }
}

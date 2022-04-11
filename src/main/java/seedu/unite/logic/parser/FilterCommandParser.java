package seedu.unite.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.unite.commons.exceptions.IllegalValueException;
import seedu.unite.logic.commands.FilterCommand;
import seedu.unite.logic.parser.exceptions.ParseException;
import seedu.unite.model.person.PersonContainsTagPredicate;
import seedu.unite.model.tag.Tag;


/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_TAG);
        Tag tag;

        try {
            tag = ParserUtil.parseTag(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterCommand.MESSAGE_USAGE), ive);
        }

        return new FilterCommand(new PersonContainsTagPredicate(tag), tag);
    }


}

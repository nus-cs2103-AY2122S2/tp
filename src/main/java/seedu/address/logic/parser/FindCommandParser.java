package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FriendFilterPredicate;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.LogName;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TITLE, PREFIX_TAG);

        boolean isAnyArgumentGiven = argMultimap.arePrefixesPresent(PREFIX_NAME)
                || argMultimap.arePrefixesPresent(PREFIX_TITLE)
                || argMultimap.arePrefixesPresent(PREFIX_TAG);

        if (!isAnyArgumentGiven || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Set<FriendName> nameKeywords = ParserUtil.parseFriendNames(argMultimap.getAllValues(PREFIX_NAME));

        Set<LogName> logTitleKeywords = ParserUtil.parseTitles(argMultimap.getAllValues(PREFIX_TITLE));

        Set<Tag> tagKeywords = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new FindCommand(new FriendFilterPredicate(nameKeywords, logTitleKeywords, tagKeywords));
    }

}

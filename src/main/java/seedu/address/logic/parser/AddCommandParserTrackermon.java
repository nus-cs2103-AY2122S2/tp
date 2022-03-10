package seedu.address.logic.parser;

import static seedu.address.commons.core.MessagesTrackermon.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxTrackermon.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxTrackermon.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntaxTrackermon.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommandTrackermon;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.show.Name;
import seedu.address.model.show.Show;
import seedu.address.model.show.Status;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParserTrackermon implements ParserTrackermon<AddCommandTrackermon> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommandTrackermon parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STATUS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STATUS, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommandTrackermon.MESSAGE_USAGE));
        }

        Name name = ParserUtilTrackermon.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Status status = ParserUtilTrackermon.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        Set<Tag> tagList = ParserUtilTrackermon.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Show show = new Show(name, status, tagList);

        return new AddCommandTrackermon(show);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

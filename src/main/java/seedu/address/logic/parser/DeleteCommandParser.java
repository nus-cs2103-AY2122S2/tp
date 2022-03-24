package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PLAYER, PREFIX_LINEUP);

        if (arePrefixesPresent(argMultimap, PREFIX_PLAYER, PREFIX_LINEUP)) {
            // delete player from lineup
            // for now, we assume that there is only one team
            Name person = ParserUtil.parsePlayer(argMultimap.getValue(PREFIX_PLAYER).get());
            LineupName lineup = ParserUtil.parseLineupName(argMultimap.getValue(PREFIX_LINEUP).get());
            return new DeleteCommand(person, lineup);
        } else if (arePrefixesPresent(argMultimap, PREFIX_PLAYER)) {
            Name person = ParserUtil.parsePlayer(argMultimap.getValue(PREFIX_PLAYER).get());
            return new DeleteCommand(person);
        } else if (arePrefixesPresent(argMultimap, PREFIX_LINEUP)) {
            LineupName lineup = ParserUtil.parseLineupName(argMultimap.getValue(PREFIX_LINEUP).get());
            return new DeleteCommand(lineup);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

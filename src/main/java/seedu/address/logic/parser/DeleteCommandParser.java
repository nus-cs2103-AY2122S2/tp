package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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
                ArgumentTokenizer.tokenize(args, PREFIX_PLAYER, PREFIX_LINEUP, PREFIX_TEAM);

        if (arePrefixesPresent(argMultimap, PREFIX_PLAYER, PREFIX_LINEUP, PREFIX_TEAM)) {
            // delete player from lineup
            // for now, we assume that there is only one team
            String person = ParserUtil.parseString(argMultimap.getValue(PREFIX_PLAYER).get());
            String lineup = ParserUtil.parseString(argMultimap.getValue(PREFIX_LINEUP).get());
            String team = ParserUtil.parseString(argMultimap.getValue(PREFIX_TEAM).get());
            return new DeleteCommand(DeleteCommand.DELETE_PLAYER_FROM_LINEUP, person, lineup, team);
        } else if (arePrefixesPresent(argMultimap, PREFIX_PLAYER, PREFIX_TEAM)) {
            String person = ParserUtil.parseString(argMultimap.getValue(PREFIX_PLAYER).get());
            String team = ParserUtil.parseString(argMultimap.getValue(PREFIX_TEAM).get());
            return new DeleteCommand(DeleteCommand.DELETE_PLAYER_FROM_TEAM, person, team);
        } else if (arePrefixesPresent(argMultimap, PREFIX_PLAYER)) {
            String person = ParserUtil.parseString(argMultimap.getValue(PREFIX_PLAYER).get());
            return new DeleteCommand(DeleteCommand.DELETE_PLAYER_FROM_POOL, person);
        } else if (arePrefixesPresent(argMultimap, PREFIX_LINEUP, PREFIX_TEAM)) {
            String lineup = ParserUtil.parseString(argMultimap.getValue(PREFIX_LINEUP).get());
            String team = ParserUtil.parseString(argMultimap.getValue(PREFIX_TEAM).get());
            return new DeleteCommand(DeleteCommand.DELETE_LINEUP_FROM_TEAM, lineup, team);
        } else if (arePrefixesPresent(argMultimap, PREFIX_TEAM)) {
            String team = ParserUtil.parseString(argMultimap.getValue(PREFIX_TEAM).get());
            return new DeleteCommand(DeleteCommand.DELETE_TEAM_FROM_POOL, team);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

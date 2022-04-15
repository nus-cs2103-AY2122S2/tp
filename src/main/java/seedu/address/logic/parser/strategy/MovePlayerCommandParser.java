package seedu.address.logic.parser.strategy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_XCOORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YCOORD;

import java.util.stream.Stream;

import seedu.address.logic.commands.strategy.MovePlayerCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.strategy.Player;

/**
 * Parses input arguments and creates a new MovePlayerCommand object.
 */
public class MovePlayerCommandParser implements Parser<MovePlayerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MovePlayerCommand
     * and returns an MovePlayerCommand object for execution.
     *
     * @return an MovePlayerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public MovePlayerCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_XCOORD, PREFIX_YCOORD);
        if (!arePrefixesPresent(argMultimap, PREFIX_XCOORD, PREFIX_YCOORD)
            || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MovePlayerCommand.MESSAGE_USAGE));
        }

        try {
            int xCoord = Integer.parseInt(argMultimap.getValue(PREFIX_XCOORD).get());
            int yCoord = Integer.parseInt(argMultimap.getValue(PREFIX_YCOORD).get());
            String playerName = argMultimap.getPreamble();

            if (!Player.isValidXCoord(xCoord)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        MovePlayerCommand.MESSAGE_USAGE));
            }

            if (!Player.isValidYCoord(yCoord)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        MovePlayerCommand.MESSAGE_USAGE));
            }

            return new MovePlayerCommand(playerName, xCoord, yCoord);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MovePlayerCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

package seedu.address.logic.parser.position;

import seedu.address.logic.commands.position.AddPositionCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.position.*;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddPositionCommandParser implements Parser<AddPositionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPositionCommand
     * and returns an AddPositionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public AddPositionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_POSITION, PREFIX_NUM_OPENINGS, PREFIX_DESCRIPTION,
                        PREFIX_REQUIREMENT);

        if (!arePrefixesPresent(argMultiMap, PREFIX_POSITION, PREFIX_NUM_OPENINGS, PREFIX_DESCRIPTION,
                PREFIX_REQUIREMENT) || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPositionCommand.MESSAGE_USAGE));
        }

        PositionName positionName = ParserUtil.parsePositionName(argMultiMap.getValue(PREFIX_POSITION).get());
        PositionOpenings positionOpenings = ParserUtil.parseOpenings(argMultiMap.getValue(PREFIX_NUM_OPENINGS).get());
        Description description = ParserUtil.parseDescription(argMultiMap.getValue(PREFIX_DESCRIPTION).get());
        Set<Requirement> requirements = ParserUtil.parseRequirements(argMultiMap.getAllValues(PREFIX_REQUIREMENT));

        Position position = new Position(positionName, description, positionOpenings, requirements);

        return new AddPositionCommand(position);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnmarkCommandParser implements Parser<UnmarkCommand> {
    public static final int GET_CLIENT_INDEX = 0;
    public static final int GET_PROCEDURE_INDEX = 1;

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkCommand
     * and returns an UnmarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args);
        List<Index> indexes = new ArrayList<>();
        try {
            indexes.addAll(ParserUtil.parseIndexes(argumentMultimap.getPreamble()));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnmarkCommand.MESSAGE_USAGE), pe);
        }
        if (indexes.size() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnmarkCommand.MESSAGE_USAGE));
        }

        return new UnmarkCommand(indexes.get(GET_CLIENT_INDEX), indexes.get(GET_PROCEDURE_INDEX));
    }
}

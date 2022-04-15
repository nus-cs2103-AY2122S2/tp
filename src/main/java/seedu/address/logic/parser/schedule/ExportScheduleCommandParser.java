package seedu.address.logic.parser.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedule.ExportScheduleCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input argument and creates a new ExportScheduleCommand object.
 */
public class ExportScheduleCommandParser implements Parser<ExportScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns an ExportCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExportScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index targetIndex;
        try {
            targetIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new ExportScheduleCommand(targetIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ExportScheduleCommand.MESSAGE_USAGE), pe);
        }

    }
}

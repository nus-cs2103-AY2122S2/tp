package seedu.address.logic.parser.schedule;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedule.AddScheduleCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDateTime;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class EditScheduleCommandParser implements Parser<AddScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddScheduleCommand parse(String args) throws ParseException {
        String[] input;
        Index index;
        try {
            input = this.parseDelimitedCommand(args);
            index = ParserUtil.parseIndex(input[0]);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScheduleCommand.MESSAGE_USAGE), pe);
        }
        final LocalDateTime interviewSlot = ParserUtil.parseDateTime(input[1]);
        return new AddScheduleCommand(index, interviewSlot);
    }

    /**
     * Validates a 2-part command separated by a delimiter and returns the parts if command is valid.
     *
     * @param command The 2-part command to be validated.
     * @return The 2 parts of the command in a String array.
     * @throws ParseException If the command is not in a valid format.
     */
    private String[] parseDelimitedCommand(String command)
            throws ParseException {
        final String[] validDelimiter = command.split(" /at ");
        if (validDelimiter.length < 2) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return validDelimiter;
    }

}

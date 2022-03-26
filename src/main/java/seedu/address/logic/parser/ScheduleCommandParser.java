package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.schedule.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.schedule.AddScheduleCommandParser;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ScheduleCommand parse(String args) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        final String scheduleCommandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (scheduleCommandWord) {
        case "add":
            return new AddScheduleCommandParser().parse(arguments);
        /*case "edit":
            return new EditScheduleCommandParser().parse(arguments);
        case "delete":
            return new DeleteScheduleCommandParser().parse(arguments);*/
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }
    }
}

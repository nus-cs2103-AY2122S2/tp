package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.meeting.AddMeetingCommand;
import seedu.address.logic.commands.meeting.ArchiveMeetingCommand;
import seedu.address.logic.commands.meeting.DeleteMeetingCommand;
import seedu.address.logic.commands.meeting.EditMeetingCommand;
import seedu.address.logic.commands.meeting.FindMeetingCommand;
import seedu.address.logic.commands.meeting.ListMeetingCommand;
import seedu.address.logic.commands.meeting.OpenMeetingCommand;
import seedu.address.logic.commands.module.AddModuleCommand;
import seedu.address.logic.commands.module.DeleteModuleCommand;
import seedu.address.logic.commands.module.EditModuleCommand;
import seedu.address.logic.commands.module.ListModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.meeting.AddMeetingCommandParser;
import seedu.address.logic.parser.meeting.DeleteMeetingCommandParser;
import seedu.address.logic.parser.meeting.EditMeetingCommandParser;
import seedu.address.logic.parser.meeting.FindMeetingCommandParser;
import seedu.address.logic.parser.meeting.OpenMeetingCommandParser;
import seedu.address.logic.parser.module.AddModuleCommandParser;
import seedu.address.logic.parser.module.DeleteModuleCommandParser;
import seedu.address.logic.parser.module.EditModuleCommandParser;

/**
 * Parses user input.
 */
public class LinkyTimeParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        // Meeting Commands
        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommandParser().parse(arguments);
        case ArchiveMeetingCommand.COMMAND_WORD:
            return new ArchiveMeetingCommand();
        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommandParser().parse(arguments);
        case EditMeetingCommand.COMMAND_WORD:
            return new EditMeetingCommandParser().parse(arguments);
        case ListMeetingCommand.COMMAND_WORD:
            return new ListMeetingCommand();
        case FindMeetingCommand.COMMAND_WORD:
            return new FindMeetingCommandParser().parse(arguments);
        case OpenMeetingCommand.COMMAND_WORD:
            return new OpenMeetingCommandParser().parse(arguments);

        // Module Commands
        case AddModuleCommand.COMMAND_WORD:
            return new AddModuleCommandParser().parse(arguments);
        case DeleteModuleCommand.COMMAND_WORD:
            return new DeleteModuleCommandParser().parse(arguments);
        case EditModuleCommand.COMMAND_WORD:
            return new EditModuleCommandParser().parse(arguments);
        case ListModuleCommand.COMMAND_WORD:
            return new ListModuleCommand();

        // System Commands
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

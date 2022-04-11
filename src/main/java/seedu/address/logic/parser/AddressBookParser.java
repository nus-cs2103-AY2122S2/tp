package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.commands.AddPreferenceCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ContactedCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.commands.DeletePreferenceCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.TutorialCommand;
import seedu.address.logic.commands.ViewClientCommand;
import seedu.address.logic.commands.meeting.AddMeetingCommand;
import seedu.address.logic.commands.meeting.CloseMeetingCommand;
import seedu.address.logic.commands.meeting.DeleteMeetingCommand;
import seedu.address.logic.commands.meeting.EditMeetingCommand;
import seedu.address.logic.commands.meeting.ListMeetingCommand;
import seedu.address.logic.commands.policy.AddPolicyCommand;
import seedu.address.logic.commands.policy.DeletePolicyCommand;
import seedu.address.logic.commands.policy.EditPolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.meeting.AddMeetingCommandParser;
import seedu.address.logic.parser.meeting.CloseMeetingCommandParser;
import seedu.address.logic.parser.meeting.DeleteMeetingCommandParser;
import seedu.address.logic.parser.meeting.EditMeetingCommandParser;
import seedu.address.logic.parser.meeting.ListMeetingCommandParser;
import seedu.address.logic.parser.policy.AddPolicyCommandParser;
import seedu.address.logic.parser.policy.DeletePolicyCommandParser;
import seedu.address.logic.parser.policy.EditPolicyCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

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

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ContactedCommand.COMMAND_WORD:
            return new ContactedCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case TutorialCommand.COMMAND_WORD:
            return new TutorialCommand();

        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommandParser().parse(arguments);

        case ListMeetingCommand.COMMAND_WORD:
            return new ListMeetingCommandParser().parse(arguments);

        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommandParser().parse(arguments);

        case EditMeetingCommand.COMMAND_WORD:
            return new EditMeetingCommandParser().parse(arguments);

        case AddPolicyCommand.COMMAND_WORD:
            return new AddPolicyCommandParser().parse(arguments);

        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);

        case AddPreferenceCommand.COMMAND_WORD:
            return new AddPreferenceCommandParser().parse(arguments);

        case DeletePreferenceCommand.COMMAND_WORD:
            return new DeletePreferenceCommandParser().parse(arguments);

        case DeletePolicyCommand.COMMAND_WORD:
            return new DeletePolicyCommandParser().parse(arguments);

        case EditPolicyCommand.COMMAND_WORD:
            return new EditPolicyCommandParser().parse(arguments);

        case ViewClientCommand.COMMAND_WORD:
            return new ViewClientCommandParser().parse(arguments);

        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case CloseMeetingCommand.COMMAND_WORD:
            return new CloseMeetingCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

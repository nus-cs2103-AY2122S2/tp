package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCsvCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.help.HelpCommand;
import seedu.address.logic.commands.interview.AcceptInterviewCommand;
import seedu.address.logic.commands.interview.FailInterviewCommand;
import seedu.address.logic.commands.interview.PassInterviewCommand;
import seedu.address.logic.commands.interview.RejectInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.help.HelpCommandParser;
import seedu.address.logic.parser.interview.AcceptInterviewCommandParser;
import seedu.address.logic.parser.interview.FailInterviewCommandParser;
import seedu.address.logic.parser.interview.PassInterviewCommandParser;
import seedu.address.logic.parser.interview.RejectInterviewCommandParser;

/**
 * Parses user input.
 */
public class HireLahParser {

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

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case PassInterviewCommand.COMMAND_WORD:
            return new PassInterviewCommandParser().parse(arguments);

        case FailInterviewCommand.COMMAND_WORD:
            return new FailInterviewCommandParser().parse(arguments);

        case AcceptInterviewCommand.COMMAND_WORD:
            return new AcceptInterviewCommandParser().parse(arguments);

        case RejectInterviewCommand.COMMAND_WORD:
            return new RejectInterviewCommandParser().parse(arguments);
        case ExportCsvCommand.COMMAND_WORD:
            return new ExportCsvCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

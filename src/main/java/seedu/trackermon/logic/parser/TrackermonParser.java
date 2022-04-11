package seedu.trackermon.logic.parser;

import static seedu.trackermon.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.trackermon.logic.commands.AddCommand;
import seedu.trackermon.logic.commands.ClearCommand;
import seedu.trackermon.logic.commands.Command;
import seedu.trackermon.logic.commands.DeleteCommand;
import seedu.trackermon.logic.commands.EditCommand;
import seedu.trackermon.logic.commands.ExitCommand;
import seedu.trackermon.logic.commands.ExportCommand;
import seedu.trackermon.logic.commands.FindCommand;
import seedu.trackermon.logic.commands.HelpCommand;
import seedu.trackermon.logic.commands.ImportCommand;
import seedu.trackermon.logic.commands.ListCommand;
import seedu.trackermon.logic.commands.SortCommand;
import seedu.trackermon.logic.commands.SuggestCommand;
import seedu.trackermon.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TrackermonParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_UNKNOWN_COMMAND,
                    HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ImportCommand.COMMAND_WORD:
            return new ImportCommand();

        case ExportCommand.COMMAND_WORD:
            return new ExportCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case SuggestCommand.COMMAND_WORD:
            return new SuggestCommand();

        default:
            throw new ParseException(String.format(MESSAGE_UNKNOWN_COMMAND,
                    HelpCommand.MESSAGE_USAGE));
        }
    }

}

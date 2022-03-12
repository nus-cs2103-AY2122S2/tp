package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.contax.logic.commands.AddAppointmentCommand;
import seedu.contax.logic.commands.AddCommand;
import seedu.contax.logic.commands.AddTagCommand;
import seedu.contax.logic.commands.ChainCommand;
import seedu.contax.logic.commands.ClearCommand;
import seedu.contax.logic.commands.Command;
import seedu.contax.logic.commands.DeleteAppointmentCommand;
import seedu.contax.logic.commands.DeleteCommand;
import seedu.contax.logic.commands.DeleteTagCommand;
import seedu.contax.logic.commands.EditAppointmentCommand;
import seedu.contax.logic.commands.EditCommand;
import seedu.contax.logic.commands.ExitCommand;
import seedu.contax.logic.commands.FindCommand;
import seedu.contax.logic.commands.HelpCommand;
import seedu.contax.logic.commands.ImportCsvCommand;
import seedu.contax.logic.commands.ListAppointmentCommand;
import seedu.contax.logic.commands.ListCommand;
import seedu.contax.logic.commands.ListTagCommand;
import seedu.contax.logic.parser.exceptions.ParseException;

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

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        // Import CSV Command
        case ImportCsvCommand.COMMAND_WORD:
            return new ImportCsvParser().parse(arguments);

        // Appointment commands
        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case ListAppointmentCommand.COMMAND_WORD:
            return new ListAppointmentCommand();

        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parse(arguments);

        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);

        // Tag management commands
        case AddTagCommand.COMMAND_WORD:
            return new AddTagCommandParser().parse(arguments);

        case ChainCommand.COMMAND_WORD:
            return new ChainCommandParser().parse(arguments);

        case ListTagCommand.COMMAND_WORD:
            return new ListTagCommand();

        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CopyEmailsCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.NextCommand;
import seedu.address.logic.commands.PreviousCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.ScrubCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final Logger LOGGER = LogsCenter.getLogger(AddressBookParser.class.getName());

    /**
     * Parses user input into command for execution.
     *
     * @param userInput Full user input string.
     * @return The command based on the user input.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        LOGGER.log(Level.INFO, "Executing AddressBookParser#parseCommand(String)");
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            LOGGER.log(Level.INFO, "Invalid command format");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to AddCommandParser");
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to EditCommandParser");
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to DeleteCommandParser");
            return new DeleteCommandParser().parse(arguments);

        case ScrubCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to ScrubCommandParser");
            return new ScrubCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to FindCommandParser");
            return new FindCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            requireEmpty(arguments, ClearCommand.MESSAGE_USAGE);
            LOGGER.log(Level.INFO, "Parsed to ClearCommand");
            return new ClearCommand();

        case ListCommand.COMMAND_WORD:
            requireEmpty(arguments, ListCommand.MESSAGE_USAGE);
            LOGGER.log(Level.INFO, "Parsed to ListCommand");
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            requireEmpty(arguments, ExitCommand.MESSAGE_USAGE);
            LOGGER.log(Level.INFO, "Parsed to ExitCommand");
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            requireEmpty(arguments, HelpCommand.MESSAGE_USAGE);
            LOGGER.log(Level.INFO, "Parsed to HelpCommand");
            return new HelpCommand();

        case CopyEmailsCommand.COMMAND_WORD:
            requireEmpty(arguments, CopyEmailsCommand.MESSAGE_USAGE);
            LOGGER.log(Level.INFO, "Parsed to CopyEmailsCommand");
            return new CopyEmailsCommand();

        case PreviousCommand.COMMAND_WORD:
            requireEmpty(arguments, PreviousCommand.MESSAGE_USAGE);
            LOGGER.log(Level.INFO, "Parsed to PreviousCommand");
            return new PreviousCommand();

        case NextCommand.COMMAND_WORD:
            requireEmpty(arguments, NextCommand.MESSAGE_USAGE);
            LOGGER.log(Level.INFO, "Parsed to NextCommand");
            return new NextCommand();

        case UndoCommand.COMMAND_WORD:
            requireEmpty(arguments, UndoCommand.MESSAGE_USAGE);
            LOGGER.log(Level.INFO, "Parsed to UndoCommand");
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            requireEmpty(arguments, RedoCommand.MESSAGE_USAGE);
            LOGGER.log(Level.INFO, "Parsed to RedoCommand");
            return new RedoCommand();

        case AddTagCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to AddTagCommand");
            return new AddTagCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to ViewCommand");
            return new ViewCommandParser().parse(arguments);

        case DeleteTagCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to DeleteTagCommand");
            return new DeleteTagCommandParser().parse(arguments);

        default:
            LOGGER.log(Level.INFO, "Unknown command");
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Checks if the given argument is empty, otherwise throws ParseException.
     *
     * @param arguments Arguments to be checked.
     * @param messageUsage Message usage.
     * @throws ParseException If arguments is not empty.
     */
    private void requireEmpty(String arguments, String messageUsage) throws ParseException {
        if (!arguments.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, messageUsage));
        }
    }

}

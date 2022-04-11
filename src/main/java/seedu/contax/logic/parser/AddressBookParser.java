package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.contax.logic.commands.AddAppointmentCommand;
import seedu.contax.logic.commands.AddPersonCommand;
import seedu.contax.logic.commands.AddTagCommand;
import seedu.contax.logic.commands.AppointmentsBetweenCommand;
import seedu.contax.logic.commands.BatchCommand;
import seedu.contax.logic.commands.ChainCommand;
import seedu.contax.logic.commands.ClearCommand;
import seedu.contax.logic.commands.Command;
import seedu.contax.logic.commands.DeleteAppointmentCommand;
import seedu.contax.logic.commands.DeletePersonCommand;
import seedu.contax.logic.commands.DeleteTagCommand;
import seedu.contax.logic.commands.EditAppointmentCommand;
import seedu.contax.logic.commands.EditPersonCommand;
import seedu.contax.logic.commands.EditPriorityCommand;
import seedu.contax.logic.commands.EditTagCommand;
import seedu.contax.logic.commands.ExitCommand;
import seedu.contax.logic.commands.ExportCsvCommand;
import seedu.contax.logic.commands.FindAppointmentCommand;
import seedu.contax.logic.commands.FindByTagCommand;
import seedu.contax.logic.commands.FindPersonCommand;
import seedu.contax.logic.commands.FreeBetweenCommand;
import seedu.contax.logic.commands.HelpCommand;
import seedu.contax.logic.commands.ImportCsvCommand;
import seedu.contax.logic.commands.ListAppointmentCommand;
import seedu.contax.logic.commands.ListPersonCommand;
import seedu.contax.logic.commands.ListTagCommand;
import seedu.contax.logic.commands.RangeCommand;
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

        return getNonNullCommandOrElseThrow(() -> new ParseException(MESSAGE_UNKNOWN_COMMAND),
                parseGeneralCommands(commandWord, arguments),
                parsePersonCommands(commandWord, arguments),
                parseTagCommands(commandWord, arguments),
                parseAppointmentCommands(commandWord, arguments),
                parseCsvCommands(commandWord, arguments)
        );
    }

    /**
     * Iterates through the supplied {@code commands} and returns the first non-null Command.
     * If all supplied commands are null, it throws an exception supplied by {@code allNullExceptionSupplier}.
     */
    private <E extends Exception> Command getNonNullCommandOrElseThrow(Supplier<E> allNullExceptionSupplier,
                                                                         Command... commands)
            throws E {
        for (Command command : commands) {
            if (command != null) {
                return command;
            }
        }
        throw allNullExceptionSupplier.get();
    }

    /** Performs parsing of all person commands. */
    private Command parsePersonCommands(String commandWord, String arguments) throws ParseException {
        //@@author kwanhw
        switch(commandWord) {
        case AddPersonCommand.COMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case RangeCommand.COMMAND_WORD:
            return new RangeCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case FindPersonCommand.COMMAND_WORD:
            return new FindPersonCommandParser().parse(arguments);

        case ListPersonCommand.COMMAND_WORD:
            return new ListPersonCommand();

        //@@author
        default:
            return null;
        }
    }

    /** Performs parsing of all tag commands. */
    private Command parseTagCommands(String commandWord, String arguments) throws ParseException {
        //@@author kwanhw
        switch (commandWord) {
        case AddTagCommand.COMMAND_WORD:
            return new AddTagCommandParser().parse(arguments);

        case ListTagCommand.COMMAND_WORD:
            return new ListTagCommand();

        case EditTagCommand.COMMAND_WORD:
            return new EditTagCommandParser().parse(arguments);

        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        case FindByTagCommand.COMMAND_WORD:
            return new FindByTagCommandParser().parse(arguments);

        //@@author
        default:
            return null;
        }
    }

    /** Performs parsing of all appointment commands. */
    private Command parseAppointmentCommands(String commandWord, String arguments) throws ParseException {
        switch(commandWord) {
        //@@author sharpstorm
        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);
        case ListAppointmentCommand.COMMAND_WORD:
            return new ListAppointmentCommand();
        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parse(arguments);
        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case AppointmentsBetweenCommand.COMMAND_WORD:
            return new AppointmentsBetweenCommandParser().parse(arguments);
        case FreeBetweenCommand.COMMAND_WORD:
            return new FreeBetweenCommandParser().parse(arguments);

        //@@author HanJiyao
        case EditPriorityCommand.COMMAND_WORD:
            return new EditPriorityCommandParser().parse(arguments);
        //@@author
        //@@author Wei-Xinn
        case FindAppointmentCommand.COMMAND_WORD:
            return new FindAppointmentCommandParser().parse(arguments);
        //@@author

        default:
            return null;
        }
    }

    /** Performs parsing of all csv commands. */
    private Command parseCsvCommands(String commandWord, String arguments) throws ParseException {
        //@@author dandaandaaaaaan
        switch(commandWord) {

        // Import CSV Command
        case ImportCsvCommand.COMMAND_WORD:
            return new ImportCsvParser().parse(arguments);

        // Export CSV Command
        case ExportCsvCommand.COMMAND_WORD:
            return new ExportCsvCommand();

        //@@author
        default:
            return null;
        }
    }

    /** Performs parsing of all commands that do not fall into a specific functional category. */
    private Command parseGeneralCommands(String commandWord, String arguments) throws ParseException {
        switch(commandWord) {

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        //@@author HanJiyao
        // Command chaining
        case ChainCommand.COMMAND_WORD:
            return new ChainCommandParser().parse(arguments);

        // Batch Command
        case BatchCommand.COMMAND_WORD:
            return new BatchCommandParser().parse(arguments);
        //@@author

        default:
            return null;
        }
    }
}

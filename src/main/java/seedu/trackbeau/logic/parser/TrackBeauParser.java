package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.trackbeau.logic.commands.ClearCommand;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.ExitCommand;
import seedu.trackbeau.logic.commands.HelpCommand;
import seedu.trackbeau.logic.commands.booking.AddBookingCommand;
import seedu.trackbeau.logic.commands.booking.DeleteBookingCommand;
import seedu.trackbeau.logic.commands.customer.AddCustomerCommand;
import seedu.trackbeau.logic.commands.customer.DeleteCustomerCommand;
import seedu.trackbeau.logic.commands.customer.EditCustomerCommand;
import seedu.trackbeau.logic.commands.customer.FindCustomerCommand;
import seedu.trackbeau.logic.commands.customer.ListCustomersCommand;
import seedu.trackbeau.logic.commands.service.AddServiceCommand;
import seedu.trackbeau.logic.commands.service.ListServicesCommand;
import seedu.trackbeau.logic.parser.booking.AddBookingCommandParser;
import seedu.trackbeau.logic.parser.booking.DeleteBookingCommandParser;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.logic.parser.service.AddServiceCommandParser;

/**
 * Parses user input.
 */
public class TrackBeauParser {

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
        case ListServicesCommand.COMMAND_WORD:
            return new ListServicesCommand();

        case AddServiceCommand.COMMAND_WORD:
            return new AddServiceCommandParser().parse(arguments);

        case AddBookingCommand.COMMAND_WORD:
            return new AddBookingCommandParser().parse(arguments);

        case DeleteBookingCommand.COMMAND_WORD:
            return new DeleteBookingCommandParser().parse(arguments);

        case AddCustomerCommand.COMMAND_WORD:
            return new AddCustomerCommandParser().parse(arguments);

        case EditCustomerCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCustomerCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCustomerCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCustomersCommand.COMMAND_WORD:
            return new ListCustomersCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

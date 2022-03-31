package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.trackbeau.logic.commands.ClearCommand;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.ExitCommand;
import seedu.trackbeau.logic.commands.HelpCommand;
import seedu.trackbeau.logic.commands.PlotAllergyChartCommand;
import seedu.trackbeau.logic.commands.PlotCommand;
import seedu.trackbeau.logic.commands.PlotHairChartCommand;
import seedu.trackbeau.logic.commands.PlotMonthlyCustomerChartCommand;
import seedu.trackbeau.logic.commands.PlotServiceChartCommand;
import seedu.trackbeau.logic.commands.PlotSkinChartCommand;
import seedu.trackbeau.logic.commands.PlotStaffChartCommand;
import seedu.trackbeau.logic.commands.ScheduleCommand;
import seedu.trackbeau.logic.commands.ScheduleNextCommand;
import seedu.trackbeau.logic.commands.SchedulePreviousCommand;
import seedu.trackbeau.logic.commands.booking.AddBookingCommand;
import seedu.trackbeau.logic.commands.booking.DeleteBookingCommand;
import seedu.trackbeau.logic.commands.booking.EditBookingCommand;
import seedu.trackbeau.logic.commands.booking.FindBookingCommand;
import seedu.trackbeau.logic.commands.booking.ListBookingsCommand;
import seedu.trackbeau.logic.commands.customer.AddCustomerCommand;
import seedu.trackbeau.logic.commands.customer.DeleteCustomerCommand;
import seedu.trackbeau.logic.commands.customer.EditCustomerCommand;
import seedu.trackbeau.logic.commands.customer.FindCustomerCommand;
import seedu.trackbeau.logic.commands.customer.ListCustomersCommand;
import seedu.trackbeau.logic.commands.service.AddServiceCommand;
import seedu.trackbeau.logic.commands.service.DeleteServiceCommand;
import seedu.trackbeau.logic.commands.service.EditServiceCommand;
import seedu.trackbeau.logic.commands.service.FindServiceCommand;
import seedu.trackbeau.logic.commands.service.ListServicesCommand;
import seedu.trackbeau.logic.parser.booking.AddBookingCommandParser;
import seedu.trackbeau.logic.parser.booking.DeleteBookingCommandParser;
import seedu.trackbeau.logic.parser.booking.EditBookingCommandParser;
import seedu.trackbeau.logic.parser.booking.FindBookingCommandParser;
import seedu.trackbeau.logic.parser.customer.AddCustomerCommandParser;
import seedu.trackbeau.logic.parser.customer.DeleteCustomerCommandParser;
import seedu.trackbeau.logic.parser.customer.EditCustomerCommandParser;
import seedu.trackbeau.logic.parser.customer.FindCustomerCommandParser;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.logic.parser.service.AddServiceCommandParser;
import seedu.trackbeau.logic.parser.service.DeleteServiceCommandParser;
import seedu.trackbeau.logic.parser.service.EditServiceCommandParser;
import seedu.trackbeau.logic.parser.service.FindServiceCommandParser;

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
        case ListCustomersCommand.COMMAND_WORD:
            return new ListCustomersCommand();

        case AddCustomerCommand.COMMAND_WORD:
            return new AddCustomerCommandParser().parse(arguments);

        case EditCustomerCommand.COMMAND_WORD:
            return new EditCustomerCommandParser().parse(arguments);

        case DeleteCustomerCommand.COMMAND_WORD:
            return new DeleteCustomerCommandParser().parse(arguments);

        case FindCustomerCommand.COMMAND_WORD:
            return new FindCustomerCommandParser().parse(arguments);

        case ListServicesCommand.COMMAND_WORD:
            return new ListServicesCommand();

        case AddServiceCommand.COMMAND_WORD:
            return new AddServiceCommandParser().parse(arguments);

        case EditServiceCommand.COMMAND_WORD:
            return new EditServiceCommandParser().parse(arguments);

        case DeleteServiceCommand.COMMAND_WORD:
            return new DeleteServiceCommandParser().parse(arguments);

        case FindServiceCommand.COMMAND_WORD:
            return new FindServiceCommandParser().parse(arguments);

        case ListBookingsCommand.COMMAND_WORD:
            return new ListBookingsCommand();

        case AddBookingCommand.COMMAND_WORD:
            return new AddBookingCommandParser().parse(arguments);

        case EditBookingCommand.COMMAND_WORD:
            return new EditBookingCommandParser().parse(arguments);

        case DeleteBookingCommand.COMMAND_WORD:
            return new DeleteBookingCommandParser().parse(arguments);

        case FindBookingCommand.COMMAND_WORD:
            return new FindBookingCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ScheduleCommand.COMMAND_WORD:
            return new ScheduleCommandParser().parse(arguments);

        case ScheduleNextCommand.COMMAND_WORD:
            return new ScheduleNextCommand();

        case SchedulePreviousCommand.COMMAND_WORD:
            return new SchedulePreviousCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        // chart commands

        case PlotAllergyChartCommand.COMMAND_WORD:
            return new PlotAllergyChartCommand();

        case PlotServiceChartCommand.COMMAND_WORD:
            return new PlotServiceChartCommand();

        case PlotStaffChartCommand.COMMAND_WORD:
            return new PlotStaffChartCommand();

        case PlotHairChartCommand.COMMAND_WORD:
            return new PlotHairChartCommand();

        case PlotSkinChartCommand.COMMAND_WORD:
            return new PlotSkinChartCommand();

        case PlotMonthlyCustomerChartCommand.COMMAND_WORD:
            return new PlotMonthlyCustomerChartCommand();

        case PlotCommand.COMMAND_WORD:
            return new PlotCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.commands.AddDishCommand;
import seedu.address.logic.commands.AddDriverCommand;
import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCustomerCommand;
import seedu.address.logic.commands.DeleteDishCommand;
import seedu.address.logic.commands.DeleteDriverCommand;
import seedu.address.logic.commands.EditCustomerCommand;
import seedu.address.logic.commands.EditDishCommand;
import seedu.address.logic.commands.EditDriverCommand;
import seedu.address.logic.commands.EditOrderCommand;
import seedu.address.logic.commands.EditOrderStatusCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCustomerCommand;
import seedu.address.logic.commands.ListDishCommand;
import seedu.address.logic.commands.ListDriverCommand;
import seedu.address.logic.commands.ListOrderCommand;
import seedu.address.logic.commands.RevenueCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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

        case AddCustomerCommand.COMMAND_WORD:
            return new AddCustomerCommandParser().parse(arguments);

        case AddDishCommand.COMMAND_WORD:
            return new AddDishCommandParser().parse(arguments);

        case EditCustomerCommand.COMMAND_WORD:
            return new EditCustomerCommandParser().parse(arguments);

        case EditDishCommand.COMMAND_WORD:
            return new EditDishCommandParser().parse(arguments);

        case DeleteCustomerCommand.COMMAND_WORD:
            return new DeleteCustomerCommandParser().parse(arguments);

        case DeleteDishCommand.COMMAND_WORD:
            return new DeleteDishCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCustomerCommand.COMMAND_WORD:
            return new ListCustomerCommand();

        case ListDishCommand.COMMAND_WORD:
            return new ListDishCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddOrderCommand.COMMAND_WORD:
            return new AddOrderCommandParser().parse(arguments);

        case EditOrderStatusCommand.COMMAND_WORD:
            return new EditOrderStatusCommandParser().parse(arguments);

        case EditOrderCommand.COMMAND_WORD:
            return new EditOrderCommandParser().parse(arguments);

        case FindOrderCommand.COMMAND_WORD:
            return new FindOrderCommandParser().parse(arguments);

        case ListOrderCommand.COMMAND_WORD:
            return new ListOrderCommandParser().parse(arguments);

        case AddDriverCommand.COMMAND_WORD:
            return new AddDriverCommandParser().parse(arguments);

        case DeleteDriverCommand.COMMAND_WORD:
            return new DeleteDriverCommandParser().parse(arguments);

        case ListDriverCommand.COMMAND_WORD:
            return new ListDriverCommandParser().parse(arguments);

        case EditDriverCommand.COMMAND_WORD:
            return new EditDriverCommandParser().parse(arguments);

        case RevenueCommand.COMMAND_WORD:
            return new RevenueCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

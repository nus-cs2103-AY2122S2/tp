package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddInsuranceCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String SINGLE_COMMAND_FORMAT = "";

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
        final String theRest = matcher.group("arguments");

        final String type = getCommandType(theRest);
        final String arguments = theRest.trim().substring(2);

        switch (type) {
        case Command.COMMAND_PERSON:
            return this.parsePersonCommand(commandWord, arguments);
        case Command.COMMAND_INSURANCE:
            return this.parseInsuranceCommand(commandWord, arguments);
        case Command.COMMAND_RECORD:
            //TODO: parse record
        case Command.COMMAND_APPOINTMENT:
            //TODO: parse appointment
        case SINGLE_COMMAND_FORMAT:
            return this.parseGeneralCommand(commandWord);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command parseGeneralCommand(String commandWord) throws ParseException {
        switch (commandWord) {
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ListPersonCommand.COMMAND_WORD:
            return new ListPersonCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private String getCommandType(String arguments) throws ParseException {
        if (arguments.trim().length() == 0) {
            return SINGLE_COMMAND_FORMAT;
        }

        if (arguments.trim().length() < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        return arguments.trim().substring(0, 2);
    }

    /**
     * Parse person command command.
     *
     * @param commandWord the command word
     * @param arguments   the argument
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parsePersonCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddPersonCommand.COMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case FindPersonCommand.COMMAND_WORD:
            return new FindPersonCommandParser().parse(arguments);

        case ListPersonCommand.COMMAND_WORD:
            return new ListPersonCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parse insurance command command.
     *
     * @param commandWord the command word
     * @param arguments   the argument
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseInsuranceCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddInsuranceCommand.COMMAND_WORD:
            return new AddInsuranceCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

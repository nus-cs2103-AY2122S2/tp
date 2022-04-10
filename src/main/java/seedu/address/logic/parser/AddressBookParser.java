package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddInsuranceCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.AddRecordCommand;
import seedu.address.logic.commands.ClearAppointmentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeleteInsuranceCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.DeleteRecordCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditInsuranceCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditRecordCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.commands.FindInsuranceCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.FindRecordCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.logic.commands.ListAppointmentHistoryCommand;
import seedu.address.logic.commands.ListExpiredRecordCommand;
import seedu.address.logic.commands.ListInsuranceCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.ListRecordCommand;
import seedu.address.logic.commands.SortAppointmentCommand;
import seedu.address.logic.commands.SortRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.person.Person;

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
    public Command parseCommand(Model model, String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String theRest = matcher.group("arguments");

        final String type = getCommandType(theRest);
        String arguments = "";

        if (type != SINGLE_COMMAND_FORMAT) {
            arguments = theRest.trim().substring(2);
        }

        switch (type) {
        case Command.COMMAND_PERSON:
            return this.parsePersonCommand(commandWord, arguments);
        case Command.COMMAND_INSURANCE:
            return this.parseInsuranceCommand(commandWord, arguments);
        case Command.COMMAND_RECORD:
            return this.parseRecordCommand(commandWord, arguments, model.getAddressBook().getPersonList(),
                    model.getFilteredInsuranceList());
        case Command.COMMAND_APPOINTMENT:
            return this.parseAppointmentCommand(commandWord, arguments);
        case Command.COMMAND_APPOINTMENT_HISTORY:
            return this.parseAppointmentHistoryCommand(commandWord);
        case Command.COMMAND_EXPIRED_RECORD:
            return this.parseExpiredRecordCommand(commandWord);
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
        if (arguments.trim().split(" ")[0].length() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        return arguments.trim().substring(0, 2);
    }

    /**
     * Parse person command.
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
     * Parse insurance command.
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

        case DeleteInsuranceCommand.COMMAND_WORD:
            return new DeleteInsuranceCommandParser().parse(arguments);

        case ListInsuranceCommand.COMMAND_WORD:
            return new ListInsuranceCommand();

        case EditInsuranceCommand.COMMAND_WORD:
            return new EditInsuranceCommandParser().parse(arguments);

        case FindInsuranceCommand.COMMAND_WORD:
            return new FindInsuranceCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parse appointment command.
     *
     * @param commandWord the command word
     * @param arguments   the argument
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseAppointmentCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);
        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);
        case ListAppointmentCommand.COMMAND_WORD:
            return new ListAppointmentCommand();
        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parse(arguments);
        case FindAppointmentCommand.COMMAND_WORD:
            return new FindAppointmentCommandParser().parse(arguments);
        case ClearAppointmentCommand.COMMAND_WORD:
            return new ClearAppointmentCommand();
        case SortAppointmentCommand.COMMAND_WORD:
            return new SortAppointmentCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parse appointment history command.
     *
     * @param commandWord the command word
     * @return the command based on the user input
     */
    private Command parseAppointmentHistoryCommand(String commandWord) throws ParseException {
        if (commandWord.equals(ListAppointmentHistoryCommand.COMMAND_WORD)) {
            return new ListAppointmentHistoryCommand();
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parse expired record command.
     *
     * @param commandWord the command word
     * @return the command based on the user input
     */
    private Command parseExpiredRecordCommand(String commandWord) throws ParseException {
        if (commandWord.equals(ListExpiredRecordCommand.COMMAND_WORD)) {
            return new ListExpiredRecordCommand();
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parse appointment command.
     *
     * @param commandWord the command word
     * @param arguments   the argument
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */

    public Command parseRecordCommand(String commandWord, String arguments, List<Person> personList,
                                      ObservableList<Insurance> insuranceList) throws ParseException {

        switch (commandWord) {
        case AddRecordCommand.COMMAND_WORD:
            return new AddRecordCommandParser().parse(personList, insuranceList, arguments);

        case DeleteRecordCommand.COMMAND_WORD:
            return new DeleteRecordCommandParser().parse(arguments);

        case ListRecordCommand.COMMAND_WORD:
            return new ListRecordCommand();

        case EditRecordCommand.COMMAND_WORD:
            return new EditRecordCommandParser().parse(arguments);

        case FindRecordCommand.COMMAND_WORD:
            return new FindRecordCommandParser().parse(arguments);

        case SortRecordCommand.COMMAND_WORD:
            return new SortRecordCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

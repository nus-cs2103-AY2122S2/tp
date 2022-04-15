package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.ListType;
import seedu.address.logic.commands.AddCompanyCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ArchiveAllCommand;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAllCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCompanyCommand;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCompanyCommand;
import seedu.address.logic.commands.FindEventCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCompanyCommand;
import seedu.address.logic.commands.ListEventCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.SortCompanyCommand;
import seedu.address.logic.commands.SortEventCommand;
import seedu.address.logic.commands.SortPersonCommand;
import seedu.address.logic.commands.UnarchiveAllCommand;
import seedu.address.logic.commands.UnarchiveCommand;
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

        case AddPersonCommand.COMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);

        case AddCompanyCommand.COMMAND_WORD:
            return new AddCompanyCommandParser().parse(arguments);

        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case EditCompanyCommand.COMMAND_WORD:
            return new EditCompanyCommandParser().parse(arguments);

        case EditEventCommand.COMMAND_WORD:
            return new EditEventCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteAllCommand.COMMAND_WORD:
            return new DeleteAllCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCompanyCommand.COMMAND_WORD:
            return new FindCompanyCommandParser().parse(arguments);

        case FindEventCommand.COMMAND_WORD:
            return new FindEventCommandParser().parse(arguments);

        case FindPersonCommand.COMMAND_WORD:
            return new FindPersonCommandParser().parse(arguments);

        case ListPersonCommand.COMMAND_WORD:
            return new ListCommandParser(ListType.PERSON).parse(arguments);

        case ListCompanyCommand.COMMAND_WORD:
            return new ListCommandParser(ListType.COMPANY).parse(arguments);

        case ListEventCommand.COMMAND_WORD:
            return new ListCommandParser(ListType.EVENT).parse(arguments);

        case SortPersonCommand.COMMAND_WORD:
            return new SortCommandParser(ListType.PERSON).parse(arguments);

        case SortCompanyCommand.COMMAND_WORD:
            return new SortCommandParser(ListType.COMPANY).parse(arguments);

        case SortEventCommand.COMMAND_WORD:
            return new SortCommandParser(ListType.EVENT).parse(arguments);

        case ArchiveCommand.COMMAND_WORD:
            return new ArchiveCommandParser().parse(arguments);

        case ArchiveAllCommand.COMMAND_WORD:
            return new ArchiveAllCommand();

        case UnarchiveCommand.COMMAND_WORD:
            return new UnarchiveCommandParser().parse(arguments);

        case UnarchiveAllCommand.COMMAND_WORD:
            return new UnarchiveAllCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

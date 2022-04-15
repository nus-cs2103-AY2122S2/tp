package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.person.AddPersonCommand;
import seedu.address.logic.commands.person.AddPersonTagCommand;
import seedu.address.logic.commands.person.ClearPersonCommand;
import seedu.address.logic.commands.person.DeletePersonCommand;
import seedu.address.logic.commands.person.DeletePersonTagCommand;
import seedu.address.logic.commands.person.EditPersonCommand;
import seedu.address.logic.commands.person.FindPersonCommand;
import seedu.address.logic.commands.person.ListPersonCommand;
import seedu.address.logic.commands.person.SortStrengthCommand;
import seedu.address.logic.commands.person.SortWeaknessCommand;
import seedu.address.logic.commands.person.notecommands.AddMiscCommand;
import seedu.address.logic.commands.person.notecommands.AddStrengthCommand;
import seedu.address.logic.commands.person.notecommands.AddWeaknessCommand;
import seedu.address.logic.commands.person.notecommands.DeleteMiscCommand;
import seedu.address.logic.commands.person.notecommands.DeleteStrengthCommand;
import seedu.address.logic.commands.person.notecommands.DeleteWeaknessCommand;
import seedu.address.logic.commands.strategy.AddPlayerCommand;
import seedu.address.logic.commands.strategy.DeletePlayerCommand;
import seedu.address.logic.commands.strategy.ExportCommand;
import seedu.address.logic.commands.strategy.LoadCourtCommand;
import seedu.address.logic.commands.strategy.MovePlayerCommand;
import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.logic.commands.task.AddTaskTagCommand;
import seedu.address.logic.commands.task.ClearTaskCommand;
import seedu.address.logic.commands.task.DeleteTaskCommand;
import seedu.address.logic.commands.task.DeleteTaskTagCommand;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.logic.commands.task.FindTaskCommand;
import seedu.address.logic.commands.task.GetPersonCommand;
import seedu.address.logic.commands.task.ListTaskCommand;
import seedu.address.logic.commands.task.SortTaskByDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.person.AddPersonCommandParser;
import seedu.address.logic.parser.person.AddPersonTagCommandParser;
import seedu.address.logic.parser.person.DeletePersonCommandParser;
import seedu.address.logic.parser.person.DeletePersonTagCommandParser;
import seedu.address.logic.parser.person.EditPersonCommandParser;
import seedu.address.logic.parser.person.FindPersonCommandParser;
import seedu.address.logic.parser.person.notecommands.AddMiscCommandParser;
import seedu.address.logic.parser.person.notecommands.AddStrengthCommandParser;
import seedu.address.logic.parser.person.notecommands.AddWeaknessCommandParser;
import seedu.address.logic.parser.person.notecommands.DeleteMiscCommandParser;
import seedu.address.logic.parser.person.notecommands.DeleteStrengthCommandParser;
import seedu.address.logic.parser.person.notecommands.DeleteWeaknessCommandParser;
import seedu.address.logic.parser.strategy.AddPlayerCommandParser;
import seedu.address.logic.parser.strategy.DeletePlayerCommandParser;
import seedu.address.logic.parser.strategy.LoadCourtCommandParser;
import seedu.address.logic.parser.strategy.MovePlayerCommandParser;
import seedu.address.logic.parser.task.AddTaskCommandParser;
import seedu.address.logic.parser.task.AddTaskTagCommandParser;
import seedu.address.logic.parser.task.ClearTaskCommandParser;
import seedu.address.logic.parser.task.DeleteTaskCommandParser;
import seedu.address.logic.parser.task.DeleteTaskTagCommandParser;
import seedu.address.logic.parser.task.EditTaskCommandParser;
import seedu.address.logic.parser.task.FindTaskCommandParser;
import seedu.address.logic.parser.task.GetPersonCommandParser;

/**
 * Parses user input.
 */
public class Coach2K22Parser {

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

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case ClearPersonCommand.COMMAND_WORD:
            return new ClearPersonCommand();

        case ClearTaskCommand.COMMAND_WORD:
            return new ClearTaskCommandParser().parse(arguments);

        case FindPersonCommand.COMMAND_WORD:
            return new FindPersonCommandParser().parse(arguments);

        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments);

        case GetPersonCommand.COMMAND_WORD:
            return new GetPersonCommandParser().parse(arguments);

        case ListPersonCommand.COMMAND_WORD:
            return new ListPersonCommand();

        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();

        case SortStrengthCommand.COMMAND_WORD:
            return new SortStrengthCommand();

        case SortWeaknessCommand.COMMAND_WORD:
            return new SortWeaknessCommand();

        case AddStrengthCommand.COMMAND_WORD:
            return new AddStrengthCommandParser().parse(arguments);

        case AddWeaknessCommand.COMMAND_WORD:
            return new AddWeaknessCommandParser().parse(arguments);

        case AddMiscCommand.COMMAND_WORD:
            return new AddMiscCommandParser().parse(arguments);

        case DeleteStrengthCommand.COMMAND_WORD:
            return new DeleteStrengthCommandParser().parse(arguments);

        case DeleteWeaknessCommand.COMMAND_WORD:
            return new DeleteWeaknessCommandParser().parse(arguments);

        case DeleteMiscCommand.COMMAND_WORD:
            return new DeleteMiscCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddPersonTagCommand.COMMAND_WORD:
            return new AddPersonTagCommandParser().parse(arguments);

        case DeletePersonTagCommand.COMMAND_WORD:
            return new DeletePersonTagCommandParser().parse(arguments);

        case AddPlayerCommand.COMMAND_WORD:
            return new AddPlayerCommandParser().parse(arguments);

        case DeletePlayerCommand.COMMAND_WORD:
            return new DeletePlayerCommandParser().parse(arguments);

        case LoadCourtCommand.COMMAND_WORD:
            return new LoadCourtCommandParser().parse(arguments);

        case AddTaskTagCommand.COMMAND_WORD:
            return new AddTaskTagCommandParser().parse(arguments);

        case DeleteTaskTagCommand.COMMAND_WORD:
            return new DeleteTaskTagCommandParser().parse(arguments);

        case MovePlayerCommand.COMMAND_WORD:
            return new MovePlayerCommandParser().parse(arguments);

        case SortTaskByDateCommand.COMMAND_WORD:
            return new SortTaskByDateCommand();

        case ExportCommand.COMMAND_WORD:
            return new ExportCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

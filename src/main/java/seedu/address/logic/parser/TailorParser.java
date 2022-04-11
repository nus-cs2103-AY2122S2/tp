package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCsvCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MailAllCommand;
import seedu.address.logic.commands.MailIndexCommand;
import seedu.address.logic.commands.MailXCommand;
import seedu.address.logic.commands.NewTaskCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.RemoveTaskCommand;
import seedu.address.logic.commands.SetDefaultGroupCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TailorParser {

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

        final String commandWord = matcher.group("commandWord").toLowerCase();
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

        case SetDefaultGroupCommand.COMMAND_WORD:
            return new SetDefaultGroupCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand(arguments);

        case MailXCommand.COMMAND_WORD:
            return new MailXCommandParser().parse(arguments);

        case MailIndexCommand.COMMAND_WORD:
            return new MailIndexCommandParser().parse(arguments);

        case MailAllCommand.COMMAND_WORD:
            return new MailAllCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case NewTaskCommand.COMMAND_WORD:
            return new NewTaskCommandParser().parse(arguments);

        case RemoveTaskCommand.COMMAND_WORD:
            return new RemoveTaskCommandParser().parse(arguments);

        case ImportCsvCommand.COMMAND_WORD:
            return new ImportCsvCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

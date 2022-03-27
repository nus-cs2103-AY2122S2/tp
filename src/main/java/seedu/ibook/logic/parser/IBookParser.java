package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.ibook.logic.commands.AddCommand;
import seedu.ibook.logic.commands.AddItemCommand;
import seedu.ibook.logic.commands.ClearCommand;
import seedu.ibook.logic.commands.Command;
import seedu.ibook.logic.commands.DeleteCommand;
import seedu.ibook.logic.commands.DeleteItemCommand;
import seedu.ibook.logic.commands.ExitCommand;
import seedu.ibook.logic.commands.ExpiredCommand;
import seedu.ibook.logic.commands.FindCommand;
import seedu.ibook.logic.commands.HelpCommand;
import seedu.ibook.logic.commands.ListCommand;
import seedu.ibook.logic.commands.RedoCommand;
import seedu.ibook.logic.commands.UndoCommand;
import seedu.ibook.logic.commands.UpdateCommand;
import seedu.ibook.logic.commands.UpdateItemCommand;
import seedu.ibook.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class IBookParser {

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
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case UpdateCommand.COMMAND_WORD:
            return new UpdateCommandParser().parse(arguments);

        case AddItemCommand.COMMAND_WORD:
            return new AddItemCommandParser().parse(arguments);

        case DeleteItemCommand.COMMAND_WORD:
            return new DeleteItemCommandParser().parse(arguments);

        case UpdateItemCommand.COMMAND_WORD:
            return new UpdateItemCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case ExpiredCommand.COMMAND_WORD:
            return new ExpiredCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}


package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.ibook.logic.commands.ClearCommand;
import seedu.ibook.logic.commands.Command;
import seedu.ibook.logic.commands.ExitCommand;
import seedu.ibook.logic.commands.HelpCommand;
import seedu.ibook.logic.commands.RedoCommand;
import seedu.ibook.logic.commands.UndoCommand;
import seedu.ibook.logic.commands.item.AddItemCommand;
import seedu.ibook.logic.commands.item.DeleteItemCommand;
import seedu.ibook.logic.commands.item.ExpiredCommand;
import seedu.ibook.logic.commands.item.RemindCommand;
import seedu.ibook.logic.commands.item.UpdateItemCommand;
import seedu.ibook.logic.commands.product.AddCommand;
import seedu.ibook.logic.commands.product.DeleteAllCommand;
import seedu.ibook.logic.commands.product.DeleteCommand;
import seedu.ibook.logic.commands.product.FindCommand;
import seedu.ibook.logic.commands.product.ListCommand;
import seedu.ibook.logic.commands.product.OutOfStockCommand;
import seedu.ibook.logic.commands.product.UpdateAllCommand;
import seedu.ibook.logic.commands.product.UpdateCommand;
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

        case DeleteAllCommand.COMMAND_WORD:
            return new DeleteAllCommand();

        case UpdateCommand.COMMAND_WORD:
            return new UpdateCommandParser().parse(arguments);

        case UpdateAllCommand.COMMAND_WORD:
            return new UpdateAllCommandParser().parse(arguments);

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

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case ExpiredCommand.COMMAND_WORD:
            return new ExpiredCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case OutOfStockCommand.COMMAND_WORD:
            return new OutOfStockCommand();

        case RemindCommand.COMMAND_WORD:
            return new RemindCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}


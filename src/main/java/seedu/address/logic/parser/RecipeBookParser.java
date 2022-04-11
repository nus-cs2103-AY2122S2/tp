package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CancelClearCommand;
import seedu.address.logic.commands.CancelResetCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ConfirmedClearCommand;
import seedu.address.logic.commands.ConfirmedResetCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class RecipeBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private boolean requiresConfirmationClear = false;
    private boolean requiresConfirmationReset = false;

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
        final String arguments = matcher.group("arguments") + " ";

        if (!requiresConfirmationClear && !requiresConfirmationReset) {
            switch (commandWord) {
            case AddCommand.COMMAND_WORD:
                return new AddCommandParser().parse(arguments);
            case ClearCommand.COMMAND_WORD:
                requiresConfirmationClear = ClearCommandParser.isNotForcedClear(arguments);
                return new ClearCommandParser().parse(arguments);
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommandParser().parse(arguments);
            case EditCommand.COMMAND_WORD:
                return new EditCommandParser().parse(arguments);
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case FindCommand.COMMAND_WORD:
                return new FindCommandParser().parse(arguments);
            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case ResetCommand.COMMAND_WORD:
                requiresConfirmationReset = ResetCommandParser.isNotForcedReset(arguments);
                return new ResetCommandParser().parse(arguments);
            case ViewCommand.COMMAND_WORD:
                return new ViewCommandParser().parse(arguments);
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        } else if (requiresConfirmationClear) {
            requiresConfirmationClear = false;
            switch (commandWord) {
            case CancelClearCommand.COMMAND_WORD:
                return new CancelClearCommand();
            case ConfirmedClearCommand.COMMAND_WORD:
                return new ConfirmedClearCommand();
            default:
                throw new ParseException(ConfirmedClearCommand.MESSAGE_UNKNOWN_COMMAND + ClearCommand.MESSAGE_USAGE);
            }
        } else {
            requiresConfirmationReset = false;
            switch (commandWord) {
            case CancelResetCommand.COMMAND_WORD:
                return new CancelResetCommand();
            case ConfirmedResetCommand.COMMAND_WORD:
                return new ConfirmedResetCommand();
            default:
                throw new ParseException(ConfirmedResetCommand.MESSAGE_UNKNOWN_COMMAND + ResetCommand.MESSAGE_USAGE);
            }
        }
    }
}

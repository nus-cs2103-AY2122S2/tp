package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EmailCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.ResizeCommand;
import seedu.address.logic.commands.SummariseCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    public static final String MESSAGE_IRRELEVANT_PARAMETERS = "Please ensure that there are no "
            + "additional parameters for this type of command.";
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private String[] singleCommandList = {ClearCommand.COMMAND_WORD, ListCommand.COMMAND_WORD,
        ExitCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD, SummariseCommand.COMMAND_WORD,
        ArchiveCommand.COMMAND_WORD, UndoCommand.COMMAND_WORD, RedoCommand.COMMAND_WORD, EmailCommand.COMMAND_WORD};

    /**
     * Checks whether command that does not require any parameters such as {@Code ListCommand} are input by the user.
     * @param arguments any arguments that user may have added
     * @param commandWord the type of command inputted
     * @throws ParseException if the user input additional parameters
     */
    private void checkForSingleCommandWord(String arguments, String commandWord) throws ParseException {
        String trimmedArgs = arguments.trim();
        for (String command : singleCommandList) {
            if (command.equals(commandWord) && !trimmedArgs.isEmpty()) {
                throw new ParseException(MESSAGE_IRRELEVANT_PARAMETERS);
            }
        }
    }

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

        checkForSingleCommandWord(arguments, commandWord);

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

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SummariseCommand.COMMAND_WORD:
            return new SummariseCommand();

        case ArchiveCommand.COMMAND_WORD:
            return new ArchiveCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case ResizeCommand.COMMAND_WORD:
            return new ResizeCommandParser().parse(arguments);

        case EmailCommand.COMMAND_WORD:
            return new EmailCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

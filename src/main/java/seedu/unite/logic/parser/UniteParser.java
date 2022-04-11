package seedu.unite.logic.parser;

import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unite.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.unite.logic.commands.AddCommand;
import seedu.unite.logic.commands.AddTagCommand;
import seedu.unite.logic.commands.AttachTagCommand;
import seedu.unite.logic.commands.ClearCommand;
import seedu.unite.logic.commands.ClearEmptyTagCommand;
import seedu.unite.logic.commands.Command;
import seedu.unite.logic.commands.DeleteCommand;
import seedu.unite.logic.commands.DeleteTagCommand;
import seedu.unite.logic.commands.DetachTagCommand;
import seedu.unite.logic.commands.DisableMouseUxCommand;
import seedu.unite.logic.commands.EditCommand;
import seedu.unite.logic.commands.EnableMouseUxCommand;
import seedu.unite.logic.commands.ExitCommand;
import seedu.unite.logic.commands.FilterCommand;
import seedu.unite.logic.commands.FindCommand;
import seedu.unite.logic.commands.GrabCommand;
import seedu.unite.logic.commands.HelpCommand;
import seedu.unite.logic.commands.ListCommand;
import seedu.unite.logic.commands.ListTagCommand;
import seedu.unite.logic.commands.ProfileCommand;
import seedu.unite.logic.commands.RemarkTagCommand;
import seedu.unite.logic.commands.SwitchThemeCommand;
import seedu.unite.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class UniteParser {

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

        if (commandWord.equals(ClearCommand.COMMAND_WORD)) {
            return new ClearCommandParser().parse(userInput);
        }

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddTagCommand.COMMAND_WORD:
            return new AddTagCommandParser().parse(arguments);

        case AttachTagCommand.COMMAND_WORD:
            return new AttachTagCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        case DetachTagCommand.COMMAND_WORD:
            return new DetachTagCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListTagCommand.COMMAND_WORD:
            return new ListTagCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case SwitchThemeCommand.COMMAND_WORD:
            return new SwitchThemeCommandParser().parse(arguments);

        case ProfileCommand.COMMAND_WORD:
            return new ProfileCommandParser().parse(arguments);

        case RemarkTagCommand.COMMAND_WORD:
            return new RemarkTagCommandParser().parse(arguments);

        case GrabCommand.COMMAND_WORD:
            return new GrabCommandParser().parse(arguments);

        case ClearEmptyTagCommand.COMMAND_WORD:
            return new ClearEmptyTagCommand();

        case EnableMouseUxCommand.COMMAND_WORD:
            return new EnableMouseUxCommand();

        case DisableMouseUxCommand.COMMAND_WORD:
            return new DisableMouseUxCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

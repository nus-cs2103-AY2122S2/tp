package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INCOMPATIBLE_VIEW_MODE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddImageCommand;
import seedu.address.logic.commands.AssignTagCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CreateTagCommand;
import seedu.address.logic.commands.DeadlineCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteDeadlineCommand;
import seedu.address.logic.commands.DeleteImageCommand;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.DetailedViewExecutable;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FavouriteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HighImportanceCommand;
import seedu.address.logic.commands.ImagesCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListFavouritesCommand;
import seedu.address.logic.commands.ListImportantCommand;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.logic.commands.SortsCommand;
import seedu.address.logic.commands.UnassignTagCommand;
import seedu.address.logic.commands.ViewCommand;
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

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case NoteCommand.COMMAND_WORD:
            return new NoteCommandParser().parse(arguments);

        case FavouriteCommand.COMMAND_WORD:
            return new FavouriteCommandParser().parse(arguments);

        case ListFavouritesCommand.COMMAND_WORD:
            return new ListFavouritesCommand();

        case FindTagCommand.COMMAND_WORD:
            return new FindTagCommandParser().parse(arguments);

        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case HighImportanceCommand.COMMAND_WORD:
            return new HighImportanceCommandParser().parse(arguments);

        case ListImportantCommand.COMMAND_WORD:
            return new ListImportantCommand();

        case CreateTagCommand.COMMAND_WORD:
            return new CreateTagCommandParser().parse(arguments);

        case SortsCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case AssignTagCommand.COMMAND_WORD:
            return new AssignTagCommandParser().parse(arguments);

        case UnassignTagCommand.COMMAND_WORD:
            return new UnassignTagCommandParser().parse(arguments);

        case AddImageCommand.COMMAND_WORD:
            return new AddImageCommandParser().parse(arguments);

        case ImagesCommand.COMMAND_WORD:
            return new ImagesCommandParser().parse(arguments);

        case DeleteImageCommand.COMMAND_WORD:
            return new DeleteImageCommandParser().parse(arguments);

        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        case DeleteDeadlineCommand.COMMAND_WORD:
            // Fallthrough
        case DeleteNoteCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_INCOMPATIBLE_VIEW_MODE);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input for a command to execute in detailed view mode.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public DetailedViewExecutable parseDetailedViewCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommandParser().parseInDetailedViewContext(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parseInDetailedViewContext(arguments);

        case NoteCommand.COMMAND_WORD:
            return new NoteCommandParser().parseInDetailedViewContext(arguments);

        case FavouriteCommand.COMMAND_WORD:
            return new FavouriteCommand();

        case HighImportanceCommand.COMMAND_WORD:
            return new HighImportanceCommand();

        case CreateTagCommand.COMMAND_WORD:
            return new CreateTagCommandParser().parse(arguments);

        case AssignTagCommand.COMMAND_WORD:
            return new AssignTagCommandParser().parseInDetailedViewContext(arguments);

        case UnassignTagCommand.COMMAND_WORD:
            return new UnassignTagCommandParser().parseInDetailedViewContext(arguments);

        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parseInDetailedViewContext(arguments);

        case DeleteDeadlineCommand.COMMAND_WORD:
            return new DeleteDeadlineCommandParser().parseInDetailedViewContext(arguments);

        case AddImageCommand.COMMAND_WORD:
            return new AddImageCommand();

        case DeleteImageCommand.COMMAND_WORD:
            return new DeleteImageCommandParser().parseInDetailedViewContext(arguments);

        case ImagesCommand.COMMAND_WORD:
            return new ImagesCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ClearCommand.COMMAND_WORD:
            // Fallthrough
        case DeleteCommand.COMMAND_WORD:
            // Fallthrough
        case DeleteTagCommand.COMMAND_WORD:
            // Fallthrough
        case FindCommand.COMMAND_WORD:
            // Fallthrough
        case FindTagCommand.COMMAND_WORD:
            // Fallthrough
        case ListFavouritesCommand.COMMAND_WORD:
            // Fallthrough
        case ListImportantCommand.COMMAND_WORD:
            // Fallthrough
        case SortsCommand.COMMAND_WORD:
            // Fallthrough
        case ViewCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_INCOMPATIBLE_VIEW_MODE);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

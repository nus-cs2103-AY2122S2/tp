package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DisenrolCommand;
import seedu.address.logic.commands.EnrolCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GradeCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Parses user input.
 */
public class TAssistParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String MESSAGE_USAGE = "Please refer to our User Guide at "
            + "https://ay2122s2-cs2103t-t13-2.github.io/tp/UserGuide.html";

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, Model model) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments, model);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments, model);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments, model);

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments, model);

        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommandParser().parse(arguments, model);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments, model);

        case EnrolCommand.COMMAND_WORD:
            return new EnrolCommandParser().parse(arguments, model);

        case DisenrolCommand.COMMAND_WORD:
            return new DisenrolCommandParser().parse(arguments, model);

        case GradeCommand.COMMAND_WORD:
            return new GradeCommandParser().parse(arguments, model);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

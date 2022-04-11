package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddLessonCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteLessonCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindLessonCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListLessonsCommand;
import seedu.address.logic.commands.ListStudentsCommand;
import seedu.address.logic.commands.UnassignCommand;
import seedu.address.logic.commands.ViewLessonInfoCommand;
import seedu.address.logic.commands.ViewStudentInfoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TeachWhatParser {

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
        requireNonNull(userInput);
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddStudentCommand.COMMAND_WORD:
            // Fallthrough
        case AddStudentCommand.SHORTENED_COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case DeleteStudentCommand.COMMAND_WORD:
            // Fallthrough
        case DeleteStudentCommand.SHORTENED_COMMAND_WORD:
            return new DeleteStudentCommandParser().parse(arguments);

        case AddLessonCommand.COMMAND_WORD:
            // Fallthrough
        case AddLessonCommand.SHORTENED_COMMAND_WORD:
            return new AddLessonCommandParser().parse(arguments);

        case DeleteLessonCommand.COMMAND_WORD:
            // Fallthrough
        case DeleteLessonCommand.SHORTENED_COMMAND_WORD:
            return new DeleteLessonCommandParser().parse(arguments);

        case EditStudentCommand.COMMAND_WORD:
            // Fallthrough
        case EditStudentCommand.SHORTENED_COMMAND_WORD:
            return new EditStudentCommandParser().parse(arguments);

        case EditLessonCommand.COMMAND_WORD:
            // Fallthrough
        case EditLessonCommand.SHORTENED_COMMAND_WORD:
            return new EditLessonCommandParser().parse(arguments);

        case FindStudentCommand.COMMAND_WORD:
            // Fallthrough
        case FindStudentCommand.SHORTENED_COMMAND_WORD:
            return new FindStudentCommandParser().parse(arguments);

        case FindLessonCommand.COMMAND_WORD:
            // Fallthrough
        case FindLessonCommand.SHORTENED_COMMAND_WORD:
            return new FindLessonCommandParser().parse(arguments);

        case ListStudentsCommand.COMMAND_WORD:
            // Fallthrough
        case ListStudentsCommand.SHORTENED_COMMAND_WORD:
            return new ListStudentsCommand();

        case ListLessonsCommand.COMMAND_WORD:
            // Fallthrough
        case ListLessonsCommand.SHORTENED_COMMAND_WORD:
            return new ListLessonsCommand();

        case ViewStudentInfoCommand.COMMAND_WORD:
            return new ViewStudentInfoCommandParser().parse(arguments);

        case ViewLessonInfoCommand.COMMAND_WORD:
            return new ViewLessonInfoCommandParser().parse(arguments);

        case AssignCommand.COMMAND_WORD:
            return new AssignCommandParser().parse(arguments);

        case UnassignCommand.COMMAND_WORD:
            return new UnassignCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

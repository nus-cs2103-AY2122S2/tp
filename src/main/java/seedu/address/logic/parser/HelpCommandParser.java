package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.AddAssessmentCommand;
import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteAssessmentCommand;
import seedu.address.logic.commands.DeleteClassCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListClassCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns an HelpCommand object for execution.
     */
    public HelpCommand parse(String args) throws ParseException {
        requireNonNull(args);
        final String commandInquiry = args.trim().toLowerCase();
        switch (commandInquiry) {

        case AddCommand.COMMAND_WORD:

        case EditCommand.COMMAND_WORD:

        case DeleteCommand.COMMAND_WORD:

        case ClearCommand.COMMAND_WORD:

        case FindCommand.COMMAND_WORD:

        case ListClassCommand.COMMAND_WORD:

        case ListCommand.COMMAND_WORD:

        case ExitCommand.COMMAND_WORD:

        case HelpCommand.COMMAND_WORD:

        case AddAssessmentCommand.COMMAND_WORD:

        case DeleteAssessmentCommand.COMMAND_WORD:

        case AddClassCommand.COMMAND_WORD:

        case DeleteClassCommand.COMMAND_WORD:

        case AddStudentCommand.COMMAND_WORD:
            return new HelpCommand(commandInquiry);

        default:
            return new HelpCommand();
        }
    }
}


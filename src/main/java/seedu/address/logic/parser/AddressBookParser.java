package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.applicant.AddApplicant;
import seedu.address.logic.commands.applicant.DeleteApplicant;
import seedu.address.logic.commands.applicant.EditApplicant;
import seedu.address.logic.commands.applicant.FindApplicant;
import seedu.address.logic.commands.applicant.ListApplicant;
import seedu.address.logic.commands.applicant.MarkApplicant;
import seedu.address.logic.commands.applicant.SortApplicant;
import seedu.address.logic.commands.applicant.TabApplicant;
import seedu.address.logic.commands.job.AddJob;
import seedu.address.logic.commands.job.DeleteJob;
import seedu.address.logic.commands.job.EditJob;
import seedu.address.logic.commands.job.FindJob;
import seedu.address.logic.commands.job.ListJob;
import seedu.address.logic.commands.job.MarkJob;
import seedu.address.logic.commands.job.SortJob;
import seedu.address.logic.commands.job.TabJob;
import seedu.address.logic.parser.applicant.AddApplicantParser;
import seedu.address.logic.parser.applicant.DeleteApplicantParser;
import seedu.address.logic.parser.applicant.EditApplicantParser;
import seedu.address.logic.parser.applicant.FindApplicantParser;
import seedu.address.logic.parser.applicant.MarkApplicantParser;
import seedu.address.logic.parser.applicant.SortApplicantParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.job.AddJobParser;
import seedu.address.logic.parser.job.DeleteJobParser;
import seedu.address.logic.parser.job.EditJobParser;
import seedu.address.logic.parser.job.FindJobParser;
import seedu.address.logic.parser.job.MarkJobParser;

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

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case EditApplicant.COMMAND_WORD:
            return new EditApplicantParser().parse(arguments);

        case AddApplicant.COMMAND_WORD:
            return new AddApplicantParser().parse(arguments);

        case MarkApplicant.COMMAND_WORD:
            return new MarkApplicantParser().parse(arguments);

        case DeleteApplicant.COMMAND_WORD:
            return new DeleteApplicantParser().parse(arguments);

        case FindApplicant.COMMAND_WORD:
            return new FindApplicantParser().parse(arguments);

        case ListApplicant.COMMAND_WORD:
            return new ListApplicant();

        case SortApplicant.COMMAND_WORD:
            return new SortApplicantParser().parse(arguments);

        case MarkJob.COMMAND_WORD:
            return new MarkJobParser().parse(arguments);

        case AddJob.COMMAND_WORD:
            return new AddJobParser().parse(arguments);

        case EditJob.COMMAND_WORD:
            return new EditJobParser().parse(arguments);

        case ListJob.COMMAND_WORD:
            return new ListJob();

        case TabApplicant.COMMAND_WORD:
            return new TabApplicant();

        case TabJob.COMMAND_WORD:
            return new TabJob();

        case DeleteJob.COMMAND_WORD:
            return new DeleteJobParser().parse(arguments);

        case FindJob.COMMAND_WORD:
            return new FindJobParser().parse(arguments);

        case SortJob.COMMAND_WORD:
            return new SortJob();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

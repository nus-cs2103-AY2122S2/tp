package seedu.address.logic.parser.interview;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import java.util.Date;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.interview.AddInterviewCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.Interview;


/**
 * Parses input arguments and creates a new AddInterviewCommand object
 */
public class AddInterviewCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddInterviewCommand
     * and returns an AddInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddInterviewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_POSITION);

        Index applicantIndex;

        // Find applicant index, to be converted into actual applicant in AddInterviewCommand. This is because we need
        // the model class to help us match the index to the actual applicant
        try {
            applicantIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE), pe);
        }

        // Can find actual date and do error checking here
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());

        // Find position index, to be converted into actual position in AddInterviewCommand. This is because we need
        // the model class to help us match the index to the actual applicant
        Index positionIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_POSITION).get());

        return new AddInterviewCommand(applicantIndex, date, positionIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

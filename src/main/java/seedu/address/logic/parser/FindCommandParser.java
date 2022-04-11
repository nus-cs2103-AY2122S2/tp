package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEYWORD;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.candidate.predicate.ApplicationStatusContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.AvailabilityContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.CandidateContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.ContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.CourseContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.InterviewStatusContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.PhoneContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.RemarkContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.SeniorityContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.StudentIdContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a {@code FindCommand} object for execution.
     * @param args contains the user input to be parsed
     * @return new {@code FindCommand} object with correct predicate parameter
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_KEYWORD, PREFIX_FIELD);

        // throws exception if no keywords are specified
        if (!arePrefixesPresent(argMultimap, PREFIX_KEYWORD)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> keywords = argMultimap.getAllValues(PREFIX_KEYWORD);
        Optional<String> field = argMultimap.getValue(PREFIX_FIELD);
        ContainsKeywordsPredicate predicate;
        String fieldString;

        if (field.isEmpty()) {
            fieldString = "";
        } else {
            fieldString = field.get().toLowerCase();
        }

        predicate = findMatchingPredicate(fieldString, keywords);

        return new FindCommand(predicate);
    }

    /**
     * Returns the matching new {@link ContainsKeywordsPredicate} object based on the field input by the user.
     * @param fieldString provides the field specified in user input as a string
     * @param keywords provides the list of keywords to search for
     * @return new created matching ContainsKeywordsPredicate subclass object
     * @throws ParseException if the user input for {@code fieldString} not conform the expected format
     */
    private ContainsKeywordsPredicate findMatchingPredicate(String fieldString, List<String> keywords)
            throws ParseException {
        switch (fieldString) {
        case "appstatus":
            return new ApplicationStatusContainsKeywordsPredicate(keywords);
        case "avail":
            return new AvailabilityContainsKeywordsPredicate(keywords);
        case "all":
        case "":
            return new CandidateContainsKeywordsPredicate(keywords);
        case "course":
            return new CourseContainsKeywordsPredicate(keywords);
        case "email":
            return new EmailContainsKeywordsPredicate(keywords);
        case "intstatus":
            return new InterviewStatusContainsKeywordsPredicate(keywords);
        case "name":
            return new NameContainsKeywordsPredicate(keywords);
        case "phone":
            return new PhoneContainsKeywordsPredicate(keywords);
        case "remark":
            return new RemarkContainsKeywordsPredicate(keywords);
        case "seniority":
            return new SeniorityContainsKeywordsPredicate(keywords);
        case "studentid":
            return new StudentIdContainsKeywordsPredicate(keywords);
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.INVALID_ATTRIBUTE_FIELD));
        }
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

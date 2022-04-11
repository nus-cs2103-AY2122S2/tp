package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTKEY;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.candidate.predicate.ContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a {@code SortCommand} object for execution.
     * @param args contains the user input to be parsed
     * @return new {@code SortCommand} object with correct comparator for sorting
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORTKEY);

        // throws exception if no sort attribute field is specified
        if (!arePrefixesPresent(argMultimap, PREFIX_SORTKEY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String sortKey = argMultimap.getValue(PREFIX_SORTKEY).get().toLowerCase();
        Comparator<Candidate> sortComparator = findMatchingComparator(sortKey);

        return new SortCommand(sortComparator, sortKey);
    }

    /**
     * Returns the matching new {@link Comparator<Candidate>} object based on the field input by the user to be
     * sorted by.
     * @param sortKey provides the field specified in user input as a string
     * @return new created matching {@link Comparator<Candidate>} object
     * @throws ParseException if the user input for {@code sortKey} not conform the expected format
     */
    private Comparator<Candidate> findMatchingComparator(String sortKey)
            throws ParseException {
        switch (sortKey) {
        case "appstatus":
            return Comparator.comparing(l -> l.getApplicationStatus().toString().toLowerCase());
        case "course":
            return Comparator.comparing(l -> l.getCourse().toString().toLowerCase());
        case "intstatus":
            return Comparator.comparing(l -> l.getInterviewStatus().toString().toLowerCase());
        case "name":
            return Comparator.comparing(l -> l.getName().toString().toLowerCase());
        case "seniority":
            return Comparator.comparing(l -> l.getSeniority().toString().toLowerCase());
        case "studentid":
            return Comparator.comparing(l -> l.getStudentId().toString().toLowerCase());
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.INVALID_ATTRIBUTE_FIELD));
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

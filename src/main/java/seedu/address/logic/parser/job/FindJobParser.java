package seedu.address.logic.parser.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;

import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.job.FindJob;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.job.Job;

public class FindJobParser implements Parser<FindJob> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindJob
     * and returns a FindJob object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindJob parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_JOBTITLE, PREFIX_JOBID);
        Predicate<Job> predicate;

        if (arePrefixesPresent(argMultimap, PREFIX_JOBTITLE, PREFIX_JOBID)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindJob.MESSAGE_USAGE));
        }

        if (!anyPrefixesPresent(argMultimap, PREFIX_JOBTITLE, PREFIX_JOBID)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindJob.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_JOBTITLE).isPresent()) {
            predicate = ParserUtil.parsePredicate(argMultimap.getValue(PREFIX_JOBTITLE).get(), true, false);
        } else if (argMultimap.getValue(PREFIX_JOBID).isPresent()) {
            predicate = ParserUtil.parsePredicate(argMultimap.getValue(PREFIX_JOBID).get(), false, true);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindJob.MESSAGE_USAGE));
        }

        return new FindJob(predicate);
    }

    /**
     * Returns true if there is a value mapped to PREFIX_INTERVIEW_STATUS and/or PREFIX_APPLICATION_STATUS
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(argumentMultimap::containsPrefix);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}


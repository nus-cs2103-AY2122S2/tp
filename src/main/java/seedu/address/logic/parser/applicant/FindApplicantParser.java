package seedu.address.logic.parser.applicant;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.applicant.FindApplicant;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.NameApplicantContainsKeywordsPredicate;

public class FindApplicantParser implements Parser<FindApplicant> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindApplicant
     * and returns a FindApplicant object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindApplicant parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindApplicant.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindApplicant(new NameApplicantContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}

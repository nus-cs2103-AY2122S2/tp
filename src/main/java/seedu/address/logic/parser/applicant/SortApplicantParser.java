package seedu.address.logic.parser.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTAPPLICANT;

import seedu.address.logic.commands.applicant.SortApplicant;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class SortApplicantParser implements Parser<SortApplicant> {

    @Override
    public SortApplicant parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput,
                PREFIX_SORTAPPLICANT);

        String compareBy;

        if (argumentMultimap.getValue(PREFIX_SORTAPPLICANT).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortApplicant.MESSAGE_USAGE));
        } else {

            try {
                compareBy = ParserUtil.parseApplicantComparator(argumentMultimap.getValue(PREFIX_SORTAPPLICANT).get());
            } catch (ParseException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortApplicant.MESSAGE_USAGE), e);
            }

        }

        return new SortApplicant(compareBy);
    }
}

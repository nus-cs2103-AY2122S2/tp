package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;

import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tasks.TaskContainsKeywordPredicate;


public class FindTaskCommandParser extends FindCommandParser {

    /**
     * Checks for any data that does not follow format.
     *
     * @param groups All groups captured by the g/ flag.
     * @return A new FindTaskCommand Object ready for execution.
     * @throws ParseException An invalid input was found.
     */
    public FindTaskCommand parse(List<String> groups) throws ParseException {

        for (String group : groups) {
            if (havePrefixesPresent(group, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_JOB,
                    PREFIX_STAGE, PREFIX_GROUP)) {
                throw new ParseException("[" + group + "] Invalid flags are found.");
            }

            ArgumentMultimap fields = ArgumentTokenizer.tokenize(padding + group, PREFIX_HEADER,
                    PREFIX_INFORMATION, PREFIX_DATE, PREFIX_TIME);

            checkInvalidHeader(fields.getAllValues(PREFIX_HEADER), group);
            checkInvalidInformation(fields.getAllValues(PREFIX_INFORMATION), group);
            checkInvalidDates(fields.getAllValues(PREFIX_DATE), group);
            checkInvalidTime(fields.getAllValues(PREFIX_TIME), group);
        }

        return new FindTaskCommand(new TaskContainsKeywordPredicate(groups));
    }
}


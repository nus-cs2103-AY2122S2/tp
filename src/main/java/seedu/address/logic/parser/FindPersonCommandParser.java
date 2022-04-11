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

import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

public class FindPersonCommandParser extends FindCommandParser {

    /**
     * Checks for any data that does not follow format.
     *
     * @param groups All groups captured by the g/ flag.
     * @return A new FindPersonCommand Object ready for execution.
     * @throws ParseException An invalid input was found.
     */
    public FindPersonCommand parse(List<String> groups) throws ParseException {

        for (String group : groups) {

            if (havePrefixesPresent(group, PREFIX_DATE, PREFIX_TIME, PREFIX_GROUP,
                    PREFIX_INFORMATION, PREFIX_HEADER)) {
                throw new ParseException("[" + group + "] Invalid flags are found.");
            }

            ArgumentMultimap fields = ArgumentTokenizer.tokenize(padding + group, PREFIX_NAME, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_JOB, PREFIX_STAGE);

            checkInvalidName(fields.getAllValues(PREFIX_NAME), group);
            checkInvalidPhone(fields.getAllValues(PREFIX_PHONE), group);
            checkInvalidEmail(fields.getAllValues(PREFIX_EMAIL), group);
            checkInvalidAddress(fields.getAllValues(PREFIX_ADDRESS), group);
            checkInvalidJob(fields.getAllValues(PREFIX_JOB), group);
            checkInvalidStage(fields.getAllValues(PREFIX_STAGE), group);
        }

        return new FindPersonCommand(new PersonContainsKeywordsPredicate(groups));
    }
}


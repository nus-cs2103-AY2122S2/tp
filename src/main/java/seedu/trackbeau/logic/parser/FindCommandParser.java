package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_HAIRTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_REGDATE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SKINTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STAFFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.trackbeau.logic.commands.customer.FindCustomerCommand;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.customer.SearchContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCustomerCommand object
 */
public class FindCommandParser implements Parser<FindCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCustomerCommand
     * and returns a FindCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCustomerCommand parse(String userInput) throws ParseException {
        Integer attributeCount = 11;

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_SKINTYPE, PREFIX_HAIRTYPE,
                    PREFIX_BIRTHDATE, PREFIX_REGDATE,PREFIX_STAFFS, PREFIX_SERVICES, PREFIX_ALLERGIES);
        Prefix[] prefixList = {PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
            PREFIX_SKINTYPE, PREFIX_HAIRTYPE, PREFIX_BIRTHDATE, PREFIX_REGDATE,
                PREFIX_STAFFS, PREFIX_SERVICES, PREFIX_ALLERGIES};

        if (userInput.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCustomerCommand.MESSAGE_USAGE));
        }

        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections.nCopies(attributeCount, null));

        for (int i = 0; i < attributeCount; i++) {
            if (argMultimap.getValue(prefixList[i]).isPresent() && argMultimap.getPreamble().isEmpty()) {
                //parseAddress is used because it allows for all formats except for empty strings
                prefixArr.add(i,
                    Arrays.asList(ParserUtil
                        .parseName(argMultimap.getValue(prefixList[i]).get()).toString().split(" ")));
            }
        }

        return new FindCustomerCommand(new SearchContainsKeywordsPredicate(prefixArr));

    }
}

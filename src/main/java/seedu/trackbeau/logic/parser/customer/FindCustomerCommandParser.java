package seedu.trackbeau.logic.parser.customer;

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
import static seedu.trackbeau.model.customer.CustomerSearchContainsKeywordsPredicate.NON_TAG_ATTRIBUTE_COUNT;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.trackbeau.logic.commands.customer.FindCustomerCommand;
import seedu.trackbeau.logic.parser.ArgumentMultimap;
import seedu.trackbeau.logic.parser.ArgumentTokenizer;
import seedu.trackbeau.logic.parser.Parser;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.logic.parser.Prefix;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.customer.CustomerSearchContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCustomerCommand object
 */
public class FindCustomerCommandParser implements Parser<FindCustomerCommand> {
    private Prefix[] prefixList = { PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_SKINTYPE,
                                    PREFIX_HAIRTYPE, PREFIX_BIRTHDATE, PREFIX_REGDATE, PREFIX_STAFFS,
                                    PREFIX_SERVICES, PREFIX_ALLERGIES};
    private String[] parse = {"parseName", "parsePhone", "parseEmail", "parseAddress", "parseSkinType", "parseHairType",
        "parseBirthDate", "parseRegistrationDate"};

    /**
     * Parses the given {@code String} of arguments in the context of the FindCustomerCommand
     * and returns a FindCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCustomerCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, prefixList[0], prefixList[1], prefixList[2], prefixList[3],
                    prefixList[4], prefixList[5], prefixList[6], prefixList[7], prefixList[8], prefixList[9],
                    prefixList[10]);

        prefixParser(argMultimap);

        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections
                .nCopies(CustomerSearchContainsKeywordsPredicate.FIND_ATTRIBUTE_COUNT, null));
        try {
            for (int i = 0; i < CustomerSearchContainsKeywordsPredicate.FIND_ATTRIBUTE_COUNT; i++) {
                if (!argMultimap.getValue(prefixList[i]).isPresent()) {
                    continue;
                }
                if (i < NON_TAG_ATTRIBUTE_COUNT) {
                    for (String value : argMultimap.getValue(prefixList[i]).get().split(" ")) {
                        Method m = ParserUtil.class.getDeclaredMethod(parse[i], String.class);
                        m.invoke(null, value).toString();
                    }
                }
                prefixArr.set(i, Arrays.asList(ParserUtil.parseFindValues(argMultimap
                    .getValue(prefixList[i]).get()).toString().split(" ")));
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException ie) {
            if (ie.getCause() instanceof ParseException) {
                throw (ParseException) ie.getCause();
            }
        }
        CustomerSearchContainsKeywordsPredicate customerSearch = new CustomerSearchContainsKeywordsPredicate(prefixArr);
        return new FindCustomerCommand(customerSearch);

    }

    /**
     * Parses the given {@code ArgumentMultimap} to check if any of the valid prefix is present.
     * @throws ParseException if the user input does not contain any valid prefix.
     */
    public void prefixParser(ArgumentMultimap argMultimap) throws ParseException {
        Boolean hasPrefix = false;
        for (Prefix prefix : prefixList) {
            if (ParserUtil.arePrefixesPresent(argMultimap, prefix)) {
                hasPrefix = true;
                break;
            }
        }

        if (!hasPrefix || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCustomerCommand.MESSAGE_USAGE));
        }
    }
}

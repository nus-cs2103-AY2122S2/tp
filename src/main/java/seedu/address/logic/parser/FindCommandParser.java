package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_PACKAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.CombineContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.FieldContainsKeywordsPredicate;
import seedu.address.model.person.predicates.InsurancePackageContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of prefixes and arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_INSURANCE_PACKAGE,
                        PREFIX_ADDRESS, PREFIX_TAG);

        if (!aPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_INSURANCE_PACKAGE, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (noKeywordsPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_INSURANCE_PACKAGE, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_NO_KEYWORD));
        }

        List<FieldContainsKeywordsPredicate> predicatesList = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            List<String> nameKeywords = parseKeywords(argMultimap.getValue(PREFIX_NAME).get());
            NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(nameKeywords);
            predicatesList.add(namePredicate);
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            List<String> phoneKeywords = parseKeywords(argMultimap.getValue(PREFIX_PHONE).get());
            PhoneContainsKeywordsPredicate phonePredicate = new PhoneContainsKeywordsPredicate(phoneKeywords);
            predicatesList.add(phonePredicate);
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            List<String> emailKeywords = parseKeywords(argMultimap.getValue(PREFIX_EMAIL).get());
            EmailContainsKeywordsPredicate emailPredicate = new EmailContainsKeywordsPredicate(emailKeywords);
            predicatesList.add(emailPredicate);
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            List<String> addressKeywords = parseKeywords(argMultimap.getValue(PREFIX_ADDRESS).get());
            AddressContainsKeywordsPredicate addressPredicate = new AddressContainsKeywordsPredicate(addressKeywords);
            predicatesList.add(addressPredicate);
        }
        if (argMultimap.getValue(PREFIX_INSURANCE_PACKAGE).isPresent()) {
            List<String> insurancePackageKeywords = parseKeywords(argMultimap.getValue(PREFIX_INSURANCE_PACKAGE).get());
            InsurancePackageContainsKeywordsPredicate insurancePackagePredicate =
                    new InsurancePackageContainsKeywordsPredicate(insurancePackageKeywords);
            predicatesList.add(insurancePackagePredicate);
        }
        if (!argMultimap.getAllValues(PREFIX_TAG).isEmpty()) {
            List<String> tagsKeywords = parseKeywords(String.join(" ", argMultimap.getAllValues(PREFIX_TAG)));
            TagsContainsKeywordsPredicate tagsPredicate = new TagsContainsKeywordsPredicate(tagsKeywords);
            predicatesList.add(tagsPredicate);
        }
        return new FindCommand(new CombineContainsKeywordsPredicate(predicatesList));
    }

    /**
     * Parses the arguments given for the find field into a List of String
     */
    private static List<String> parseKeywords(String input) {
        return Arrays.asList(input.split("\\s+"));
    }

    /**
     * Returns true if one of the prefix contains {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean aPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if any of the values mapped to the field prefix is empty
     */
    private static boolean noKeywordsPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> {
            if (argumentMultimap.getValue(prefix).isPresent()) {
                return argumentMultimap.getValue(prefix).get().isBlank();
            }
            return false;
        });
    }
}

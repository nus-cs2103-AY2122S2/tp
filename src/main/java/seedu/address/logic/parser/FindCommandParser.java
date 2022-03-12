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
import seedu.address.logic.commands.FindCommand.FindPersonDescriptor;
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
     * Parses the given {@code String} of arguments in the context of the FindCommand
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

        FindPersonDescriptor findPersonDescriptor = new FindPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameKeywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            NameContainsKeywordsPredicate namePredicate =
                    new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
            findPersonDescriptor.setNamePredicate(namePredicate);
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String[] phoneKeywords = argMultimap.getValue(PREFIX_PHONE).get().split("\\s+");
            PhoneContainsKeywordsPredicate phonePredicate =
                    new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords));
            findPersonDescriptor.setPhonePredicate(phonePredicate);
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String[] emailKeywords = argMultimap.getValue(PREFIX_EMAIL).get().split("\\s+");
            EmailContainsKeywordsPredicate emailPredicate =
                    new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords));
            findPersonDescriptor.setEmailPredicate(emailPredicate);
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String[] addressKeywords = argMultimap.getValue(PREFIX_ADDRESS).get().split("\\s+");
            AddressContainsKeywordsPredicate addressPredicate =
                    new AddressContainsKeywordsPredicate(Arrays.asList(addressKeywords));
            findPersonDescriptor.setAddressPredicate(addressPredicate);
        }
        if (argMultimap.getValue(PREFIX_INSURANCE_PACKAGE).isPresent()) {
            String[] insurancePackageKeywords = argMultimap.getValue(PREFIX_INSURANCE_PACKAGE).get().split("\\s+");
            InsurancePackageContainsKeywordsPredicate insurancePackagePredicate =
                    new InsurancePackageContainsKeywordsPredicate(Arrays.asList(insurancePackageKeywords));
            findPersonDescriptor.setInsurancePackagePredicate(insurancePackagePredicate);
        }
        if (!argMultimap.getAllValues(PREFIX_TAG).isEmpty()) {
            String[] tagsKeywords = String.join(" ", argMultimap.getAllValues(PREFIX_TAG)).split("\\s+");
            TagsContainsKeywordsPredicate tagsPredicate =
                    new TagsContainsKeywordsPredicate(Arrays.asList(tagsKeywords));
            findPersonDescriptor.setTagsPredicate((tagsPredicate));
        }
        return new FindCommand(createCombineContainsKeywordsPredicate(findPersonDescriptor));
    }

    /**
     * Creates and returns a {@code CombineContainsKeywordsPredicate } that contains all the predicates
     * of the fields given by the user put into the {@code FindPersonDescriptor }
     */
    private static CombineContainsKeywordsPredicate createCombineContainsKeywordsPredicate(
            FindPersonDescriptor findPersonDescriptor) {
        List<FieldContainsKeywordsPredicate> predicatesList = new ArrayList<>();
        if (findPersonDescriptor.getNamePredicate().isPresent()) {
            predicatesList.add(findPersonDescriptor.getNamePredicate().get());
        }
        if (findPersonDescriptor.getPhonePredicate().isPresent()) {
            predicatesList.add(findPersonDescriptor.getPhonePredicate().get());
        }
        if (findPersonDescriptor.getEmailPredicate().isPresent()) {
            predicatesList.add(findPersonDescriptor.getEmailPredicate().get());
        }
        if (findPersonDescriptor.getAddressPredicate().isPresent()) {
            predicatesList.add(findPersonDescriptor.getAddressPredicate().get());
        }
        if (findPersonDescriptor.getInsurancePackagePredicate().isPresent()) {
            predicatesList.add(findPersonDescriptor.getInsurancePackagePredicate().get());
        }
        if (findPersonDescriptor.getTagsPredicate().isPresent()) {
            predicatesList.add(findPersonDescriptor.getTagsPredicate().get());
        }
        return new CombineContainsKeywordsPredicate(predicatesList);
    }

    /**
     * Returns true if one of the prefix contains {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean aPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;

import java.util.Arrays;

import seedu.address.logic.commands.FindByPrefixCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.person.TutorialContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    public static final String EMPTY_INPUT = "NULL_INPUT";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_TAG, PREFIX_STUDENTID, PREFIX_TUTORIALNAME);

        String name = argMultimap.getValue(PREFIX_NAME).isPresent()
                ? argMultimap.getValue(PREFIX_NAME).get()
                : EMPTY_INPUT;
        String phone = argMultimap.getValue(PREFIX_PHONE).isPresent()
                ? argMultimap.getValue(PREFIX_PHONE).get()
                : EMPTY_INPUT;
        String email = argMultimap.getValue(PREFIX_EMAIL).isPresent()
                ? argMultimap.getValue(PREFIX_EMAIL).get()
                : EMPTY_INPUT;
        String address = argMultimap.getValue(PREFIX_ADDRESS).isPresent()
                ? argMultimap.getValue(PREFIX_ADDRESS).get()
                : EMPTY_INPUT;
        String tag = argMultimap.getValue(PREFIX_TAG).isPresent()
                ? argMultimap.getValue(PREFIX_TAG).get()
                : EMPTY_INPUT;
        String studentId = argMultimap.getValue(PREFIX_STUDENTID).isPresent()
                ? argMultimap.getValue(PREFIX_STUDENTID).get()
                : EMPTY_INPUT;
        String tutorialName = argMultimap.getValue(PREFIX_TUTORIALNAME).isPresent()
                ? argMultimap.getValue(PREFIX_TUTORIALNAME).get()
                : EMPTY_INPUT;

        boolean isPrefixPresent =
                !name.equals(EMPTY_INPUT)
                || !phone.equals(EMPTY_INPUT)
                || !email.equals(EMPTY_INPUT)
                || !address.equals(EMPTY_INPUT)
                || !tag.equals(EMPTY_INPUT)
                || !studentId.equals(EMPTY_INPUT)
                || !tutorialName.equals(EMPTY_INPUT);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        if (isPrefixPresent) {
            String[] nameByPrefixKeywords = name.split("\\s+");
            String[] phoneByPrefixKeywords = phone.split("\\s+");
            String[] emailByPrefixKeywords = email.split("\\s+");
            String[] addressByPrefixKeywords = address.split("\\s+");
            String[] tagByPrefixKeywords = tag.split("\\s+");
            String[] studentIdByPrefixKeywords = studentId.split("\\s+");
            String[] tutorialNameByPrefixKeywords = tutorialName.split("\\s+");
            return new FindByPrefixCommand(
                    new NameContainsKeywordsPredicate(Arrays.asList(nameByPrefixKeywords), true),
                    new PhoneContainsKeywordsPredicate(Arrays.asList(phoneByPrefixKeywords)),
                    new EmailContainsKeywordsPredicate(Arrays.asList(emailByPrefixKeywords)),
                    new AddressContainsKeywordsPredicate(Arrays.asList(addressByPrefixKeywords)),
                    new TagContainsKeywordsPredicate(Arrays.asList(tagByPrefixKeywords)),
                    new StudentIdContainsKeywordsPredicate(Arrays.asList(studentIdByPrefixKeywords)),
                    new TutorialContainsKeywordsPredicate(Arrays.asList(tutorialNameByPrefixKeywords))
            );
        } else {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords), false));
        }
    }

}

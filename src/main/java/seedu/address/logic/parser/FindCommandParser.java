package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACADEMIC_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AcademicMajorContainsKeywordsPredicate;
import seedu.address.model.person.AttributeContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ACADEMIC_MAJOR, PREFIX_TAG);

        if (args.contains(PREFIX_NAME.getPrefix())) {
            String[] keywords = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get())
                    .toString().split("\\s+");
            AttributeContainsKeywordsPredicate type = new NameContainsKeywordsPredicate(Arrays.asList(keywords));
            return new FindCommand(type);
        } else if (args.contains(PREFIX_PHONE.getPrefix())) {
            String[] keywords = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get())
                    .toString().split("\\s+");
            AttributeContainsKeywordsPredicate type = new PhoneContainsKeywordsPredicate(Arrays.asList(keywords));
            return new FindCommand(type);
        } else if (args.contains(PREFIX_EMAIL.getPrefix())) {
            String[] keywords = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get())
                    .toString().split("\\s+");
            AttributeContainsKeywordsPredicate type = new EmailContainsKeywordsPredicate(Arrays.asList(keywords));
            return new FindCommand(type);
        } else if (args.contains(PREFIX_ACADEMIC_MAJOR.getPrefix())) {
            String[] keywords = ParserUtil.parseAcademicMajor(argMultimap.getValue(PREFIX_ACADEMIC_MAJOR).get())
                    .toString().split("\\s+");
            AttributeContainsKeywordsPredicate type =
                    new AcademicMajorContainsKeywordsPredicate(Arrays.asList(keywords));
            return new FindCommand(type);
        } else if (args.contains(PREFIX_TAG.getPrefix())) {
            String[] keywords = ParserUtil.parseName(argMultimap.getValue(PREFIX_TAG).get())
                    .toString().split("\\s+");
            AttributeContainsKeywordsPredicate type =
                    new TagContainsKeywordsPredicate(Arrays.asList(keywords));
            return new FindCommand(type);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}


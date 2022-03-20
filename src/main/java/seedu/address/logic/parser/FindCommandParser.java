package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACADEMIC_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AcademicMajor;
import seedu.address.model.person.AcademicMajorContainsKeywordsPredicate;
import seedu.address.model.person.AttributeContainsKeywordsPredicate;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] keywords;
        if (args.contains(PREFIX_NAME.getPrefix())) {
            keywords = parseName(args).split("\\s");
            AttributeContainsKeywordsPredicate type = new NameContainsKeywordsPredicate(Arrays.asList(keywords));
            return new FindCommand(type);
        } else if (args.contains(PREFIX_PHONE.getPrefix())) {
            keywords = parsePhone(args).split("\\s");
            AttributeContainsKeywordsPredicate type = new PhoneContainsKeywordsPredicate(Arrays.asList(keywords));
            return new FindCommand(type);
        } else if (args.contains(PREFIX_EMAIL.getPrefix())) {
            keywords = parseEmail(args).split("\\s");
            AttributeContainsKeywordsPredicate type = new EmailContainsKeywordsPredicate(Arrays.asList(keywords));
            return new FindCommand(type);
        } else if (args.contains(PREFIX_ACADEMIC_MAJOR.getPrefix())) {
            keywords = parseAcademicMajor(args).split("\\s");
            AttributeContainsKeywordsPredicate type =
                    new AcademicMajorContainsKeywordsPredicate(Arrays.asList(keywords));
            return new FindCommand(type);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments into a {@code String name}
     * which will be used for {@code FindCommandParser} object for execution.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the user input does not conform to {@code Name} format.
     */
    public String parseName(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        return name.fullName;
    }

    /**
     * Parses the given {@code String} of arguments into a {@code String phone}
     * which will be used for {@code FindCommandParser} object for execution.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the user input does not conform to {@code Phone} format.
     */
    public String parsePhone(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PHONE);
        if (!arePrefixesPresent(argMultimap, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        return phone.value;
    }

    /**
     * Parses the given {@code String} of arguments into a {@code String email}
     * which will be used for {@code FindCommandParser} object for execution.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the user input does not conform to {@code Email} format.
     */
    public String parseEmail(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EMAIL);
        if (!arePrefixesPresent(argMultimap, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        return email.value;
    }

    /**
     * Parses the given {@code String} of arguments into a {@code String academicMajor}
     * which will be used for {@code FindCommandParser} object for execution.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the user input does not conform to {@code AcademicMajor} format.
     */
    public String parseAcademicMajor(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ACADEMIC_MAJOR);
        if (!arePrefixesPresent(argMultimap, PREFIX_ACADEMIC_MAJOR)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        AcademicMajor academicMajor = ParserUtil.parseAcademicMajor(argMultimap.getValue(PREFIX_ACADEMIC_MAJOR).get());
        return academicMajor.value;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}


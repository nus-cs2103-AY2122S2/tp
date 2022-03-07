package seedu.address.logic.parser;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;

import java.util.Arrays;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent") // This is checked using our custom function (arePrefixesPresent).
    public FindCommand parse(String args) throws ParseException {
        FindCommand findCommand;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);

        if (arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID) // user inputted both name and id
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) { // name was used
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            String[] nameKeywords = name.toString().split("\\s+");
            findCommand = new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (arePrefixesPresent(argMultimap, PREFIX_ID)){ // student id was used
            StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_ID).get());
            String[] idKeywords = studentId.toString().split("\\s+");
            findCommand = new FindCommand(new StudentIdContainsKeywordsPredicate(Arrays.asList(idKeywords)));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return findCommand;
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.trackbeau.model.customer.SearchContainsKeywordsPredicate;
import seedu.trackbeau.logic.commands.FindCommand;
import seedu.trackbeau.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String userInput) throws ParseException {
        if (userInput.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_SKINTYPE, PREFIX_HAIRTYPE, PREFIX_STAFFS, PREFIX_SERVICES, PREFIX_ALLERGIES);

        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections.nCopies(9, null));

        if (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getPreamble().isEmpty()) {
            prefixArr.add(0,
                    Arrays.asList(ParserUtil
                            .parseName(argMultimap.getValue(PREFIX_NAME).get()).toString().split(" ")));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent() && argMultimap.getPreamble().isEmpty()) {
            prefixArr.add(1,
                    Arrays.asList(ParserUtil
                            .parseName(argMultimap.getValue(PREFIX_PHONE).get()).toString().split(" ")));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent() && argMultimap.getPreamble().isEmpty()) {
            prefixArr.add(2,
                    Arrays.asList(ParserUtil
                            .parseName(argMultimap.getValue(PREFIX_EMAIL).get()).toString().split(" ")));
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent() && argMultimap.getPreamble().isEmpty()) {
            prefixArr.add(3,
                    Arrays.asList(ParserUtil
                            .parseName(argMultimap.getValue(PREFIX_ADDRESS).get()).toString().split(" ")));
        }

        if (argMultimap.getValue(PREFIX_SKINTYPE).isPresent() && argMultimap.getPreamble().isEmpty()) {
            prefixArr.add(4,
                    Arrays.asList(ParserUtil
                            .parseName(argMultimap.getValue(PREFIX_SKINTYPE).get()).toString().split(" ")));
        }

        if (argMultimap.getValue(PREFIX_HAIRTYPE).isPresent() && argMultimap.getPreamble().isEmpty()) {
            prefixArr.add(5,
                    Arrays.asList(ParserUtil
                            .parseName(argMultimap.getValue(PREFIX_HAIRTYPE).get()).toString().split(" ")));
        }

        if (argMultimap.getValue(PREFIX_STAFFS).isPresent() && argMultimap.getPreamble().isEmpty()) {
            prefixArr.add(6,
                    Arrays.asList(ParserUtil
                            .parseName(argMultimap.getValue(PREFIX_STAFFS).get()).toString().split(" ")));
        }

        if (argMultimap.getValue(PREFIX_SERVICES).isPresent() && argMultimap.getPreamble().isEmpty()) {
            prefixArr.add(7,
                    Arrays.asList(ParserUtil
                            .parseName(argMultimap.getValue(PREFIX_SERVICES).get()).toString().split(" ")));
        }

        if (argMultimap.getValue(PREFIX_ALLERGIES).isPresent() && argMultimap.getPreamble().isEmpty()) {
            prefixArr.add(8,
                    Arrays.asList(ParserUtil
                            .parseName(argMultimap.getValue(PREFIX_ALLERGIES).get()).toString().split(" ")));
        }

        System.out.println(prefixArr.toString());

        return new FindCommand(new SearchContainsKeywordsPredicate(prefixArr));

        }
}

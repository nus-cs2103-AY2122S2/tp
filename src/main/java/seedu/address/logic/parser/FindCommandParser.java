package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_PACKAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.ParseNoKeywordException;
import seedu.address.logic.parser.exceptions.ParseNoPrefixException;
import seedu.address.model.person.predicates.CombineContainsKeywordsPredicate;
import seedu.address.model.person.predicates.FieldContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private final Logger logger = LogsCenter.getLogger(FindCommandParser.class);
    /**
     * Parses the given {@code String} of prefixes and arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        logger.info("Starting parse find args");
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_INSURANCE_PACKAGE,
                        PREFIX_ADDRESS, PREFIX_TAG);
        if (!aPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_INSURANCE_PACKAGE, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            logger.log(Level.WARNING, "Find prefix not present");
            throw new ParseNoPrefixException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (noKeywordsPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_INSURANCE_PACKAGE, PREFIX_TAG)) {
            logger.log(Level.WARNING, "Find keywords not present");
            throw new ParseNoKeywordException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                FindCommand.MESSAGE_NO_KEYWORD));
        }

        assert !argMultimap.getArgMap().isEmpty() : "Argument Multimap should not be empty";
        List<FieldContainsKeywordsPredicate> predicatesList = ParserUtil.parseArgMap(argMultimap);
        return new FindCommand(new CombineContainsKeywordsPredicate(predicatesList));
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

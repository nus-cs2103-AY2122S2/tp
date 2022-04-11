package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FormatPersonUtil;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class CopyCommandParser implements Parser<CopyCommand> {
    private static final Logger logger = LogsCenter.getLogger(CopyCommandParser.class);
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_MODULE, PREFIX_COMMENT, PREFIX_FORMAT);

        Index index;
        List<Prefix> prefixes = ArgumentTokenizer.getPrefixListInOrder(args, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STATUS, PREFIX_COMMENT, PREFIX_MODULE);

        if (prefixes.isEmpty()) {
            logger.info("Copying all Person fields");
            prefixes.addAll(Arrays.asList(PREFIX_NAME, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STATUS, PREFIX_MODULE, PREFIX_COMMENT));
        }

        if (argMultimap.getPreamble().equals("")) {
            logger.info("Copying entire AddressBook");
            return new CopyCommand(prefixes, getFormatPersonUtil(argMultimap));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE), pe);
        }

        logger.info("Copying person at index " + index.getOneBased());
        return new CopyCommand(index, prefixes, getFormatPersonUtil(argMultimap));
    }

    private FormatPersonUtil getFormatPersonUtil(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_FORMAT).isPresent()) {
            return ParserUtil.parseFormat(argMultimap.getValue(PREFIX_FORMAT).get());
        } else {
            return new FormatPersonUtil();
        }
    }
}

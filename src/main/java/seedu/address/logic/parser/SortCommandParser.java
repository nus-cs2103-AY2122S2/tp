package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMETERS_SUPPLIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STATUS, PREFIX_TAG, PREFIX_ORDER);

        List<Prefix> prefixes = ArgumentTokenizer.sortPrefixOrder(args, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STATUS, PREFIX_TAG);

        if (prefixes.size() == 0) {
            throw new ParseException(String.format(MESSAGE_NO_PARAMETERS_SUPPLIED, SortCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getPreamble().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String successField = formatFields(prefixes);

        if (argMultimap.getValue(PREFIX_ORDER).isPresent()) {
            String order = argMultimap.getValue(PREFIX_ORDER).get().toLowerCase();
            if (!order.equals("asc") && !order.equals("desc")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
            String formattedOrder = order.equals("asc") ? "ascending" : "descending";
            return new SortCommand(prefixes, formattedOrder, successField);
        }

        return new SortCommand(prefixes, "ascending", successField);
    }

    /**
     * Formats the fields to be displayed in the success message
     * @param fields the list of prefixes to be displayed
     * @return the formatted success message
     */
    public static String formatFields(List<Prefix> fields) throws ParseException {
        List<String> formattedFields = new ArrayList<>();
        for (Prefix field : fields) {
            formattedFields.add(formatPrefix(field));
        }
        return formattedFields.toString();
    }

    private static String formatPrefix(Prefix prefix) throws ParseException {
        if (prefix.equals(PREFIX_NAME)) {
            return "Name";
        } else if (prefix.equals(PREFIX_PHONE)) {
            return "Phone";
        } else if (prefix.equals(PREFIX_EMAIL)) {
            return "Email";
        } else if (prefix.equals(PREFIX_ADDRESS)) {
            return "Address";
        } else if (prefix.equals(PREFIX_STATUS)) {
            return "Status";
        } else if (prefix.equals(PREFIX_TAG)) {
            return "Module";
        } else {
            throw new ParseException("Prefix is not supported");
        }
    }
}

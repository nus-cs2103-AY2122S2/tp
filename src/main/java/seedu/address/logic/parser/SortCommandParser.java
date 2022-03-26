package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMETERS_SUPPLIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    private static final String defaultOrder = "ASC";

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STATUS, PREFIX_MODULE, PREFIX_COMMENT);

        List<Prefix> prefixes = ArgumentTokenizer.getPrefixListInOrder(args, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STATUS, PREFIX_MODULE, PREFIX_COMMENT);

        List<String> orders = ArgumentTokenizer.getArgListInOrder(args, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STATUS, PREFIX_MODULE, PREFIX_COMMENT);

        if (prefixes.size() == 0) {
            throw new ParseException(String.format(MESSAGE_NO_PARAMETERS_SUPPLIED, SortCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getPreamble().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        for (String order : orders) {
            String uppercaseOrder = order.toUpperCase();
            if (!uppercaseOrder.equals("ASC") && !uppercaseOrder.equals("DESC") && !uppercaseOrder.equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
        }

        String successField = formatFields(prefixes, orders);
        return new SortCommand(prefixes, orders, successField);
    }

    /**
     * Formats the fields to be displayed in the success message
     * @param fields the list of prefixes to be displayed
     * @return the formatted success message
     */
    public static String formatFields(List<Prefix> fields, List<String> orders) throws ParseException {
        List<List<String>> formattedFields = new ArrayList<>();
        for (int i = 0; i < fields.size(); i++) {
            List<String> formattedField = new ArrayList<>();
            formattedField.add((fields.get(i)).getDescription());
            if (orders.get(i).equals("")) {
                formattedField.add(defaultOrder);
            } else {
                formattedField.add(orders.get(i).toUpperCase());
            }
            formattedFields.add(formattedField);
        }
        return formattedFields.toString();
    }
}

package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FieldRegistry;

/**
 * Parses user input for sorting the person list in address book.
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @param args Arguments for sorting.
     * @return SortCommand object for execution.
     * @throws ParseException if there is invalid input or arguments given are empty.
     */
    @Override
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Prefix[] allPrefixes = Arrays.copyOf(FieldRegistry.PREFIXES, FieldRegistry.PREFIXES.length);
        Map<String, Prefix> prefixMap = Arrays.stream(allPrefixes)
                .collect(Collectors.toMap(Prefix::getPrefix, prefix -> prefix));
        String delimiters = "\\s|((?=" + Arrays.stream(allPrefixes).map(Prefix::getPrefix).collect(
                Collectors.joining("))|((?=")) + "))";

        String[] arguments = args.split(delimiters);
        return new SortCommand(getFieldSortOrderList(arguments, prefixMap));
    }

    /**
     * Gets the list on how the fields should be sorted based on the arguments provided.
     *
     * @param arguments information split in proper format based on what the user input.
     * @param prefixMap hashmap on the prefix and it's corresponding object.
     * @return list on how the fields should be sorted based on the arguments provided.
     */
    private List<SortCommand.FieldSortOrder> getFieldSortOrderList(String[] arguments, Map<String, Prefix> prefixMap)
            throws ParseException {
        List<SortCommand.FieldSortOrder> fieldSortOrderList = new ArrayList<SortCommand.FieldSortOrder>();

        for (int i = 0; i < arguments.length; ++i) {
            if (arguments[i].equals("")) {
                continue;
            }

            if (!prefixMap.containsKey(arguments[i]) && !arguments[i].equals(SortCommand.DESCENDING_KEYWORD)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }

            boolean isDescending = false;
            if (i + 1 < arguments.length) {
                isDescending = arguments[i + 1].equals(SortCommand.DESCENDING_KEYWORD);
            }

            fieldSortOrderList.add(new SortCommand.FieldSortOrder(prefixMap.get(arguments[i]), isDescending));
        }

        if (fieldSortOrderList.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return fieldSortOrderList;
    }
}

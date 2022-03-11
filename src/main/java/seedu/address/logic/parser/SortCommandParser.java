package seedu.address.logic.parser;


import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FieldRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class SortCommandParser implements Parser<SortCommand> {
    @Override
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Prefix[] allPrefixes = Arrays.copyOf(FieldRegistry.PREFIXES, FieldRegistry.PREFIXES.length);
        Map<String, Prefix> prefixMap = Arrays.stream(allPrefixes).collect(Collectors.toMap(Prefix::getPrefix, prefix -> prefix));
        String delimiters = "\\s|((?=" + Arrays.stream(allPrefixes).map(Prefix::getPrefix).collect(
                Collectors.joining("))|((?=")) + "))";

        String[] values = args.split(delimiters);
        return new SortCommand(getFieldSortOrderList(values, prefixMap));
    }

    private  List<SortCommand.FieldSortOrder> getFieldSortOrderList(String[] values, Map<String, Prefix> prefixMap) {
        List<SortCommand.FieldSortOrder> fieldSortOrderList = new ArrayList<SortCommand.FieldSortOrder>();

        for (int i = 0; i < values.length; ++i) {
            if (values[i].equals("")) {
                continue;
            }

            if (!prefixMap.containsKey(values[i])) {
                continue;
            }

            boolean isDescending = false;
            if (i + 1 < values.length) {
                isDescending = values[i + 1].equals("desc");
            }

            Prefix prefixTest = prefixMap.get(values[i]);

            fieldSortOrderList.add(new SortCommand.FieldSortOrder(prefixMap.get(values[i]), isDescending));
        }

        return fieldSortOrderList;
    }
}

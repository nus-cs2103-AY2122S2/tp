package seedu.trackermon.logic.parser;

import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_SORT_NAME;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_SORT_STATUS;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.trackermon.logic.commands.SortCommand;
import seedu.trackermon.logic.parser.exceptions.ParseException;
import seedu.trackermon.model.show.NameComparator;
import seedu.trackermon.model.show.Show;
import seedu.trackermon.model.show.StatusComparator;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    private static final String VALUE_ORDER_NAME = "NAME";
    private static final String VALUE_ORDER_STATUS = "STATUS";
    private static final String VALUE_ASC = "ASC";
    private static final String VALUE_DSC = "DSC";
    private static final int NO_VALUE = -1;
    private static int startingValue = 0;
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new SortCommand(new NameComparator());
        }

        Comparator<Show> nameComparator = new NameComparator();
        Comparator<Show> statusComparator = new StatusComparator();
        HashMap<Comparator<Show>, Integer> order = new HashMap<>();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORT_NAME,
                        PREFIX_SORT_STATUS, PREFIX_SORT_ORDER);

        //Initialise order map
        order.put(nameComparator, NO_VALUE);
        order.put(statusComparator, NO_VALUE);

        nameComparator = putIntoMap(order, nameComparator, PREFIX_SORT_NAME, argMultimap);
        statusComparator = putIntoMap(order, statusComparator, PREFIX_SORT_STATUS, argMultimap);

        reorderMap(order, argMultimap, nameComparator, statusComparator);

        //sort the hashmap
        Map<Comparator<Show>, Integer> sortedOrder = order.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Comparator<Show> comparator = buildComparator(sortedOrder);

        //nothing is present, sort by name ascending
        if (comparator == null) {
            return new SortCommand(nameComparator);
        }

        return new SortCommand(comparator);
    }

    private static Comparator<Show> chainComparator(Comparator<Show> comparator,
                                                    Map.Entry<Comparator<Show>, Integer> entry) {
        if (comparator == null) {
            return entry.getKey();
        } else {
            return comparator.thenComparing(entry.getKey());
        }
    }

    private static Comparator<Show> buildComparator(Map<Comparator<Show>, Integer> sortedOrder) {
        Comparator<Show> overallComparator = null;
        for (Map.Entry<Comparator<Show>, Integer> entry: sortedOrder.entrySet()) {
            if (entry.getValue() != NO_VALUE) {
                overallComparator = chainComparator(overallComparator, entry);
            }
        }
        return overallComparator;
    }


    private static Comparator<Show> putIntoMap(HashMap<Comparator<Show>, Integer> order,
                                  Comparator<Show> comparator, Prefix prefix, ArgumentMultimap argMultimap) {
        if (arePrefixesPresent(argMultimap, prefix)) {
            String valueName = argMultimap.getValue(prefix).orElse(VALUE_ASC);
            valueName = valueName.toUpperCase().trim();
            if (valueName.equals(VALUE_DSC)) {
                comparator = comparator.reversed();
            }
            order.put(comparator, startingValue);
            startingValue++;
        }

        return comparator;
    }

    private static void reorderMap(HashMap<Comparator<Show>, Integer> order, ArgumentMultimap argMultimap,
                                   Comparator<Show> nameComparator, Comparator<Show> statusComparator) {
        if (arePrefixesPresent(argMultimap, PREFIX_SORT_ORDER)) {
            String valueOrder = argMultimap.getValue(PREFIX_SORT_ORDER).get();
            valueOrder = valueOrder.toUpperCase().trim();
            int nameIndex = valueOrder.indexOf(VALUE_ORDER_NAME);
            int statusIndex = valueOrder.indexOf(VALUE_ORDER_STATUS);
            if (nameIndex == NO_VALUE) {
                nameIndex = Integer.MAX_VALUE;
            }
            if (statusIndex == NO_VALUE) {
                statusIndex = Integer.MAX_VALUE;
            }

            if (order.get(nameComparator) != NO_VALUE) {
                order.replace(nameComparator, nameIndex);
            }

            if (order.get(statusComparator) != NO_VALUE) {
                order.replace(statusComparator, statusIndex);
            }
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

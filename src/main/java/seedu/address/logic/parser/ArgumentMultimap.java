package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Stores mapping of prefixes to their respective arguments.
 * Each key may be associated with multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values may contain duplicate argument values, i.e. the same argument value
 * can be inserted multiple times for the same prefix.
 */
public class ArgumentMultimap {

    /** Prefixes mapped to their respective arguments**/
    private final Map<Prefix, List<String>> argMultimap = new HashMap<>();

    /**
     * Associates the specified argument value with {@code prefix} key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param prefix   Prefix key with which the specified argument value is to be associated
     * @param argValue Argument value to be associated with the specified prefix key
     */
    public void put(Prefix prefix, String argValue) {
        List<String> argValues = getAllValues(prefix);
        argValues.add(argValue);
        argMultimap.put(prefix, argValues);
    }

    /**
     * Returns the last value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns all values of {@code prefix}.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     */
    public List<String> getAllValues(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() {
        return getValue(new Prefix("")).orElse("");
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public boolean arePrefixesPresent(Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> this.getValue(prefix).isPresent());
    }

    /**
     * Returns true if there are no other prefixes in {@code ArgumentMultimap} other than
     * {@code prefixes}.
     */
    public boolean noOtherPrefixes(Prefix... prefixes) {
        Set<Prefix> excludedPrefixes = Set.of(prefixes);
        Set<Prefix> allRemainingPrefixes = new HashSet<>(argMultimap.keySet());
        allRemainingPrefixes.removeAll(excludedPrefixes);
        return allRemainingPrefixes.size() == 1; //I have no idea why there is an extra item
    }

    /**
     * Returns true if the all the {@code prefixes} were used and placed in the argMultiMap.
     */
    public boolean doesPrefixesExist(Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argMultimap.containsKey(prefix));
    }

    /**
     * Returns true if at least one prefix was used and placed in the argMultiMap.
     */
    public boolean atLeastOnePrefix(Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argMultimap.containsKey(prefix));
    }
}

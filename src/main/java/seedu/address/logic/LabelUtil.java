package seedu.address.logic;

import java.util.Map;
import java.util.TreeMap;

import seedu.address.model.label.Label;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;

/**
 * Contains utility methods for processing placeholder labels.
 */
public class LabelUtil {

    /**
     * Replaces all placeholder labels in the given {@code Map<Label, Phone>} with default labels.
     *
     * @param entries Map of phone numbers.
     * @return A {@code Map<Label, Phone>} with default labels.
     */
    public static Map<Label, Phone> replacePhonePlaceholders(Map<Label, Phone> entries) {
        Map<Label, Phone> labelledEntries = new TreeMap<>();
        Map<Label, Phone> placeholderEntries = new TreeMap<>();

        for (Label label : entries.keySet()) {
            if (label.isPlaceholder) {
                placeholderEntries.put(label, entries.get(label));
            } else {
                labelledEntries.put(label, entries.get(label));
            }
        }

        int i = 1;

        for (Label label : placeholderEntries.keySet()) {
            Label replacement = new Label("Phone #" + i, false);

            while (labelledEntries.containsKey(replacement)) {
                i++;
                replacement = new Label("Phone #" + i, false);
            }

            labelledEntries.put(replacement, placeholderEntries.get(label));
            i++;
        }
        return labelledEntries;
    }

    /**
     * Replaces all placeholder labels in the given {@code Map<Label, Email>} with default labels.
     *
     * @param entries Map of emails.
     * @return A {@code Map<Label, Email>} with default labels.
     */
    public static Map<Label, Email> replaceEmailPlaceholders(Map<Label, Email> entries) {
        Map<Label, Email> labelledEntries = new TreeMap<>();
        Map<Label, Email> placeholderEntries = new TreeMap<>();

        for (Label label : entries.keySet()) {
            if (label.isPlaceholder) {
                placeholderEntries.put(label, entries.get(label));
            } else {
                labelledEntries.put(label, entries.get(label));
            }
        }

        int i = 1;

        for (Label label : placeholderEntries.keySet()) {
            Label replacement = new Label("Email #" + i, false);

            while (labelledEntries.containsKey(replacement)) {
                i++;
                replacement = new Label("Email #" + i, false);
            }

            labelledEntries.put(replacement, placeholderEntries.get(label));
            i++;
        }
        return labelledEntries;
    }

    /**
     * Replaces all placeholder labels in the given {@code Map<Label, Address>} with default labels.
     *
     * @param entries Map of addresses.
     * @return A {@code Map<Label, Address>} with default labels.
     */
    public static Map<Label, Address> replaceAddressPlaceholders(Map<Label, Address> entries) {
        Map<Label, Address> labelledEntries = new TreeMap<>();
        Map<Label, Address> placeholderEntries = new TreeMap<>();

        for (Label label : entries.keySet()) {
            if (label.isPlaceholder) {
                placeholderEntries.put(label, entries.get(label));
            } else {
                labelledEntries.put(label, entries.get(label));
            }
        }

        int i = 1;

        for (Label label : placeholderEntries.keySet()) {
            Label replacement = new Label("Address #" + i, false);

            while (labelledEntries.containsKey(replacement)) {
                i++;
                replacement = new Label("Address #" + i, false);
            }

            labelledEntries.put(replacement, placeholderEntries.get(label));
            i++;
        }
        return labelledEntries;
    }
}

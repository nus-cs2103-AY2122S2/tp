package seedu.address.testutil;

import static seedu.address.logic.parser.ParserUtil.INPUT_DATE_FORMATTER;

import java.time.LocalDate;
import java.util.List;

import seedu.address.model.event.EventFilterPredicate;

/**
 * A utility class to help with building EventFilterPredicate objects.
 */
public class EventFilterPredicateBuilder {

    public static final String DEFAULT_NAME_SUBSTRING = "Default Event";
    public static final String DEFAULT_DATE = "12-5-2022";
    public static final String DEFAULT_FRIEND_NAME_SUBSTRING_1 = "Amy";
    public static final String DEFAULT_FRIEND_NAME_SUBSTRING_2 = "Alex";

    private String nameSubstring;
    private LocalDate date;
    private List<String> friendNameSubstrings;

    /**
     * Creates a {@code EventFilterPredicateBuilder} with the default details, including a valid date.
     */
    public EventFilterPredicateBuilder() {
        nameSubstring = DEFAULT_NAME_SUBSTRING;
        date = LocalDate.parse(DEFAULT_DATE, INPUT_DATE_FORMATTER);
        friendNameSubstrings = List.of(DEFAULT_FRIEND_NAME_SUBSTRING_1, DEFAULT_FRIEND_NAME_SUBSTRING_2);
    }

    /**
     * Sets the {@code nameSubstring} of the {@code EventFilterPredicate} that we are building.
     */
    public EventFilterPredicateBuilder withNameSubstring(String nameSubstring) {
        this.nameSubstring = nameSubstring;
        return this;
    }

    /**
     * Parses the {@code friendNameSubstrings} into a {@code List<String>} and set it to the
     * {@code EventFilterPredicate} that we are building.
     */
    public EventFilterPredicateBuilder withFriendNameSubstrings(String ... friendNameSubstrings) {
        this.friendNameSubstrings = List.of(friendNameSubstrings);
        return this;
    }

    /**
     * Sets the {@code date} of the {@code EventFilterPredicate} that we are building.
     */
    public EventFilterPredicateBuilder withDate(String date) {
        this.date = LocalDate.parse(date, INPUT_DATE_FORMATTER);
        return this;
    }

    /**
     * Clears the {@code date} of the {@code EventFilterPredicate} that we are building.
     */
    public EventFilterPredicateBuilder clearDate() {
        this.date = null;
        return this;
    }

    /**
     * Builds and returns the EventFilterPredicate with the data in the builder.
     */
    public EventFilterPredicate build() {
        if (date == null) {
            return new EventFilterPredicate(nameSubstring, friendNameSubstrings);
        } else {
            return new EventFilterPredicate(nameSubstring, date, friendNameSubstrings);
        }
    }
}
